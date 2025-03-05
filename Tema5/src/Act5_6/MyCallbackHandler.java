package Act5_6;

import java.io.*;
import javax.security.auth.callback.*;

public class MyCallbackHandler implements CallbackHandler {
    private String usuario;
    private String clave;

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                NameCallback nameCB = (NameCallback) callback;
                System.out.print(nameCB.getPrompt());
                usuario = br.readLine().trim();
                nameCB.setName(usuario);
            } else if (callback instanceof PasswordCallback) {
                PasswordCallback passwordCB = (PasswordCallback) callback;
                System.out.print(passwordCB.getPrompt());
                clave = br.readLine().trim();
                passwordCB.setPassword(clave.toCharArray());
            }
        }
    }
}
