
package clinica_veterinaria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CriptografiaService {

    @Value("${clinica.cpf.chave}")
    private String chaveBase64;

    private byte[] obterChave() {
        byte[] chave = Base64.getDecoder().decode(chaveBase64);

        if (chave.length != 32) {
            throw new IllegalArgumentException(
                    "A chave AES deve possuir 32 bytes.");
        }

        return chave;
    }

    public String criptografar(String cpf) {
        try {
            byte[] iv = new byte[12];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(obterChave(), "AES"),
                    new GCMParameterSpec(128, iv));

            byte[] criptografado = cipher.doFinal(
                    cpf.getBytes(StandardCharsets.UTF_8));

            byte[] resultado = new byte[iv.length + criptografado.length];

            System.arraycopy(iv, 0, resultado, 0, iv.length);
            System.arraycopy(criptografado, 0, resultado,
                    iv.length, criptografado.length);

            return Base64.getEncoder().encodeToString(resultado);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar CPF", e);
        }
    }

    public String gerarIndiceCpf(String cpf) {
        try {
            String cpfLimpo = cpf.replaceAll("\\D", "");

            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(obterChave(), "HmacSHA256"));

            byte[] resultado = mac.doFinal(
                    cpfLimpo.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(resultado);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar índice do CPF", e);
        }
    }
}
