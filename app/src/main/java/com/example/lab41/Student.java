package com.example.lab41;

import androidx.annotation.NonNull;

import java.text.Collator;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Student implements Comparable<Student>{
    String imie = "Jan";
    String nazwisko = "Nowak";
    String wydzial = "PiT";
    String dataUr = "1999-12-12";
    double srednia = 3.5;
    int zdjęcie;
    int lata;
    int miesiace;
    int dni;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/");
    String data=sdf.format(new Date());
    final int bMiesiac=Integer.parseInt(data.substring(5,7));
    final int bRok=Integer.parseInt(data.substring(0,4));
    final int bDzien=Integer.parseInt(data.substring(8,10));
    public Student(String imie, String nazwisko, String wydzial,
                   String dataUr, double srednia, int z){
        this.imie=imie;
        this.nazwisko=nazwisko;

        this.wydzial=wydzial;
        this.dataUr=dataUr;
        this.srednia=srednia;
        this.zdjęcie=z;
        studenci.add(this);
    }
    public Student(){studenci.add(this);}

    static ArrayList<Student> studenci = new  ArrayList<Student>();

    @Override
    public String toString() {
        return  String.format("%-12s %-12s\n%-12s %-12s", this.imie, this.nazwisko, this.wydzial, String.valueOf(this.srednia));
    }
    public String okreslWiek(){
        data=this.dataUr;
        int uRok=Integer.parseInt(data.substring(0,4));
        int uMiesiac=Integer.parseInt(data.substring(5,7));
        int uDzien=Integer.parseInt(data.substring(8,10));
        lata=bRok-uRok;
        miesiace=bMiesiac-uMiesiac;
        dni = bDzien-uDzien;
        if(miesiace<0) {
            lata-=1;
            miesiace=12+miesiace;}
        if(dni<0) {
            if(miesiace>0) miesiace-=1;
            else lata--;
            dni=31+dni;}
        return ": lat "+lata +" miesięcy " +miesiace +" dni " +dni;
    }
    static LocalDate dzisiaj=LocalDate.now();
    public double wiek(String data){
        int uRok=Integer.parseInt(data.substring(0,4));
        int uMiesiac=Integer.parseInt(data.substring(5,7));
        int uDzień=Integer.parseInt(data.substring(8,10));
        LocalDate dataUrodzenia = LocalDate.of(uRok, uMiesiac, uDzień);
        Period per=Period.between(dataUrodzenia,dzisiaj);
        return per.getYears()+per.getMonths()/12.0+per.getDays()/365.0;
    }
    static Comparator<Student> kompSr = new Comparator<Student>() {
        @Override
        public int compare(Student s1, Student s2) {
            double roznica=s1.srednia-s2.srednia;
            if (roznica>0) return 1;
            else if (roznica<0) return -1;
            else return 0;}
    };
    @Override
    public int compareTo(@NonNull Student student) {
        Collator c = Collator.getInstance(new Locale("pl", "PL"));
        int porównanieNazwisk=c.compare(this.nazwisko,student.nazwisko);
        int porównanieImion=c.compare(this.imie,student.imie);
        if(porównanieNazwisk==0) return porównanieImion;
        return porównanieNazwisk;}
}
