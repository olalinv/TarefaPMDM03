package com.robottitto.tarefapmdm03.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.robottitto.tarefapmdm03.R;
import com.robottitto.tarefapmdm03.api.user.UserModelService;
import com.robottitto.tarefapmdm03.api.user.model.User;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private final static String TAG = "RegisterActivity";
    private final static String ADMIN_ROLE = "Administrador";
    private final static String CLIENT_ROLE = "Cliente";
    private final static String[] roles = {CLIENT_ROLE, ADMIN_ROLE};
    private UserModelService userModelService;

    private EditText etName;
    private EditText etSurname;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private Spinner spRole;
    private Button btCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialization
        userModelService = UserModelService.get(this);

        // View elements
        etName = findViewById(R.id.etName);
        etSurname = findViewById(R.id.etSurname);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        spRole = findViewById(R.id.spRole);
        btCreateAccount = findViewById(R.id.btCreateAccount);

        // Spinner options
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRole.setAdapter(adapter);

        // Events
        btCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String surname = etSurname.getText().toString();
                String email = etEmail.getText().toString();
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                int role = spRole.getSelectedItemPosition();
                // TODO Add more fields validations
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                    User user = new User(name, surname, email, username, password, role);
                    try {
                        userModelService.addUser(user);
                        List<User> users = userModelService.getUsers();
                        if (users.size() > 0) {
                            showToast(getString(R.string.user_created));
                            showToast(userModelService.getUser(user.getUsername()).toString());
                            go(LoginActivity.class, null, null);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        showToast(getString(R.string.error) + ": " + e.getMessage());
                    }
                } else {
                    showToast(getString(R.string.error_empty_fields));
                }
            }
        });
    }

    private void go(Class<?> activityClass, String paramId, String paramValue) {
        Intent intent = new Intent(this, activityClass);
        if (null != paramId) {
            intent.putExtra(paramId, paramValue);
        }
        startActivity(intent);
    }

    private void showToast(String message) {
        Toast toast =
                Toast.makeText(getApplicationContext(),
                        message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 120);
        toast.show();
    }

}
