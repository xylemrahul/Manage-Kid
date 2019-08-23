package com.example.milk.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;
import androidx.annotation.Nullable;

import com.example.milk.R;
import com.example.milk.utils.AppUtilities;
import com.google.android.gms.vision.barcode.Barcode;
import java.util.List;
import info.androidhive.barcode.BarcodeReader;

public class BarCodeActivity extends BaseActivity implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;
    private boolean flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_barcode);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner_fragment);
    }

    @Override
    public void onScanned(Barcode barcode) {

        Log.e("barcode value", "onScanned: " + barcode.displayValue);
        barcodeReader.playBeep();
        if(!flag) {
            flag = true;
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(AppUtilities.barcode_value, Long.valueOf(barcode.displayValue));
            startActivity(intent);
        }
    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
        finish();
    }
}
