package com.example.android.coffeeaddict;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.String;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int Quantity = 98;
    int Price = 0;

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String addCustomerName) {
        if (addCustomerName != null) {
            Toast.makeText(MainActivity.this, "Enter name!", Toast.LENGTH_LONG).show();
        }
        String priceMessage = "Name: " + addCustomerName;
        priceMessage += "\nWhipped Cream: " + addWhippedCream;
        priceMessage += "\nChocolate: " + addChocolate;
        priceMessage += "\nQuantity: " + Quantity;
        priceMessage += "\nTotal: $" + price;
        priceMessage += "\nThank you! Come again!";
        return priceMessage;
    }


    public void decreaseOrder (View view) {
        if (Quantity<1) {
            Toast.makeText(MainActivity.this, "Cannot have less than 1 cup!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        Quantity -= 1;
        displayQuantity(Quantity);

    }

    public void increaseOrder(View view) {
        if(Quantity>=100) {
            Toast.makeText(MainActivity.this, "Cannot have more than 100 cup!",
                    Toast.LENGTH_LONG).show();
            return;

        }
        Quantity += 1;
        displayQuantity(Quantity);


    }

    private int calculatePrice (boolean addWhippedCream, boolean addChocolate) {

        int plainCoffee = 4;
        if(addWhippedCream) {
            plainCoffee += 1;
        }
        if(addChocolate) {
            plainCoffee += 2;
        }
        return plainCoffee * Quantity;
    }

        @TargetApi(24)
    public void submitOrder(View view) {
            // get whipped cream checkbox from xml
        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
        Boolean hasWhippedCream = whippedCream.isChecked();

            // get chocolate checkbox from xml
        CheckBox chocolateTopping = (CheckBox) findViewById(R.id.chocolate_topping);
        Boolean hasChocolate = chocolateTopping.isChecked();

            // get input and display it in the order summary section
        EditText customerNameInput = (EditText) findViewById(R.id.client_name_input);
        String customerName = customerNameInput.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, customerName);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Addict for " + customerName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
          if (intent.resolveActivity(getPackageManager()) != null) {
              startActivity(intent);
            }
        displayMessage(priceMessage);
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText(" " + number);
    }

    //*******************************Trial and error below this line ***************************************

    /**
     * This method is called when the order button is clicked.
     */
//    @TargetApi(24)
//    public void submitOrder(View view) {
////        totalPrice = Quantity*Price;
////        NumberFormat.getCurrencyInstance().format(totalPrice);
//        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
//        Boolean hasWhippedCream = whippedCream.isChecked();
//        Log.i("MainActivity", "value fo hasWhippedCream: " + hasWhippedCream);
//
////        if (hasWhippedCream = true) {
////            whippedCream.setChecked(false);
////        } else {
////            displayMessage("Total: " + "$" + totalPrice + ". You ordered " + Quantity + " cups of coffee");
////            displayOrderSummary("Client: Dan the Man." + "\nToppings: "+ "\nYour order of " + Quantity + " cups of coffee is on it's way! \nYour total is $" + calculatePrice() );
////        }
//
//        displayMessage("Total: " + "$" + totalPrice + ". You ordered " + Quantity + " cups of coffee");
//        displayOrderSummary("Client: Dan the Man." + "\nToppings: "+ "\nYour order of " + Quantity + " cups of coffee is on it's way! \nYour total is $" + calculatePrice() );
////        displayPrice2(Quantity*3);
//    }
//
//    private int calculatePrice() {
//        return Quantity * Price;
//    }
//
//    public void increaseOrder(View view) {
//        Quantity += 1;
//        displayQuantity(Quantity);
//        totalPrice = Quantity * Price;
//        displayOrderSummary("Cups of cup: " + Quantity + " cups of coffee! \nTotal: $" + totalPrice );
////        displayQuantity2(Quantity);
//    }
//
//    public void decreaseOrder(View view) {
//        if (Quantity>0) {
//            // added conditional in order to have screen update as the order is being made
//            CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
//            Boolean hasWhippedCream = whippedCream.isChecked();
//            if (hasWhippedCream == true) {
//                Quantity -= 1;
//                displayQuantity (Quantity);
//                totalPrice = Quantity * Price;
//                displayOrderSummary("Cups of cup: " + Quantity + " cups of coffee! \nTotal: $" + totalPrice + "\nToppings: Whipped Cream");
//
//            }
//            Quantity -= 1;
//            displayQuantity (Quantity);
//            totalPrice = Quantity * Price;
//            displayOrderSummary("Cups of cup: " + Quantity + " cups of coffee! \nTotal: $" + totalPrice );
////            displayQuantity2(Quantity);
//        }
//        System.out.println("Cannot be less than 0");
//
//    }
//
//    /**
//     * This method displays the given quantity value on the screen.
//     */
//    private void displayQuantity(int number) {
//        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
//        quantityTextView.setText(" " + number);
//    }
//
////    private void displayQuantity2(int number) {
////        TextView quantityEditText = (TextView) findViewById(R.id.quantity_editText_view);;
////        quantityEditText.setText(" " + number);
////    }
//
//
//    @TargetApi(24)
//    private void displayPrice(int number) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
//    }
//
//    /**
//     * This method displays the given text on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
//        priceTextView.setText(message);
//    }
//
//    private void displayOrderSummary(String summary) {
//        TextView orderSummary = (TextView) findViewById(R.id.order_summary);
//        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream);
//        Boolean hasWhippedCream = whippedCream.isChecked();
//        if (hasWhippedCream == true) {
//            summary += "\nToppings: Whipped Cream";
//            orderSummary.setText(summary);
//        }
//        orderSummary.setText(summary);
//    }
////    @TargetApi(24)
////    private void displayPrice2(int number) {
////        EditText priceTextView2 = (EditText) findViewById(R.id.price_text_view2);
////        priceTextView2.setText(NumberFormat.getCurrencyInstance().format(number));
////    }
}
