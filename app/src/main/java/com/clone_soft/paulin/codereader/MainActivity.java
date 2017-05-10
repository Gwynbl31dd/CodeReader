package com.clone_soft.paulin.codereader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
// Import Xzing library
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

/**
 * @author Anthony Paulin <paulin.anthony@gmail.com>
 * @version 0.1
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mybutton = (Button) findViewById(R.id.scan_button);
        mybutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.scan_button){
            // Start the scanner on click
            new IntentIntegrator(this).initiateScan();
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // Use the class IntentIntegrator and its fonction parseActivityResult to pars the request code
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            // Get the barcode result
            String scanContent = scanningResult.getContents();
            // Get the barcode format
            String scanFormat = scanningResult.getFormatName();
            TextView scan_format = (TextView) findViewById(R.id.scan_format);
            TextView scan_content = (TextView) findViewById(R.id.scan_content);
            // Send the result to the textviews
            scan_format.setText("FORMAT: " + scanFormat);
            scan_content.setText("CONTENT: " + scanContent);
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No data !", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
