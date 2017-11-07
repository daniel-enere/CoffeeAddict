package com.example.android.coffeeaddict;

import android.annotation.TargetApi;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.lang.String;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int Quantity = 0;
    int Price = 3;
    int totalPrice;
//    int numCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    @TargetApi(24)
    public void submitOrder(View view) {
        totalPrice = Quantity*Price;
        NumberFormat.getCurrencyInstance().format(totalPrice);
        displayMessage("Total: " + "$" + totalPrice + ". You ordered " + Quantity + " cups of coffee");
        displayOrderSummary("Your order of " + Quantity + " cups of coffee is on it's way! Your total is $" + totalPrice );
//        displayPrice2(Quantity*3);
    }

    public void increaseOrder(View view) {
        Quantity += 1;
        displayQuantity(Quantity);
        totalPrice = Quantity * Price;
        displayOrderSummary("Cups of cup: " + Quantity + " cups of coffee! \nTotal: $" + totalPrice );
//        displayQuantity2(Quantity);
    }

    public void decreaseOrder(View view) {
        if (Quantity>0) {
            Quantity -= 1;
            displayQuantity (Quantity);
            totalPrice = Quantity * Price;
            displayOrderSummary("Cups of cup: " + Quantity + " cups of coffee! \nTotal: $" + totalPrice );
//            displayQuantity2(Quantity);
        }
        System.out.println("Cannot be less than 0");

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }

//    private void displayQuantity2(int number) {
//        TextView quantityEditText = (TextView) findViewById(R.id.quantity_editText_view);;
//        quantityEditText.setText(" " + number);
//    }


    @TargetApi(24)
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private void displayOrderSummary(String summary) {
        TextView orderSummary = (TextView) findViewById(R.id.order_summary);
        orderSummary.setText(summary);
    }
//    @TargetApi(24)
//    private void displayPrice2(int number) {
//        EditText priceTextView2 = (EditText) findViewById(R.id.price_text_view2);
//        priceTextView2.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
}
