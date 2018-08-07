package com.abhinavsingh153.project.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.text.NumberFormat;

import static android.R.attr.name;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=0;




    /**
     * This method is called when the order button is clicked.
     */



    public void submitOrder(View view) {

       // Log.v("MainActivity", "Has whipped cream" + hasWhippedCream );



        EditText editText = (EditText) findViewById( R.id.edit_text);
        String name = editText.getText().toString();   //chaining method calls.
        //the return data type of getText() is "Editable".
        //the toString() is called on the Editable object which returns a String value and store it in the name variable.

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_check_box);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();


        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_check_box);
        boolean hasChocolate = chocolateCheckBox.isChecked();


        int price = calculatePrice(hasWhippedCream , hasChocolate);



       String priceMessage = createOrderSummary(name , price, hasWhippedCream , hasChocolate);
      //  String priceMessage="Total: $"+price+"\nThankYou :)";
        Intent intent= new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order summary for " + name);
        intent.putExtra(Intent.EXTRA_TEXT ,priceMessage );
        if (intent.resolveActivity(getPackageManager()) !=null);
        startActivity(intent);


        // in this createOrederSummary method will be calleds 1st and the int value
        // which is returned in calCulate Price method gets stored in the priceOfCoffee variable and then the displayMessage method is called.

    }

    /**
     * Calculates the price of the order.
     *
     *  the number of cups of coffee ordered
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate)
    {// base price per cup of coffee
        int price=5;

        // Add $1 to the base price
        if (addWhippedCream)
        {
            price += 1;
        }

        //Add $2 to the base price
        if(addChocolate)
        {
            price +=  2;
        }

        return quantity* price;

    }

    private String createOrderSummary(String personName, int priceOfCoffee, boolean addWhippedCream, boolean addChocolate) // priceOfCoffee variable is used to store the onteger value of price of a cup
    {


        String priceMessage="Name: " + personName;
        priceMessage += "\nWhipped Cream added? : " + addWhippedCream;
        priceMessage += "\nChocolate Added? : " + addChocolate;
        priceMessage += "\nQuantity: "+ quantity;
        priceMessage += "\nTotal: $"+priceOfCoffee+"\nThankYou :)";
        return priceMessage;

    }

    public void increment(View view){// calling display method
         if (quantity == 100)
         {
             Toast.makeText(this, "You cannot have more than 100 cups of coffee ", Toast.LENGTH_SHORT).show();
             return;
         }

         quantity += 1;
        display(quantity);


    }

    public void decrement(View view) {
        if(quantity == 1)
        {
            Toast.makeText(this, "You cannot have less than 1 cup of coffee ", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (quantity==0)
        {
            Toast.makeText(this, "You cannot have less than 0 cups of coffee ", Toast.LENGTH_SHORT).show();
            return;

        }

        quantity = quantity - 1;
        display(quantity); //calling display method

    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
  //  private void displayPrice(int number) {
  //      TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
   //     priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
   // }



    //Reset method
    /** public void reset(View view)
     {
         quantity=0;
         price=0;
         display(quantity);
         displayPrice(price);

     }
     **/
}
