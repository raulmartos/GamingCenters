package com.example.proyectodam_final.create;


import android.app.DatePickerDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView;

import com.example.proyectodam_final.R;
import com.example.proyectodam_final.pojo.Booking;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class CreateBooking extends AppCompatActivity {

    private Button btnAddBooking;
    private Spinner spSilla;
    private EditText edtCliente, edtFecha;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private Calendar date;
    final List<String> sillaList = Arrays.asList("Selecciona una reserva: ", "Fila 1 - Silla 1", "Fila 1 - Silla 2", "Fila 1 - Silla 3", "Fila 1 - Silla 4 ",
            "Fila 2 - Silla 1", "Fila 2 - Silla 2", "Fila 2 - Silla 3", "Fila 2 - Silla 4", "Fila 2 - Silla 4");
    private boolean isFirstSelection = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        elementMatcher();
        edtFecha.setOnClickListener(v -> datePicker());
        spinnerInfo();
    }

    private void elementMatcher() {
        spSilla = findViewById(R.id.spSilla);
        edtCliente = findViewById(R.id.edtCliente);
        edtFecha = findViewById(R.id.edtFecha);
    }

    private void addBooking() {
        FirebaseDatabase.getInstance().getReference().child("bookings").child(spinnerValue()).setValue(fetchBookingData());
    }

    private Booking fetchBookingData() {
        String silla = spinnerValue();
        String cliente = edtCliente.getText().toString();
        String date = validateDate();
        long createdAt = getCurrentTime();
        return new Booking(silla, cliente, date);
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

    private void spinnerInfo() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sillaList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spSilla.setAdapter(adapter);
        spSilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isFirstSelection) {
                    isFirstSelection = false;
                } else if (position == 0) {
                    spSilla.setSelection(1);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spSilla.setSelection(0);
    }

    private String spinnerValue() {
        int position = spSilla.getSelectedItemPosition();
        return spSilla.getItemAtPosition(position).toString();
    }

    private long getCurrentTime() {
        return Instant.now().getEpochSecond();
    }

}