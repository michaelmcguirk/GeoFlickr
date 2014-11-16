package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.text.InputFilter.LengthFilter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ShoppingListActivity extends ActionBarActivity
{
	Bundle b;
	ArrayList<Product> products;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shoppinglist);
		
		//Ref: Source: http://scriptedpapers.com/2013/11/26/android-pass-arraylist-of-custom-class-via-intent/
		b = getIntent().getExtras();
		products = (ArrayList<Product>) b.getSerializable("sListB");
		//End Ref.

		TableLayout t = (TableLayout) findViewById(R.id.table1);

		for (Product p : products)
		{
			TableRow tr = new TableRow(this);
			String pName = p.getName();
			String pPrice = String.valueOf(p.getPrice());
			String pQty = String.valueOf(p.getPrice());

			// TextView name = (TextView) findViewById(R.id.tvProductName);

			TextView name = new TextView(this);
			name.setText(pName);
			name.setPadding(10, 10, 20, 10);
			tr.addView(name);

			// TextView price = (TextView) findViewById(R.id.tvProductPrice);
			TextView price = new TextView(this);
			price.setText(pPrice);
			price.setPadding(20, 10, 20, 10);
			tr.addView(price);

			// TextView qty = (TextView) findViewById(R.id.tvQty);
			TextView qty = new TextView(this);
			qty.setText(pQty);
			qty.setPadding(20, 10, 20, 10);
			tr.addView(qty);

			t.addView(tr);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
