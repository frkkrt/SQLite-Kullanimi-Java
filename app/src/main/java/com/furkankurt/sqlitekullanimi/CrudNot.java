package com.furkankurt.sqlitekullanimi;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

public class CrudNot {

    SQLiteDatabase Sqlliedb;
    SqLite_Katmani Benimdb;

    public CrudNot(Activity activity)
    {
        Benimdb=new SqLite_Katmani(activity);

    }
    //Okuma Yazma Açık
    public void ac()
    {
        //GetWritable hem okuma hem yazma yapmamıza olanak sağlar.
        Sqlliedb=Benimdb.getWritableDatabase();
    }
    //Okuma Yazma Pasif
    public void kapat(){
        Benimdb.close();
    }

    //Ekleme-Insert
    public String Insert(String NotAdi,String Tarih){
        //SQL Lite sadece bunu kabul ediyor.
        ContentValues val=new ContentValues();
        val.put("NotAdi",NotAdi);
        val.put("Tarih",Tarih);
        //Sqlliedb.execSQL("Insert into TBLNot(NotAdi,Tarih) Values('"+NotAdi+"','"+Tarih+"')");
        Sqlliedb.insert("TBLNot",null,val);
        return "Veri Eklendi";
    }

    //Güncelleme-Update

    public String Update(String NotAdi,String Tarih,int id)
    {
        //ContentValues kullanırsan hepsini güncellemek zorundasın
        ContentValues val=new ContentValues();
        val.put("NotAdi",NotAdi);
        val.put("Tarih",Tarih);
        Sqlliedb.update("TBLNot", val,"id="+id,null);
        //Sorguyu elle yazarsan o zaman istediğini güncelleyebilirsin.
        //Tek başına bu sorgu da aynı görevi görür.
        //Sqlliedb.execSQL("Update TBLNot set NotAdi='"+NotAdi+"',Tarih='"+Tarih+"'From TBLNot where id="+id+"");

        return "Bilgiler Güncellendi";
    }
    //Silme-Delete
    public String Delete(int id){
        //Sqlliedb.execSQL("Delete from TBLnot where id="+id+"");
        Sqlliedb.delete("TBLNot","id="+id,null);
        return "Bilgi Slindi";
    }
    //Listeleme-Select
    public List<TBLNot> Listele(){
        List<TBLNot> notlarim=new ArrayList<>();
        Cursor cursor=Sqlliedb.rawQuery("Select * from TBLNot",null);
        //Bir Sonraki Satır Varmı
        cursor.moveToFirst();//İlk kayıttan başla
        while(!cursor.isAfterLast())//satır satır atlatma işlemi
        {
            notlarim.add(new TBLNot(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)));
            //Bir sonraki satır'a gider.
            cursor.moveToNext();
        }
        return notlarim;
        }

//Koşullu veri getirme(Şartlı Veri Getirme)
    public TBLNot TekData(int id){
        List<TBLNot> notlarim=new ArrayList<>();
        Cursor cursor=Sqlliedb.rawQuery("Select * from TBLNot where id="+id+"",null);
        //Bir Sonraki Satır Varmı
        if(cursor.moveToFirst())//satır satır atlatma işlemi
        {
           TBLNot not=new TBLNot(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2));
        cursor.close();
        return not;
        }
        else {
            cursor.close();
            return null;
        }

    }
}

