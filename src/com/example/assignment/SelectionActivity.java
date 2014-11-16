package com.example.assignment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectionActivity extends ActionBarActivity
{
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<Product> shoppingList = new ArrayList<Product>();
	User u;
	double userBudget;
	Button finished;
	TextView totalBudget, totalSpend;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		u = (User)getIntent().getExtras().get("user");
		userBudget = u.getBudget();
		System.out.println("Name: " + u.getName());
		System.out.println("Age: " + u.getAge());
		System.out.println("Budget: " + u.getBudget());
		System.out.println("Email: " + u.getEmailAddr());
		System.out.println("JobTitle: " + u.getJobTitle());
		setContentView(R.layout.activity_selection);
		createList();
		
		finished = (Button) findViewById(R.id.finished_button);
		totalBudget = (TextView) findViewById(R.id.tvBudgetValue);
		totalSpend = (TextView) findViewById(R.id.tvSpendValue);
		
		totalBudget.setText("€" + u.getBudget());
		
	       finished.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(SelectionActivity.this, ShoppingListActivity.class);
					Bundle sListBundle = new Bundle();
					sListBundle.putSerializable("sListB", shoppingList);
					intent.putExtras(sListBundle);
					startActivity(intent);
				}
			});
		

		ArrayAdapter<Product> adapter = new ProductListAdapter();
		ListView list = (ListView) findViewById(android.R.id.list);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				int toastDuration = 2;
				Product selectedProduct = products.get(position);
				boolean inList = shoppingList.contains(selectedProduct);
				Toast.makeText(getApplicationContext(), selectedProduct.getName(), toastDuration).show();
				
				if(!inList)
				{
					int greenBGColor = getResources().getColor(R.color.green_background);
					view.setBackgroundColor(greenBGColor);
					checkBudget(selectedProduct);
					
				}
				else if(inList)
				{
					int whiteBGColor =  getResources().getColor(R.color.white);
					view.setBackgroundColor(whiteBGColor);
					shoppingList.remove(selectedProduct);
				}
				
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
		products.add(new Product("Bananas", "1kg bananas", 1.25, 0));
		products.add(new Product("Pork Chops", "500g Pork Chops", 1.25, 0));
		products.add(new Product("Pasta", "500g Spaghetti", 1.25, 0));
		products.add(new Product("Suagr", "Brown Sugar 1KG", 1.25, 0));
		products.add(new Product("Sugar", "White sugar 1KG", 1.25, 0));
		products.add(new Product("Bread", "White Sliced Pan", 1.25, 0));
		products.add(new Product("Potatoes", "2kg Potataoes", 1.25, 0));
		products.add(new Product("Lettace", " 1 Head", 1.25, 0));
		products.add(new Product("Pizza", "Frozen Pizza", 1.25, 0));
		products.add(new Product("Olive Oil", "1 Litre", 1.25, 0));
		products.add(new Product("Frozen Chips", "2KG Oven Chips", 1.25, 0));
	}
	
	private void addToShoppingList(Product product)
	{
		shoppingList.add(product);
	}
	
	private void checkBudget(Product selectedProduct)
	{
		double totalSpend = 0;
		double selectedPrice = selectedProduct.getPrice();
		if(totalSpend + selectedPrice > userBudget)
		{
			Toast.makeText(getApplicationContext(), "Budget exceeded!", 2).show();
		}
		else
		{
			addToShoppingList(selectedProduct);
		}
		
		for(Product p: shoppingList)
		{
			totalSpend += p.getPrice();
		}
		this.totalSpend.setText("€" + totalSpend);
		System.out.println(totalSpend);
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
			boolean inList = shoppingList.contains(currentProduct);
			if(inList)
			{
				System.out.println("Is in list? : " + inList);
				int greenBGColor = getResources().getColor(R.color.green_background);
				view.setBackgroundColor(greenBGColor);
			}

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
