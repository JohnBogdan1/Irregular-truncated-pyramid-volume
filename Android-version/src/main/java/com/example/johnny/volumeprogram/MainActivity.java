package com.example.johnny.volumeprogram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v) {

        double la = 0, lb = 0, lc = 0, ld = 0, lA = 0, lB = 0, lC = 0, lD = 0, ldiagJos = 0, ldiagSus = 0, lh = 0, lH = 0;

        EditText a = (EditText) findViewById(R.id.a);
        EditText b = (EditText) findViewById(R.id.b);
        EditText c = (EditText) findViewById(R.id.c);
        EditText d = (EditText) findViewById(R.id.d);

        EditText A = (EditText) findViewById(R.id.A);
        EditText B = (EditText) findViewById(R.id.B);
        EditText C = (EditText) findViewById(R.id.C);
        EditText D = (EditText) findViewById(R.id.D);

        EditText diagJos = (EditText) findViewById(R.id.diagJos);
        EditText diagSus = (EditText) findViewById(R.id.diagSus);

        EditText h = (EditText) findViewById(R.id.h);
        EditText H = (EditText) findViewById(R.id.H);

        TextView result = (TextView) findViewById(R.id.volume);

        try {
            la = Double.parseDouble(a.getText().toString().trim());
            lb = Double.parseDouble(b.getText().toString().trim());
            lc = Double.parseDouble(c.getText().toString().trim());
            ld = Double.parseDouble(d.getText().toString().trim());

            lA = Double.parseDouble(A.getText().toString().trim());
            lB = Double.parseDouble(B.getText().toString().trim());
            lC = Double.parseDouble(C.getText().toString().trim());
            lD = Double.parseDouble(D.getText().toString().trim());

            ldiagJos = Double.parseDouble(diagJos.getText().toString().trim());
            ldiagSus = Double.parseDouble(diagSus.getText().toString().trim());

            lh = Double.parseDouble(h.getText().toString().trim());
            lH = Double.parseDouble(H.getText().toString().trim());
        } catch (Exception e) {
            result.setText("Nu ati adaugat toate valorile.");
            return;
        }

        double alphaJos, gamaJos, alphaSus, gamaSus;

        alphaSus = (double) Math.acos((la * la + ld * ld - ldiagSus * ldiagSus) / (2 * la * ld));
        gamaSus = (double) Math.acos((lb * lb + lc * lc - ldiagSus * ldiagSus) / (2 * lb * lc));

        alphaJos = (double) Math.acos((lA * lA + lD * lD - ldiagJos * ldiagJos) / (2 * lA * lD));
        gamaJos = (double) Math.acos((lB * lB + lC * lC - ldiagJos * ldiagJos) / (2 * lB * lC));

        double semiperimeterJos, semiperimeterSus;

        semiperimeterSus = (double) (la + lb + lc + ld) / 2;
        semiperimeterJos = (double) (lA + lB + lC + lD) / 2;

        double ariaJos, ariaSus;

        ariaSus = (double) Math.sqrt((semiperimeterSus - la) * (semiperimeterSus - lb)
                * (semiperimeterSus - lc) * (semiperimeterSus - ld)
                - la * lb * lc * ld * Math.pow(Math.cos((double) (alphaSus + gamaSus) / 2), 2));

        ariaJos = (double) Math.sqrt((semiperimeterJos - lA) * (semiperimeterJos - lB) * (semiperimeterJos - lC)
                * (semiperimeterJos - lD)
                - lA * lB * lC * lD * Math.pow(Math.cos((double) (alphaJos + gamaJos) / 2), 2));

        double volumeTrunchi = 0, volumePyramid = 0, totalVolume = 0;

        // Calculez volumele corpurilor
        volumeTrunchi = (double) ((lH * (ariaJos + (double) Math.sqrt(ariaJos * ariaSus) + ariaSus)) * 1
                / 3);
        volumePyramid = (double) (ariaSus * lh) * 1 / 3;

        // Volumul final
        totalVolume = volumeTrunchi + volumePyramid;
        if (String.valueOf(totalVolume) == "NaN")
            result.setText("Ati adaugat o valoare nula.");
        else
            result.setText(String.valueOf(totalVolume));
    }
}
