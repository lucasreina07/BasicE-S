/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import java.io.Console;
import java.util.Arrays;
import java.io.IOException;
/**
 *
 * @author AlienWare
 */
public class Password {

    public static void main(String[] args) throws IOException {

        Console c = System.console();
        if (c == null) {
            System.err.println("No console.");
            System.exit(1);
        }

        String login = c.readLine("Ingrese su inicio de sesion: ");
        char[] oldPassword = c.readPassword("Ingrese su contrase;a anterior: ");

        if (verify(login, oldPassword)) {
            boolean noMatch;
            do {
                char[] newPassword1 = c.readPassword("Ingrese su nueva contrase;a: ");
                char[] newPassword2 = c.readPassword("Ingrese nuevamente la constrase;a: ");
                noMatch = !Arrays.equals(newPassword1, newPassword2);
                if (noMatch) {
                    c.format("Las contrase;as no coinciden. Intentelo de nuevo.%n");
                } else {
                    change(login, newPassword1);
                    c.format("Contrase;a para %s cambiado.%n", login);
                }
                Arrays.fill(newPassword1, ' ');
                Arrays.fill(newPassword2, ' ');
            } while (noMatch);
        }

        Arrays.fill(oldPassword, ' ');
    }

    // Dummy change method.
    static boolean verify(String login, char[] password) {
        // Este método siempre devuelve 
        // verdadero en este ejemplo. 
        // Modifique este método para verificar 
        // la contraseña de acuerdo con sus reglas. 
        return true;
    }

    // Dummy change method.
    static void change(String login, char[] password) {
        // Modifique este método para cambiar 
        // contraseña de acuerdo con sus reglas.
    }
}

