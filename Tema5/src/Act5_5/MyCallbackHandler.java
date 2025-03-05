package Act5_5;

import java.io.*;
import javax.security.auth.callback.*;

public class MyCallbackHandler implements CallbackHandler {
    private String usuario;
    private String clave;

    // Método handle
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for (int i = 0; i < callbacks.length; i++) {
            Callback callback = callbacks[i];

            if (callback instanceof NameCallback) {
                NameCallback nameCB = (NameCallback) callback;

                // Muestra el prompt
                System.out.print(nameCB.getPrompt());

                // Entrada estándar, solicitar el nombre del usuario
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                usuario = br.readLine(); // Lectura por teclado
                nameCB.setName(usuario);

            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCB = (PasswordCallback) callback;

                // Muestra el prompt
                System.out.print(passwordCB.getPrompt());

                // Entrada estándar, solicitar la clave del usuario
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                clave = br.readLine(); // Lectura por teclado
                passwordCB.setPassword(clave.toCharArray());
            }
        }
    }
}
