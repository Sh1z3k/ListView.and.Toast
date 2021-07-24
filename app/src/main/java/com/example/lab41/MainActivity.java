package com.example.lab41;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String[] tablica;
    ListView lV;
    Toast tost;
    Button przycisk;
    ImageView imageView;
    ArrayAdapter<Student> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //tablica = getResources().getStringArray(R.array.województwa);
        lV=(ListView) findViewById(R.id.listView);
        lV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        imageView = findViewById(R.id.imageView);
        tost = Toast.makeText(this,"test text",Toast.LENGTH_LONG);
        TextView tekst1 = findViewById(R.id.poleTekstowe);

        if (Student.studenci.isEmpty()) {
            new Student("Stanisław", "Antczak", "Mechaniczny", "1998-01-11", 4.13,R.drawable.facet1);
            new Student("Janusz", "Antkiewicz", "Mechaniczny", "1998-08-11", 4.19,R.drawable.facet2);
            new Student("Paweł", "Bartnik", "Nawigacyjny", "1998-01-11", 4.13,R.drawable.facet3);
            new Student("Adam", "Bartkowiak", "Nawigacyjny", "1996-07-11", 3.13,R.drawable.facet4);
            new Student("Paweł", "Zagórski", "Nawigacyjny", "1995-01-11", 3.99,R.drawable.facet5);
            new Student("Piotr", "Zawadzki", "PiT", "1997-03-21", 4.01,R.drawable.facet6);
            new Student("Bartosz", "Kowalski", "Nawigacyjny", "1995-11-11", 3.99,R.drawable.facet7);
            new Student("Mikołaj", "Żurawski", "PiT", "1997-12-21", 4.89,R.drawable.facet8);
            new Student("Paweł", "Filipiak", "PiT", "2001-04-29", 3.99,R.drawable.facet9);
            new Student("Zdzisław", "Gutkowski", "Nawigacyjny", "2001-05-29", 4.05,R.drawable.facet10);
        }
        tablica = new String[Student.studenci.size()];
        for(int i =0;i<Student.studenci.size();i++){
            tablica[i] = Student.studenci.get(i).toString();
        }
        adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_checked, Student.studenci);
        lV.setAdapter(adapter);
        lV.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        przycisk = findViewById(R.id.button);
        AdapterView.OnItemClickListener sluchacz = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tekst1.setText(tablica[position]);
                imageView.setImageResource(Student.studenci.get(position).zdjęcie);
            }
        };
        View.OnClickListener object = new View.OnClickListener() {


            public void onClick(View v) {
                String napis = "";
                for(int i=0;i<lV.getCount();i++){
                    if(lV.isItemChecked(i))
                        napis = napis+" "+tablica[i]+"\n";
                }
                tost.setText(napis);
                tost.show();
            }
        };
        przycisk.setOnClickListener(object);
        lV.setOnItemClickListener(sluchacz);
    }
    public void onRBC(View view){
        switch(view.getId()) {
            case R.id.radioButton:
                Student.studenci.sort(null);
                adapter.notifyDataSetChanged();
                Log.v("klik","nazwisko");
                break;
            case R.id.radioButton2:
                Student.studenci.sort(Student.kompSr);
                adapter.notifyDataSetChanged();
                Log.v("klik","srednia");
                break; }
    }
}