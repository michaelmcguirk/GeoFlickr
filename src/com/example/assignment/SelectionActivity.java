package com.example.assignment;

import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionActivity extends ActionBarActivity
{
	List<Product> products = new ArrayList<Product>();
	List<Product> shoppingList = new ArrayList<Product>();

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		User u = (User)getIntent().getExtras().get("user");
		System.out.println("Name: " + u.getName());
		System.out.println("Age: " + u.getAge());
		System.out.println("Budget: " + u.getBudget());
		System.out.println("Email: " + u.getEmailAddr());
		System.out.println("JobTitle: " + u.getJobTitle());
		setContentView(R.layout.activity_selection);
		createList();
		

		ArrayAdapter<Product> adapter = new ProductListAdapter();
		ListView list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				int toastDuration = 5;
				Product selectedProduct = products.get(position);
				Toast.makeText(getApplicationContext(), selectedProduct.getName(), toastDuration).show();
				addToShoppingList(selectedProduct);
			}

		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void createList()
	{
		products.add(new Product("Beans", "Tin of Beans", 0.59, 0));
		products.add(new Product("Eggs", "Six Eggs", 1.89, 0));
		products.add(new Product("Bacon", "300g of Bacon", 1.99, 0));
		products.add(new Product("Apples", "1kg Apples", 1.49, 0));
		products.add(new Product("Bread", "Brown Sliced Pan", 1.25, 0));
	}
	
	private void addToShoppingList(Product product)
	{
		shoppingList.add(product);
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

	private class ProductListAdapter extends ArrayAdapter<Product>
	{
		public ProductListAdapter()
		{
			super(SelectionActivity.this, R.layout.row, products);
			// TODO Auto-generated constructor stub
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			// TODO Auto-generated method stub
			View view = getLayoutInflater()
					.inflate(R.layout.row, parent, false);
			Product currentProduct = products.get(position);

			TextView nameText = (TextView) view.findViewById(R.id.item_name);
			nameText.setText(currentProduct.getName());

			TextView descriptionText = (TextView) view
					.findViewById(R.id.item_desc);
			descriptionText.setText(currentProduct.getDescription());

			TextView price = (TextView) view.findViewById(R.id.item_price);
			price.setText("" + currentProduct.getPrice());

			return view;
		}

	}
}
