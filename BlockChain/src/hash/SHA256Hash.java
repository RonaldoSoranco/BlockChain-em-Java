package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Hash implements CalculadoraHash {

    @Override
    public String calcular(String entrada) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(entrada.getBytes());
            
          // Converte para o hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro", e);
        }
    }
}