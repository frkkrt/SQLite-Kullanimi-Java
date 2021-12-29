package com.furkankurt.sqlitekullanimi;

import android.app.Activity;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SqLite_Katmani extends SQLiteOpenHelper {
    //Veritabanı işlemlerini yapabilmem için hangi activityde çalışacağını belirtmek zorundayız.
    //Context==activity
    public SqLite_Katmani(Activity activity)
    {
        //versiyon verme sebebi markete tekrar attığımda versiyona göre veritabanı güncellemesi gerçekleştirecek.
        super(activity,"DbNotlar",null,1);
    }
    //Veritabanındaki tablolaları oluşturuyor.

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tablo oluşturma sorgusu
        db.execSQL("Create table TBLNot(id integer primary key autoincrement, NotAdi Text,Tarih Text)");

    }
    //Uygulama üzerine uygulama kurmadığında hiçbir sorun çıkarmaz.
    //Uygulama güncellendiğinde veritabanı versiyon'unda değişiklik var ise buraya yazılır.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Bu isimde tablo varsa o zaman tabloyu tamamen sil.
        //Güncelleme yapılıdığında varsa sil.
        db.execSQL("drop table if exists TBLNot");
    }
}
