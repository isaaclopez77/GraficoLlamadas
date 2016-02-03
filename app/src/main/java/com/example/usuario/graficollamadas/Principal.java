package com.example.usuario.graficollamadas;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.example.usuario.graficollamadas.POJO.GestorLlamada;
import com.example.usuario.graficollamadas.POJO.Llamada;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Principal extends AppCompatActivity {

    private WebView webView;
    private WebSettings wSettings;
    public static GestorLlamada gp;
    private List<Llamada> salientes, entrantes;
    private int[] totalE = new int[7], totalS = new int[7];

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        webView = (WebView)findViewById(R.id.webView);

        //Configurando webView
        wSettings = webView.getSettings();
        webView.setClickable(true);
        wSettings.setJavaScriptEnabled(true);

        //Configurando cliente web
        WebClientClass webViewClient = new WebClientClass();
        webView.setWebViewClient(webViewClient);
        WebChromeClient webChromeClient = new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);

        //Añadiendo interfaz
        webView.addJavascriptInterface(this, "Android");

        //Cargando página
        webView.loadUrl("file:///android_asset/Index.html");
    }

    @Override
    protected void onResume() {
        super.onResume();
        gp = new GestorLlamada(this);
        gp.open();
        init();
    }

    private List<Llamada> getLlamadasSalientes(){
        return gp.select("tipo = ?",new String[]{"1"});
    }

    private List<Llamada> getLlamadasEntrantes(){
        return gp.select("tipo = ?",new String[]{"0"});
    }

    public void init(){
        //0 = entrante = false
        //1 = saliente = true

        salientes = getLlamadasSalientes();
        for (Llamada y: salientes){
            int dia = y.getDia();
            switch (dia){
                case 1:
                    totalS[0]++;
                    break;
                case 2:
                    totalS[1]++;
                    break;
                case 3:
                    totalS[2]++;
                    break;
                case 4:
                    totalS[3]++;
                    break;
                case 5:
                    totalS[4]++;
                    break;
                case 6:
                    totalS[5]++;
                    break;
                case 7:
                    totalS[6]++;
                    break;
            }
        }

        entrantes = getLlamadasEntrantes();
        for (Llamada y: entrantes){
            int dia = y.getDia();
            switch (dia){
                case 1:
                    totalE[0]++;
                    break;
                case 2:
                    totalE[1]++;
                    break;
                case 3:
                    totalE[2]++;
                    break;
                case 4:
                    totalE[3]++;
                    break;
                case 5:
                    totalE[4]++;
                    break;
                case 6:
                    totalE[5]++;
                    break;
                case 7:
                    totalE[6]++;
                    break;
            }
        }
    }

    @JavascriptInterface
    public int enviarEntrantes(int indice){
        return totalE[indice];
    }

    @JavascriptInterface
    public int enviarSalientes(int indice){
        return totalS[indice];
    }

    /*************** Broadcast Receiver *****************/
    public static class ReceptorLlamada extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, final Intent intent) {

            TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            tm.listen(new PhoneStateListener() {
                public void onCallStateChanged(int state, String incomingNumber) {
                    if (state == TelephonyManager.CALL_STATE_RINGING) {
                        Calendar now = Calendar.getInstance();

                        Llamada nueva = new Llamada();
                        nueva.setNumero(incomingNumber);
                        nueva.setTipo(false);
                        nueva.setDia(now.get(Calendar.DAY_OF_WEEK));
                        gp.insert(nueva);

                    }else if(state== TelephonyManager.CALL_STATE_OFFHOOK){
                        Calendar now = Calendar.getInstance();

                        Llamada nueva = new Llamada();
                        nueva.setNumero(incomingNumber);
                        nueva.setTipo(true);
                        nueva.setDia(now.get(Calendar.DAY_OF_WEEK));
                        gp.insert(nueva);
                    }
                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        }
    }

    /********** Web Client **************/
    public class WebClientClass extends WebViewClient {
        ProgressDialog pd;

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            pd = new ProgressDialog(Principal.this);
            pd.setTitle("Por favor espera...");
            pd.setMessage("La página esta cargando...");
            pd.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pd.dismiss();
        }
    }
}