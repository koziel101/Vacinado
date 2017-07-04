package br.com.inf.vacinado.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
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

import java.util.ArrayList;
import java.util.List;

import br.com.inf.vacinado.DAO.UsuarioDAO;
import br.com.inf.vacinado.Model.Usuario;
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
    private Toolbar toolbar;
    private NavigationView navigationView;
    List<Vacina> vacinas = new ArrayList<>();
    private DrawerLayout drawerLayout;
    static private FirebaseAuth.AuthStateListener mAuthListener;
    private FloatingActionButton fab;
    VacinaAdapter adapter;
    RecyclerView recycleVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carteira);

        //fab = (FloatingActionButton) findViewById(R.id.btn_add_vacina);

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addVacina();
            }
        });*/

        //recycle lista de Vacinas
        Vacina vacina = new Vacina("Minha Vacina", 2, "odiei");
        vacinas.add(vacina);
        recycleVacina = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycleVacina.setLayoutManager(llm);
        adapter = new VacinaAdapter(vacinas);
        recycleVacina.setAdapter(adapter);
        //termina recycle

        Intent it = getIntent();
        boolean userOffline = it.getBooleanExtra("Modo offline", false);

        if (userOffline) {
            Toast.makeText(getApplicationContext(), R.string.user_offline, Toast.LENGTH_LONG).show();
        }

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
            }
        };

        texto = (TextView) findViewById(R.id.vacina_nome);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.new_vacina:
                        addVacina();
                        return true;
                    case R.id.perfil:
                        startEditarCadastro();
                        return true;
                    case R.id.notificacao:
                        Toast.makeText(getApplicationContext(), "Não implementado ainda", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.log_out:
                        if (mAuthListener != null) {
                            mFirebaseAuth.signOut();
                            finalizarSessaoUser();
                        }
                        return true;
                    default:
                        return true;
                }
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
        mFirebaseAuth.addAuthStateListener(mAuthListener);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              // for (DataSnapshot vacSnapshot : dataSnapshot.getChildren()) {
             //       Vacina vac = vacSnapshot.getValue(Vacina.class);
             //       vacinas.add(vac);
                }
           // }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
       // setRecycleVacina(vacinas);
    }

    private void vacinaInfo() {
        Intent intent = new Intent(Carteira.this, VacinaInfo.class);
        startActivity(intent);
    }

    private void addVacina() {
        Intent intent = new Intent(Carteira.this, AdicionarVacina.class);
        startActivity(intent);
    }

    private void finalizarSessaoUser() {
        Intent intent = new Intent(Carteira.this, Login.class);
        startActivity(intent);
    }

    private void startEditarCadastro() {
        UsuarioDAO user = new UsuarioDAO();
        Usuario usuario = user.getUserById(mAuthListener);
        getIntent().putExtra("usuario", usuario);
        Intent intent = new Intent(Carteira.this, EditarCadastro.class);
        startActivity(intent);
    }

    private void setRecycleVacina(List listaVacinas){
        //instacia recycleView e define o prosicionamento dos intens
        recycleVacina = (RecyclerView) findViewById(R.id.recycle_view);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recycleVacina.setLayoutManager(llm);
        adapter = new VacinaAdapter(listaVacinas);
        recycleVacina.setAdapter(adapter);
    }
}
