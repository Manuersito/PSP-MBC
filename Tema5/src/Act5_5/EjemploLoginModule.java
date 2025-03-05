package Act5_5;

import java.util.Map;
import javax.security.auth.*;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class EjemploLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;

    public boolean commit() throws LoginException { return true; }
    public boolean logout() throws LoginException { return true; }
    public boolean abort() throws LoginException { return true; }

    public void initialize(Subject subject, CallbackHandler handler, Map state, Map options) {
        this.subject = subject;
        this.callbackHandler = handler;
    }

    public boolean login() throws LoginException {
        boolean autenticado = true;

        if (callbackHandler == null) {
            throw new LoginException("Se necesita CallbackHandler");
        }

        Callback[] callbacks = new Callback[2];
        callbacks[0] = new NameCallback("Nombre de usuario: ");
        callbacks[1] = new PasswordCallback("Clave: ", false);

        try {
            callbackHandler.handle(callbacks);

            String usuario = ((NameCallback) callbacks[0]).getName();
            char[] passw = ((PasswordCallback) callbacks[1]).getPassword();

            String clave = new String(passw);

            autenticado = ("pedro".equalsIgnoreCase(usuario) && "abcd".equals(clave));

            return autenticado;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
