package com.furkankurt.sqlitekullanimi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Kullanıcı=>Crud işlemleri=>SQL Katmani
        CrudNot c=new CrudNot(this);
        c.ac();

        // String cevap1=c.Insert("Bu Bir Nottur.","20.05.2021");

        // String cevap2=c.Insert("Android Dersi Notu.","21.05.2021");

        //c.Update("Yeni Güncellenen Data",c.TekData(1).Tarih,1);

        //c.Delete(2);
        //Lambda Expression
        c.Listele().forEach(e->
        {
            System.out.println(e.id+"-"+e.NotAdi+" - "+e.Tarih+"");
        });
        //Tek data getirme=System.out.println(c.TekData(1).NotAdi);


        c.kapat();

    }
}