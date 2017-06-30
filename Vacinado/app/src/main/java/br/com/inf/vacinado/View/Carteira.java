package br.com.inf.vacinado.View;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import br.com.inf.vacinado.Controller.NavigationDrawerAdapter;
import br.com.inf.vacinado.Model.Vacina;
import br.com.inf.vacinado.R;

public class Carteira extends AppCompatActivity {

    static private FirebaseAuth mFirebaseAuth = FirebaseAuth.getInstance();
    static private FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
    private String mUserId = mFirebaseUser.getUid();
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference ref = mDatabase.child("users").child(mUserId).child("vacinas");
    private TextView texto;
    private Toast toast;
    private long lastBackPressTime = 0;
    private Context mContext;


    String TITLES[] = {"Home", "Events", "Mail", "Shop", "Travel"};
    int ICONS[] = {R.drawable.ic_home, R.drawable.ic_events, R.drawable.ic_mail, R.drawable.ic_shop, R.drawable.ic_travel};

    //Similarly we Create a String Resource for the name and email in the header view
    //And we also create a int resource for profile picture in the header view

    String NAME = "Nome";
    String EMAIL = "Email";
    int PROFILE = R.drawable.aka;


    private Toolbar toolbar;                              // Declaring the Toolbar Object

    RecyclerView mRecyclerView;                           // Declaring RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declaring Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declaring Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declaring DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declaring Action Bar Drawer Toggle


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        mContext = getApplicationContext();

        Intent it = getIntent();
        boolean userOffline = it.getBooleanExtra("Modo offline", false);

        if (userOffline) {
            Toast.makeText(getApplicationContext(), R.string.user_offline, Toast.LENGTH_LONG).show();
        }

        final CardView button = (CardView) findViewById(R.id.card_view1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                vacinaInfo();
            }
        });

        texto = (TextView) findViewById(R.id.vacina_nome);

        final FloatingActionButton addVacina = (FloatingActionButton) findViewById(R.id.button_nova_vacina);
        addVacina.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                addVacina();
            }
        });

        // Configurando a ListView
        //final ListView listView = (ListView) findViewById(R.id.listView);
        //final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1);
        //listView.setAdapter(adapter);







    /* Assinging the toolbar object ot the view
    and setting the the Action bar to our toolbar
     */
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View

        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new NavigationDrawerAdapter(TITLES, ICONS, NAME, EMAIL, PROFILE);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
        // And passing the titles,icons,header view name, header view email,
        // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView

        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }


        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toast = Toast.makeText(this, R.string.fechar_app, Toast.LENGTH_LONG);
            toast.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toast != null) {
                toast.cancel();
            }
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
                    Vacina vac = vacSnapshot.getValue(Vacina.class);
                    texto.setText(vac.getNome());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void vacinaInfo() {
        Intent intent = new Intent(Carteira.this, VacinaInfo.class);
        startActivity(intent);
    }

    private void addVacina() {
        Intent intent = new Intent(Carteira.this, AdicionarVacina.class);
        startActivity(intent);
    }

    public Context contextoAplicacao() {
        return mContext;
    }
}
