package com.example.zooft;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng zoo = new LatLng(40.40934544720297, -3.760961529100968);
        LatLng lobo = new LatLng(40.40849835437475, -3.762409049782553);
        LatLng lince = new LatLng(40.408625146152005, -3.762026685555514);
        LatLng panda = new LatLng(40.40979817802131, -3.762507313141042);
        LatLng bisonte = new LatLng(40.40903305796047, -3.762756336536821);
        LatLng hormiguero = new LatLng(40.4099994288488, -3.764179279932559);
        LatLng tapir = new LatLng(40.410141934189, -3.76184139750137);
        LatLng tigre = new LatLng(40.41083223870407, -3.7646630610279885);
        LatLng pinguino = new LatLng(40.410757461549984, -3.761478183296418);
        LatLng tiburon = new LatLng(40.407702082752195, -3.7645253179396803);
        LatLng ibis = new LatLng(40.40835481437377, -3.7655280871783168);
        LatLng loris = new LatLng(40.40831193672428, -3.765248964160237);
        LatLng guacamayo = new LatLng(40.40836032041865, -3.7653502618756445);
        LatLng nutria = new LatLng(40.40931155147207, -3.7652958827811895);
        LatLng rinoceronte = new LatLng(40.409307563472815, -3.7665212291971546);

        LatLng restauranteKibanda = new LatLng(40.40731113824084, -3.7636985765881064);
        LatLng restauranteVirunga = new LatLng(40.409154919979166, -3.7651475920725956);
        LatLng restaurantePrincipal = new LatLng(40.40952582191747, -3.7618174292304265);

        LatLng fotoJirafa = new LatLng(40.41019105857672, -3.765207091804097);
        LatLng fotoAves = new LatLng(40.40815295663349, -3.765008470097159);
        LatLng fotoDelfines = new LatLng(40.40804705692474, -3.765817383937431);


        mMap.addMarker(new MarkerOptions()
                .position(zoo)
                .title("Zoo Acuarium"));

        mMap.addMarker(new MarkerOptions()
                .position(lobo)
                .title(getString(R.string.lobo)));

        mMap.addMarker(new MarkerOptions()
                .position(lince)
                .title(getString(R.string.lince)));

        mMap.addMarker(new MarkerOptions()
                .position(panda)
                .title(getString(R.string.panda)));

        mMap.addMarker(new MarkerOptions()
                .position(bisonte)
                .title(getString(R.string.bisonte)));

        mMap.addMarker(new MarkerOptions()
                .position(hormiguero)
                .title(getString(R.string.hormiguero)));

        mMap.addMarker(new MarkerOptions()
                .position(tapir)
                .title(getString(R.string.tapir)));

        mMap.addMarker(new MarkerOptions()
                .position(tigre)
                .title(getString(R.string.tigre)));

        mMap.addMarker(new MarkerOptions()
                .position(pinguino)
                .title(getString(R.string.pinguino)));

        mMap.addMarker(new MarkerOptions()
                .position(tiburon)
                .title(getString(R.string.tiburon)));

        mMap.addMarker(new MarkerOptions()
                .position(ibis)
                .title(getString(R.string.ibis)));

        mMap.addMarker(new MarkerOptions()
                .position(loris)
                .title(getString(R.string.loris)));

        mMap.addMarker(new MarkerOptions()
                .position(guacamayo)
                .title(getString(R.string.guacamayo)));

        mMap.addMarker(new MarkerOptions()
                .position(nutria)
                .title(getString(R.string.nutria)));

        mMap.addMarker(new MarkerOptions()
                .position(rinoceronte)
                .title(getString(R.string.rinoceronte)));


        mMap.addMarker(new MarkerOptions()
                .position(restauranteKibanda)
                .title(getString(R.string.restauranteKibanda))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position(restauranteVirunga)
                .title(getString(R.string.restauranteVirunga))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position(restaurantePrincipal)
                .title(getString(R.string.restaurantePrincipal))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        mMap.addMarker(new MarkerOptions()
                .position(fotoJirafa)
                .title(getString(R.string.fotoJirafa))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(fotoAves)
                .title(getString(R.string.fotoAve))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.addMarker(new MarkerOptions()
                .position(fotoDelfines)
                .title(getString(R.string.fotoDelfines))
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(zoo));

        // Add a marker in Sydney and move the camera
        /*LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}