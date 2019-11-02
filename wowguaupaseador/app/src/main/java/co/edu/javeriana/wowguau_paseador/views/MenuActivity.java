package co.edu.javeriana.wowguau_paseador.views;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.HashMap;

import co.edu.javeriana.wowguau_paseador.R;
import co.edu.javeriana.wowguau_paseador.model.Direccion;
import co.edu.javeriana.wowguau_paseador.model.Paseador;
import co.edu.javeriana.wowguau_paseador.utils.FirebaseUtils;
import co.edu.javeriana.wowguau_paseador.utils.Utils;
import de.hdodenhof.circleimageview.CircleImageView;

public class MenuActivity extends AppCompatActivity {
    private ConstraintLayout cl_logout;
    private TextView tv_h_nombre;
    private CircleImageView iv_perfil;
    private TextView tv_estado;
    private TextView tv_saldo;

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    private Paseador paseador;

<<<<<<< Updated upstream
=======
    private String TAG="MENU";

    private FragmentRefreshListener fragmentRefreshListener;

>>>>>>> Stashed changes
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mAuth = FirebaseAuth.getInstance();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerview = navigationView.getHeaderView(0);
        tv_h_nombre = headerview.findViewById(R.id.tv_h_nombre);
        iv_perfil = headerview.findViewById(R.id.iv_perfil);
        tv_estado = headerview.findViewById(R.id.estado);
        tv_saldo = headerview.findViewById(R.id.saldo);

        cl_logout = findViewById(R.id.cl_logout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio, R.id.nav_actualizar, R.id.nav_historial) //acá se agregan las otras opciones del menu
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        headerview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), PerfilActivity.class);
                i.putExtra("user", paseador);
                startActivity(i);
            }
        });
        cl_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(MenuActivity.this, LoginActivity.class);
                intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
<<<<<<< Updated upstream
        if(getIntent().hasExtra("user"))
            paseador = (Paseador) getIntent().getSerializableExtra("user");
=======
        if(getIntent().hasExtra("uid")) {
            docRef = db.collection("Paseadores").document(getIntent().getStringExtra("uid"));
            registration = docRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot snapshot, @Nullable FirebaseFirestoreException e) {
                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e);
                        return;
                    }

                    if (snapshot != null && snapshot.exists()) {
                        Log.d(TAG, "Current data: " + snapshot.getData());
                        Direccion dir = new Direccion(String.valueOf(((HashMap)snapshot.get("direccion")).get("direccion")), Double.parseDouble(((HashMap)snapshot.get("direccion")).get("latitud").toString()), Double.parseDouble(((HashMap)snapshot.get("direccion")).get("longitud").toString()));
                        paseador = new Paseador(String.valueOf(snapshot.get("correo")), String.valueOf(snapshot.get("nombre")), Long.parseLong(snapshot.get("cedula").toString()), snapshot.getTimestamp("fechaNacimiento").toDate(), Long.parseLong(snapshot.get("telefono").toString()), String.valueOf(snapshot.get("genero")), dir, String.valueOf(snapshot.get("descripcion")), Integer.parseInt(snapshot.get("experiencia").toString()));
                        paseador.setDireccionFoto(snapshot.get("direccionFoto").toString());
                        updateUI();
                    } else {
                        Log.d(TAG, "Current data: null");
                    }
                }
            });
        }
>>>>>>> Stashed changes
        else
            return;
        FirebaseUtils.descargarFotoImageView( paseador.getDireccionFoto(), iv_perfil);


        //FirebaseUtils.descargarFotoImageView( paseador.getDireccionFoto(), iv_perfil);
        //paseador.setFoto(Utils.getBitmap(iv_perfil.getDrawable()));

        Log.i("PASEADOR", paseador.getNombre());
        updateUI();
    }

    private void updateUI(){
        tv_h_nombre.setText(paseador.getNombre());
        tv_estado.setText(" "+(paseador.isEstado()? "Disponible": "No disponible"));
        tv_saldo.setText(paseador.getSaldo()+" petCoins");
<<<<<<< Updated upstream
=======
        //FirebaseUtils.descargarFotoImageView( paseador.getDireccionFoto(), iv_perfil);
        fragmentRefreshListener.onRefresh();
>>>>>>> Stashed changes
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public Paseador getPaseador() {
        return paseador;
    }

    public void setPaseador(Paseador paseador) {
        this.paseador = paseador;
        updateUI();
    }
    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    public interface FragmentRefreshListener{
        void onRefresh();
    }
}
