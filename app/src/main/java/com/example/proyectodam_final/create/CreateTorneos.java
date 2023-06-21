package com.example.proyectodam_final.create;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.proyectodam_final.R;
import com.example.proyectodam_final.callbacks.TournamentTitleCheckCallback;
import com.example.proyectodam_final.delete.DeleteTorneo;
import com.example.proyectodam_final.pojo.Tournament;
import com.google.firebase.database.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CreateTorneos extends AppCompatActivity {

    private EditText edtJugadores, edtPrecio, edtTitulo, edtFecha, edtRecompensa;
    private Spinner spLugar, spPlat, spHora;
    private Button btAdd;
    private Calendar date;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_torneos);
        elementMatcher();
        spinnerInfoLugar();
        spinnerInfoHora();
        spinnerInfoPlat();
        edtFecha.setOnClickListener(v -> datePicker());
        btAdd.setOnClickListener(v -> addTournament());
    }

    private void elementMatcher() {
        edtJugadores = findViewById(R.id.edtJugadoresTot);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtTitulo = findViewById(R.id.edtTitulo);
        edtFecha = findViewById(R.id.edtFecha);
        edtFecha.setFocusable(false);
        edtRecompensa = findViewById(R.id.edtRecompensa);
        spLugar = findViewById(R.id.spLugar);
        spPlat = findViewById(R.id.spPlataforma);
        spHora = findViewById(R.id.spHora);
        btAdd = findViewById(R.id.btnAgregar);
    }

    private void spinnerInfoLugar() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lugar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLugar.setAdapter(adapter);
    }

    private void spinnerInfoHora() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.horas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spHora.setAdapter(adapter);
    }

    private void spinnerInfoPlat() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.plataformas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPlat.setAdapter(adapter);
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
            edtFecha.setText(formattedDate);
        }, year, month, day);
        dpDialog.show();
    }

    private String validateDate() {
        String input = edtFecha.getText().toString().trim();
        if (TextUtils.isEmpty(input)) {
            Toast.makeText(this, "Escribe una fecha válida", Toast.LENGTH_SHORT).show();
            return "";
        }
        try {
            Date selectedDate = dateFormat.parse(input);
            Date currentDate = new Date(); // Fecha actual

            // Comparar las fechas
            if (selectedDate != null && !selectedDate.before(currentDate)) {
                return input;
            } else {
                Toast.makeText(this, "La fecha debe ser mayor o igual que la fecha actual", Toast.LENGTH_SHORT).show();
                return "";
            }
        } catch (ParseException e) {
            Toast.makeText(this, "Formato inválido", Toast.LENGTH_SHORT).show();
        }
        return "null";
    }

    private void addTournament() {
        String titulo = edtTitulo.getText().toString();
        String fechaValida = validateDate();

        if (!valueChecker() || edtJugadores.getText().toString().isEmpty()
                || edtPrecio.getText().toString().isEmpty()
                || edtTitulo.getText().toString().isEmpty()
                || edtFecha.getText().toString().isEmpty()
                || fechaValida.isEmpty()) {
            Toast.makeText(CreateTorneos.this, "Debes rellenar todos los campos y proporcionar una fecha válida", Toast.LENGTH_SHORT).show();
        } else {
            tournamentTitleChecker(titulo, result -> {
                if (result == 0) {
                    Toast.makeText(CreateTorneos.this, "El título del torneo ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    Tournament tournament = fetchTournamentData();
                    FirebaseDatabase.getInstance().getReference().child("tournaments").child(titulo).setValue(tournament);
                    Toast.makeText(CreateTorneos.this, "Torneo creado!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        }
    }
    private void tournamentTitleChecker(String tournamentTitle, final TournamentTitleCheckCallback callback) {
        DatabaseReference tournamentsRef = FirebaseDatabase.getInstance().getReference().child("tournaments");
        tournamentsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean titleExists = false;
                for (DataSnapshot tournamentSnapshot : dataSnapshot.getChildren()) {
                    String dbTournamentTitle = tournamentSnapshot.getKey();
                    if (Objects.requireNonNull(dbTournamentTitle).equals(tournamentTitle)) {
                        titleExists = true;
                        break;
                    }
                }
                if (titleExists) {
                    callback.onTournamentTitleChecked(0);
                } else {
                    callback.onTournamentTitleChecked(1);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CreateTorneos.this, "Ocurrió un error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private boolean valueChecker() {
        return !edtJugadores.getText().toString().isEmpty()
                && !edtPrecio.getText().toString().isEmpty()
                && !edtTitulo.getText().toString().isEmpty()
                && !edtFecha.getText().toString().isEmpty()
                && !edtRecompensa.getText().toString().isEmpty();
    }

    private Tournament fetchTournamentData() {
        String title = edtTitulo.getText().toString();
        String date = validateDate();
        String hour = spHora.getSelectedItem().toString();
        String location = spLugar.getSelectedItem().toString();
        double priceEntry = Double.parseDouble(edtPrecio.getText().toString());
        double reward = Double.parseDouble(edtRecompensa.getText().toString());
        String platform = spPlat.getSelectedItem().toString();
        int totalPlayers = Integer.parseInt(edtJugadores.getText().toString());

        return new Tournament(title, date, hour, location, priceEntry, reward, platform, totalPlayers);
    }

}