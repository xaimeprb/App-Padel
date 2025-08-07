package com.example.app1.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.example.app1.R;
import com.example.app1.domain.model.Usuario;
import com.example.app1.session.SessionManager;
import com.example.app1.ui.home.InicioActivity;

import java.util.Objects;

/**
 * LoginActivity: autentica al usuario y redirige al home o al registro.
 */
public class LoginActivity extends AppCompatActivity {

    private TextInputEditText emailInput, passwordInput;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        session = SessionManager.getInstance(this);

        // Si ya hay usuario registrado, ir directo al home
        Usuario current = session.getUser();

        if (!TextUtils.isEmpty(current.getEmail())) {

            startActivity(new Intent(this, InicioActivity.class));
            finish();
            return;

        }

        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);

        Button loginButton = findViewById(R.id.loginButton);
        TextView tvRegisterPrompt = findViewById(R.id.tvRegisterPrompt);

        // Subrayar solo la palabra 'cuenta'
        tvRegisterPrompt.setText(HtmlCompat.fromHtml(

                getString(R.string.login_prompt_html),
                HtmlCompat.FROM_HTML_MODE_LEGACY

        ));

        tvRegisterPrompt.setOnClickListener(v -> {

            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

        });

        loginButton.setOnClickListener(v -> {

            String email = Objects.requireNonNull(emailInput.getText()).toString().trim();
            String pwd   = Objects.requireNonNull(passwordInput.getText()).toString().trim();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(pwd)) {

                Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
                return;

            }

            // Simulación de autenticación
            Usuario u = new Usuario();
            u.setEmail(email);
            session.updateUser(u);

            // Ir al Home
            startActivity(new Intent(this, InicioActivity.class));
            finish();

        });
    }
}
