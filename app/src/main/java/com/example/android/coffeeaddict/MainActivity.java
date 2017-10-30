package com.example.android.coffeeaddict;

import android.annotation.TargetApi;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.lang.String;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int Quantity = 0;
//    int numCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        display(Quantity);
        displayPrice(Quantity*3);
    }

    public void increaseOrder(View view) {
        Quantity += 1;
        display(Quantity);
    }

    public void decreaseOrder(View view) {
        if (Quantity>0) {
            Quantity -= 1;
            display (Quantity);
        }
        System.out.println("Cannot be less than 0");

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }


    @TargetApi(24)
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
}
