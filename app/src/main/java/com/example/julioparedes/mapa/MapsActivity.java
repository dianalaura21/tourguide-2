package com.example.julioparedes.mapa;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.lang.reflect.Array;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener, LocationListener {

    private GoogleMap mMap;
    //variable para el marcador de ubicacion actual
    private SimpleGeofence geo;
    Marker area, areaDos;
    private Marker marcador;
    Spinner Lugares;
    double lat;
    double lng;
    //aqui se almacenan los datos de los puntos del mapa
    //  String[] veclatp = {"25.791176", "25.790967", "25.790629", "25.788927", "25.788743", "25.787977", "25.787977"};
    //String[] veclonp = {"-109.003629", "-109.004978", "-109.004039", "-109.004199", "-109.005349", "-109.006323", "-109.007220"};
    //String[] descripcionp = {"Jardín Xerofíto", "Jardín del Camino Dorado", "Jardín del Ying Yang", "Jardín Francés", "Estanque", "Plantario", "Área Recreativa"};
    //String [] brevep={"Es la colección más reciente del jardín botánico. En ella se aprecian especies del semidesierto mexicano, representado por los estados del noroeste (Sinaloa, Sonora y Baja California).","Los olivos negros que custodian el sendero dan origen al nombre de camino dorado por las tonalidades de las hojas secas sobre el suelo que son tocadas por el sol, es posible que los primero olivos negros introducidos en Sinaloa hayan sido los del jardín de Johnston.","Una notable influencia oriental en su diseño es el distintivo principal de este espacio, sus curvos caminos resaltados son pasarelas por las que desfila especies tan peculiares como sorprendentes, el árbol flor de madera se llama así por sus frutos esférico secos que abren formando una flor.","Este espacio fue el jardín privado de la familia Johnston y sitio habitual de la convivencia entre sus invitados.","Es un cuerpo de agua rodeado por diversidad de árboles y palmeras, bajo las cuales se encuentra un camino adoquinado conectado por dos puentes, desde el cual se distingue una fuente colocada al frente.","Un espacio educativo y recreativo, a través del contacto con la naturaleza, conformado por una colección de mas de 100 especies tropicales, que explican su interacción en el ecosistema.","Costumbres y tradiciones generan la identidad de una región, por ello comprometidos por preservarlas trabajamos bajo tres objetivos principales en este recinto: difusión, educación y preservación de los diferentes elementos que conforma nuestra etnia yoreme-mayo."};
    //String [] links={"http://jbbfj.org/v3/jardin-botanico/jardines/jardin-xerofito","http://jbbfj.org/v3/jardin-botanico/jardines/jardin-camino-dorado","http://jbbfj.org/v3/jardin-botanico/jardines/jardin-ying-yang","http://jbbfj.org/v3/jardin-botanico/jardines/jardin-frances","http://jbbfj.org/v3/jardin-botanico/mapa-jardin#estanque","http://jbbfj.org/v3/educacion/plantario","http://jbbfj.org/v3/educacion/recinto-educativo"};


    //aqui se almacenan los datos de los puntos del mapa
    String[] veclat = {"25.7900944", "25.7889348", "25.7878296", "25.7888076","25.5782504","25.8117089","25.8035686", "25.802362"};
    String[] veclon = {"-109.0044555", "-109.0005128", "-108.9892723","-108.998863", "-109.1183642", "-108.9685455", "-108.973889","-108.974619"};
    String[] descripcion = {"Parque Sinaloa", "Teatro Ingenio", "Mercado Independencia", "Trapiche Museo Interactivo","Playa El Maviri","Cerro de la Memoria","Pergola","Estadio Emilio Ibarra"};
    String[] breve = {"El parque Sianloa fue fundado en 1929. \nhttp://tourguidelm.000webhostapp.com/ ", "Infraestructura para eventos especiales", "Conjunto de locales comerciales", "Espacio interactivo familiar", "Zona costera de Los Mochis.", "Es el referente obligatorio de la ciudad", "hdfghsd", "sdgsdfgf"};
   String [] links ={"http://tourguidelm.000webhostapp.com/","http://tourguidelm.000webhostapp.com/","http://tourguidelm.000webhostapp.com/","http://tourguidelm.000webhostapp.com/","http://tourguidelm.000webhostapp.com/","http://tourguidelm.000webhostapp.com/", "http://tourguidelm.000webhostapp.com/", "http://tourguidelm.000webhostapp.com/"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Button cambiarUbicacionUno = (Button) findViewById(R.id.cambiarUbicacionUno);
        Button cambiarUbicacionDos = (Button) findViewById(R.id.cambiarUbicacionDos);

        //ArrayAdapter<CharSequence> adapter =ArrayAdapter.createFromResource(this,R.array.Lugares,android.R.layout.simple_spinner_item);
        //Lugares.setAdapter(adapter);

        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) this);
        onLocationChanged(new Location(LocationManager.NETWORK_PROVIDER));


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // cuando se hace click en el botton uno hte cambia tu ubicacion actual y entra al geocerca
        cambiarUbicacionUno.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lat=25.8171597;
                lng=-108.9903369;


                actualMarcador(lat,lng);
                try {
                    Thread.sleep(1000);
                    // Do some stuff
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
                //area.showInfoWindow();
            }
        });
        // cuando se hace click en el botton dos te cambia tu ubicacion actual y entra al geocerca
       // cambiarUbicacionDos.setOnClickListener( new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  finish();

   //         }

       // });

       // Lugares.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           // @Override
           // public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //switch (i){
                   // case 1:{
                        //Intent ii=new Intent(view.getContext(),MainActivity.class);
                        //ii.putExtra("lugar",i);
                        //startActivity(ii);
                        //break;
                    //}
                    //case 2:{
                      //  Intent ii=new Intent(view.getContext(),MainActivity.class);
                       // ii.putExtra("lugar",i);
                       // startActivity(ii);
                       // break;
                    //}
                    //case 3:{
                      //  Intent ii=new Intent(view.getContext(),MainActivity.class);
                       // ii.putExtra("lugar",i);
                        //startActivity(ii);
                        //break;
                    //}
                    //case 4:{
                      //  Intent ii=new Intent(view.getContext(),MainActivity.class);
                       // ii.putExtra("lugar",i);
                      ///  startActivity(ii);
                        //break;
                    //}
                    //case 5:{
                      //  Intent ii=new Intent(view.getContext(),MainActivity.class);
                       // ii.putExtra("lugar",i);
                        //startActivity(ii);
                        //break;
                    //}
                //}
           // }
      //  }//);
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
        UiSettings uis=mMap.getUiSettings();
        uis.setMyLocationButtonEnabled(true);
        // crear marcador de ubicaion actual
        actualMarcador(lat,lng);
        //agregar marcadores de lugares del parque
        for (int i = 0; i < veclat.length; i++) {
            agregarMarcador(i);
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.setOnInfoWindowClickListener(this);
    }
    //en este metodo se agregan al mapa los marcadores de los lugares mas importantes y de los cuales similaremos el geocerca
    public void agregarMarcador(int id) {
        String texto = descripcion[id];
        String desBreve = breve[id];
        Double lati = Double.parseDouble(veclat[id]);
        Double longi = Double.parseDouble(veclon[id]);
        LatLng lugar = new LatLng(lati, longi);
        if (texto.equals("Jardín Francés")){
            area =  mMap.addMarker(new MarkerOptions().position(lugar)
                    .title(texto).snippet(desBreve).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar));
            geo = new SimpleGeofence(lati,longi,20,id ,texto);
            addMarkerForFence(geo);
        } else if (texto.equals("Jardín del Camino Dorado")){
            areaDos =  mMap.addMarker(new MarkerOptions().position(lugar)
                    .title(texto).snippet(desBreve).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar));
            geo = new SimpleGeofence(lati,longi,20,id ,texto);
            addMarkerForFence(geo);
        }
        else{
            mMap.addMarker(new MarkerOptions().position(lugar)
                    .title(texto).snippet(desBreve).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            // mMap.moveCamera(CameraUpdateFactory.newLatLng(lugar));
            geo = new SimpleGeofence(lati,longi,20,id ,texto);
            addMarkerForFence(geo);
        }
    }
    //metodo para mostrar marcador de ubicacion actual
    private void actualMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) marcador.remove();
        {
            marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Ubicación Actual").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
            mMap.animateCamera(miUbicacion);
        }
        //Toast.makeText(MapsActivity.this, "dentro actualMarcador", Toast.LENGTH_LONG).show();
    }
    //metodo para crear el geocerca con el circulo rojito
    public  void addMarkerForFence(SimpleGeofence fence){
        if(fence == null){
            // display en error message and return
            return;
        }
//Instantiates a new CircleOptions object +  center/radius
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(fence.getLatitude(), fence.getLongitude()))
                .radius(fence.getRadius())
                .fillColor(0x40ff0000)

                .strokeColor(Color.TRANSPARENT));

// more operations on the circle...

    }
    //metodo para ir al link al hacer click en la ventana emergente
    @Override
    public void onInfoWindowClick(Marker marker) {
        //primero sacamos el id del marcador el cual es un string y le sacaremos el numero a ese string para saber la posicion del vector de los links que accederemos
        String idStr="";
        int id;
        idStr=marker.getId();
        id=Integer.parseInt(idStr.substring(idStr.length() - 1));
        LugaresDialogFragment.newInstance(descripcion[id-1],breve[id-1]).show(getSupportFragmentManager(), null);

        //Toast.makeText(MapsActivity.this, "id: "+id, Toast.LENGTH_LONG).show();
        //abre el navegador con el link del area
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(links[id-1])));

    }

    @Override
    public void onLocationChanged(Location location) {
        // Este mŽtodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
        // debido a la detecci—n de un cambio de ubicacion
        lat=location.getLatitude();
        lng=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
