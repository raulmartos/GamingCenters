package com.example.proyectodam_final.create;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.callbacks.UsernameCheckCallback;
import com.example.proyectodam_final.list.UserIconAdapter;
import com.example.proyectodam_final.pojo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

public class CreateUsers extends AppCompatActivity {

    private EditText edtUserName, edtName, edtLastName, edtPass, edtEmail, edtBirthday;
    private Spinner spIcon, spRole;
    private Button btAdd;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private Calendar date;
    final List<String> iconList = Arrays.asList("black", "groot", "jake", "rick", "sonic", "walter");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        elementMatcher();
        spinnerInfo();
        displayIconList();
        edtBirthday.setOnClickListener(v -> datePicker());
        btAdd.setOnClickListener(v -> addUser());
    }

    private void elementMatcher() {
        edtUserName = findViewById(R.id.userUserName);
        edtName = findViewById(R.id.userName);
        edtLastName = findViewById(R.id.userLastName);
        edtPass = findViewById(R.id.userPassword);
        edtEmail = findViewById(R.id.userEmail);
        edtBirthday = findViewById(R.id.userBirthday);
        edtBirthday.setFocusable(false);
        spIcon = findViewById(R.id.userIcon);
        spRole = findViewById(R.id.userRole);
        btAdd = findViewById(R.id.btUserAdd);
    }

    private void addUser() {
        String userName = edtUserName.getText().toString();
        if (!valueChecker()) {
            Toast.makeText(CreateUsers.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        } else if (validateDate().equals("")) {
            Toast.makeText(CreateUsers.this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
        } else if (spIcon.getSelectedItemPosition() == 0) {
            Toast.makeText(CreateUsers.this, "Debes seleccionar un icono", Toast.LENGTH_SHORT).show();
        } else if (!emailValidator(edtEmail.getText().toString())) {
            Toast.makeText(CreateUsers.this, "El email debe tener un formato válido", Toast.LENGTH_SHORT).show();
        } else {
            usernameChecker(userName, result -> {
                if (result == 0) {
                    Toast.makeText(CreateUsers.this, "El nombre de usuario ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("users").child(userName).setValue(fetchUserData());
                    Toast.makeText(CreateUsers.this, "Usuario creado!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private boolean valueChecker() {
        return !edtName.getText().toString().isEmpty()
                && !edtLastName.getText().toString().isEmpty()
                && !edtPass.getText().toString().isEmpty()
                && !edtEmail.getText().toString().isEmpty();
    }

    private User fetchUserData() {
        String name = edtName.getText().toString();
        String lastName = edtLastName.getText().toString();
        String password = edtPass.getText().toString();
        String email = edtEmail.getText().toString();
        String role = spinnerValue();
        String birthday = validateDate();
        String iconName = getSelectedIconName();
        return new User(name, lastName, birthday, email, password, role, iconName);
    }

    private void usernameChecker(String username, final UsernameCheckCallback callback) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("users");
        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean usernameExists = false;
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbUsername = userSnapshot.getKey();
                    if (Objects.requireNonNull(dbUsername).equals(username)) {
                        usernameExists = true;
                        break;
                    }
                }
                if (usernameExists) {
                    callback.onUsernameChecked(0);
                } else {
                    callback.onUsernameChecked(1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CreateUsers.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean emailValidator(String email) {
        final String EMAIL_REGEX = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        return pattern.matcher(email).matches();
    }


    private void spinnerInfo() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.roles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRole.setAdapter(adapter);
    }

    private String spinnerValue() {
        int position = spRole.getSelectedItemPosition();
        return spRole.getItemAtPosition(position).toString();
    }

    private void datePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            date = Calendar.getInstance();
            date.set(year1, month1, dayOfMonth);
            String formattedDate = dateFormat.format(date.getTime());
            edtBirthday.setText(formattedDate);
        }, year, month, day);
        dpDialog.show();
    }

    private String validateDate() {
        String input = edtBirthday.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "Escribe una fecha válida", Toast.LENGTH_SHORT).show();
            return "";
        }
        try {
            Date date = dateFormat.parse(input);
            if (date != null) {
                return input;
            } else {
                Toast.makeText(this, "Formato inválido", Toast.LENGTH_SHORT).show();
                return "";
            }
        } catch (ParseException e) {
            Toast.makeText(this, "Formato inválido", Toast.LENGTH_SHORT).show();
        }
        return "null";
    }

    private void displayIconList() {
        UserIconAdapter adapter = new UserIconAdapter(getApplicationContext(), iconList);
        adapter.setDropDownViewResource(R.layout.user_icon_list);
        spIcon.setAdapter(adapter);
    }

    private String getSelectedIconName() {
        int selectedPosition = spIcon.getSelectedItemPosition();
        if (selectedPosition > 0 && selectedPosition <= iconList.size()) {
            return iconList.get(selectedPosition - 1);
        }
        return "";
    }
}
