package com.example.app1.session;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.app1.domain.model.Usuario;
import com.example.app1.domain.model.Reserva;
import com.example.app1.domain.model.Comunicado;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton para gestionar el estado global de la sesión usando la entidad usuario, ajustes y datos mock para reservas y comunicados.
 */
public class SessionManager {
    private static final String PREFS_NAME = "app_padel_prefs";
    private static final String KEY_NOMBRE = "key_name";
    private static final String KEY_APELLIDO = "key_apellido";
    private static final String KEY_PORTAL   = "key_portal";
    private static final String KEY_PISO     = "key_piso";
    private static final String KEY_EMAIL = "key_email";
    private static final String KEY_PUSH = "key_push";
    private static final String KEY_EMAIL_NOTIF = "key_email_notif";

    private static SessionManager instance;
    private final SharedPreferences prefs;
    private Usuario usuario;
    private boolean pushNotificationsEnabled;
    private boolean emailNotificationsEnabled;
    private List<Reserva> reservas;
    private List<Comunicado> comunicados;

    private SessionManager(Context ctx) {

        prefs = ctx.getApplicationContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loadFromPrefs();

        // Inicializa datos mock

        reservas = new ArrayList<>();
        comunicados = new ArrayList<>();
    }

    /**
     * Devuelve la instancia del SessionManager.
     * @param ctx Contexto de la aplicación.
     * @return Instancia del SessionManager.
     */
    public static synchronized SessionManager getInstance(Context ctx) {

        if (instance == null) {

            instance = new SessionManager(ctx);

        }

        return instance;

    }

    /**
     * Carga los datos del usuario desde las preferencias compartidas y carga las configuraciones.
     */
    private void loadFromPrefs() {

        // Cargo usuario
        String nombre   = prefs.getString(KEY_NOMBRE, "Juan");
        String apellido = prefs.getString(KEY_APELLIDO, "Pérez");
        String portal   = prefs.getString(KEY_PORTAL, "A");
        String piso     = prefs.getString(KEY_PISO, "2");
        String email    = prefs.getString(KEY_EMAIL, "juan.perez@urbanizacion.com");

        usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setPortal(portal);
        usuario.setPiso(piso);
        usuario.setEmail(email);

        // Cargo settings

        pushNotificationsEnabled  = prefs.getBoolean(KEY_PUSH, true);
        emailNotificationsEnabled = prefs.getBoolean(KEY_EMAIL_NOTIF, true);

    }

    /**
     * Guarda los datos del usuario en las preferencias compartidas.
     */
    private void saveUser() {

        SharedPreferences.Editor e = prefs.edit();
        e.putString(KEY_NOMBRE,   usuario.getNombre());
        e.putString(KEY_APELLIDO, usuario.getApellido());
        e.putString(KEY_PORTAL,   usuario.getPortal());
        e.putString(KEY_PISO,     usuario.getPiso());
        e.putString(KEY_EMAIL,    usuario.getEmail());
        e.apply();

    }

    /**
     * Guarda las configuraciones en las SharedPreferences.
     */
    private void saveSettings() {

        SharedPreferences.Editor e = prefs.edit();
        e.putBoolean(KEY_PUSH, pushNotificationsEnabled);
        e.putBoolean(KEY_EMAIL_NOTIF, emailNotificationsEnabled);
        e.apply();

    }

    public Usuario getUser() {
        return usuario;
    }

    public void updateUser(Usuario u) {
        this.usuario = u;
        saveUser();
    }

    // Notification settings
    public boolean isPushEnabled() {
        return pushNotificationsEnabled;
    }

    public void setPushEnabled(boolean enabled) {

        this.pushNotificationsEnabled = enabled;
        saveSettings();

    }

    public boolean isEmailEnabled() {
        return emailNotificationsEnabled;
    }

    public void setEmailEnabled(boolean enabled) {

        this.emailNotificationsEnabled = enabled;
        saveSettings();

    }

    // Mock data accessors
    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> list) {
        this.reservas = list;
    }

    public List<Comunicado> getComunicados() {
        return comunicados;
    }

    public void setComunicados(List<Comunicado> list) {
        this.comunicados = list;
    }
}
