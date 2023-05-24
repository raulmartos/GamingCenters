package com.example.proyectodam_final.create;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.proyectodam_final.BookingApiService;
import com.example.proyectodam_final.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateBooking extends AppCompatActivity {

    private BookingApiService apiService;
    Button btnAddBooking ;
    private EditText edtSeat;
    private EditText edtClient;
    private EditText edtDateBooking;
    private Spinner spnBookingStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.calov.click/createBooking/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btnAddBooking  = findViewById(R.id.btnAgregarRes);
        apiService = retrofit.create(BookingApiService.class);
        edtSeat = findViewById(R.id.edtSilla);
        edtClient = findViewById(R.id.edtCliente);
        edtDateBooking = findViewById(R.id.edtFecha);
        spnBookingStatus = findViewById(R.id.spEstado);

        spnBookingStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position) != null) { // Verificar si la variable no es null
                    String selectedValue = parent.getItemAtPosition(position).toString();
                    // Aquí puedes usar el valor seleccionado como sea necesario
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Manejar el caso en que no se selecciona ningún elemento
            }
        });


        btnAddBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                //System.out.println("Entro");
                String edtSillaInput = edtSeat.getText().toString();
                String edtUserInput = edtClient.getText().toString();
                String edtDateInput = edtDateBooking.getText().toString();
                String spStatusInput = spnBookingStatus.getSelectedItem().toString();

                Call<CreateBooking> call = apiService.createBooking(
                        edtSillaInput,
                        edtUserInput,
                        edtDateInput,
                        spStatusInput
                );

                System.out.println(edtSillaInput);
                System.out.println(edtUserInput);
                System.out.println(edtDateInput);
                System.out.println(spStatusInput);

                call.enqueue(new Callback<CreateBooking>() {
                    public void onResponse(Call<CreateBooking> call, Response<CreateBooking> response) {
                        if (response.isSuccessful()) {
                            CreateBooking bookingReturn = response.body();
                            // Haz lo que necesites con la respuesta
                        } else {
                            // Mostrar error si la respuesta no fue exitosa
                        }
                    }

                    public void onFailure(Call<CreateBooking> call, Throwable t) {
                        // Mostrar error si la petición falla
                    }
                });

            }
        });

    }
}

