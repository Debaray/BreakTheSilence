package com.example.scray.breakthesilencev3;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CommnicationFragment extends Fragment {
    Database db;
    List<TableInfo> myList;
    ArrayAdapter<String> adapter;
    ArrayList<String> list;
    Button shagotomButton,apniKmnAchenButton,shobKhbrValoButton,amiValoAchiApniButton,basharShobaiValoAcheButton, shobaiKNiyeAshbenButton;
    Button apnrShatePorichoyHoyeValoLagloButton,dhonnobaadButton;
    private Button generateButton,sayItButton;
    private Button b1,b2,b3,b4,b5,b6,b7,b8;
    private AutoCompleteTextView butonTextView,sayTextView;
    private  int idCount = 1;
    private View communicationView;


    private TextToSpeech tts;
    int keyPressed=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        communicationView = inflater.inflate(R.layout.fragment_commnication, container, false);
        db=new Database(getActivity());
        db.deleteAll();
        AddData a=new AddData();
        a.addDataTable4(getActivity());
        a.addDataBanglaTable4(getActivity());
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(new Locale("bn_BD"));

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        sayItButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }

        });

        shagotomButton = (Button) communicationView.findViewById(R.id.shagotomButton);
        amiValoAchiApniButton = (Button) communicationView.findViewById(R.id.amiValoAchiApni);
        apnrShatePorichoyHoyeValoLagloButton = (Button) communicationView.findViewById(R.id.apnarShatePorichoyHoyeValoLaglo);
        shobKhbrValoButton = (Button) communicationView.findViewById(R.id.shobKhbrValo);
        apniKmnAchenButton = (Button) communicationView.findViewById(R.id.apniKmnAchen);
        basharShobaiValoAcheButton = (Button) communicationView.findViewById(R.id.basharShobaiValoAche);
        dhonnobaadButton = (Button) communicationView.findViewById(R.id.dhonnobaad);
        shobaiKNiyeAshbenButton = (Button) communicationView.findViewById(R.id.shobaikNiyeBashayAshben);

        b1 = (Button) communicationView.findViewById(R.id.b1);
        b2 = (Button) communicationView.findViewById(R.id.b2);
        b3 = (Button) communicationView.findViewById(R.id.b3);
        b4 = (Button) communicationView.findViewById(R.id.b4);
        b5 = (Button) communicationView.findViewById(R.id.b5);
        b6 = (Button) communicationView.findViewById(R.id.b6);
        b7 = (Button) communicationView.findViewById(R.id.b7);
        b8 = (Button) communicationView.findViewById(R.id.b8);

        populateList();

        butonTextView = (AutoCompleteTextView) communicationView.findViewById(R.id.editButtonText);
        butonTextView.setThreshold(1);
        butonTextView.setAdapter(adapter);

        sayTextView = (AutoCompleteTextView) communicationView.findViewById(R.id.editText);
        sayTextView.setThreshold(1);
        sayTextView.setAdapter(adapter);

        generateButton = (Button) communicationView.findViewById(R.id.generateButtonId);
        sayItButton = (Button) communicationView.findViewById(R.id.sayButtonId);

        sayTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               keyPressed=1;
            }
        });

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(idCount == 1)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b1.setText(butonTextView.getText());
                        b1.setVisibility(View.VISIBLE);
                        butonTextView.setText(null);
                        idCount++;
                    }
                }
                else if(idCount == 2)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b2.setText(butonTextView.getText());
                        b2.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
                else if(idCount == 3)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b3.setText(butonTextView.getText());
                        b3.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
                else if(idCount == 4)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b4.setText(butonTextView.getText());
                        b4.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
                else if(idCount == 5)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b5.setText(butonTextView.getText());
                        b5.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
                if(idCount == 6)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b6.setText(butonTextView.getText());
                        b6.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
                else if(idCount == 7)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b7.setText(butonTextView.getText());
                        b7.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
                else if(idCount == 8)
                {
                    if(!(butonTextView.getText().toString().isEmpty())) {
                        b8.setText(butonTextView.getText());
                        b8.setVisibility(View.VISIBLE);
                        butonTextView.setText(" ");
                        idCount++;
                    }
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b1.getText().toString();
                speak(s);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b2.getText().toString();
                speak(s);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b3.getText().toString();
                speak(s);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b4.getText().toString();
                speak(s);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b5.getText().toString();
                speak(s);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b6.getText().toString();
                speak(s);
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b7.getText().toString();
                speak(s);
            }
        });
        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = b8.getText().toString();
                speak(s);
            }
        });
        shagotomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // onDestroy();
                String s = shagotomButton.getText().toString();
                speak(s);
                // sayTextView.setText(s);
            }
        });

        amiValoAchiApniButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // onDestroy();
                String s = amiValoAchiApniButton.getText().toString();
                speak(s);
            }
        });

        apnrShatePorichoyHoyeValoLagloButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // onDestroy();
                String s = apnrShatePorichoyHoyeValoLagloButton.getText().toString();
                speak(s);
            }
        });

        shobKhbrValoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //  onDestroy();
                String s = shobKhbrValoButton.getText().toString();
                speak(s);
            }
        });

        apniKmnAchenButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // onDestroy();
                String s = apniKmnAchenButton.getText().toString();
                speak(s);
            }
        });

        basharShobaiValoAcheButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //onDestroy();
                String s = basharShobaiValoAcheButton.getText().toString();
                speak(s);
            }
        });

        dhonnobaadButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // onDestroy();
                String s = dhonnobaadButton.getText().toString();
                speak(s);
            }
        });

        shobaiKNiyeAshbenButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //onDestroy();
                String s = shobaiKNiyeAshbenButton.getText().toString();
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });

        sayItButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = sayTextView.getText().toString();
                if(keyPressed==0)
                {
                    db.insertTable4(sayTextView.getText().toString(),0);
                    populateList();
                    sayTextView.setAdapter(adapter);
                    //listView.setVisibility(View.INVISIBLE);
                }
                else
                {
                    keyPressed=0;
                }
                speak(s);
                sayTextView.setText(null);
            }
        });


        return communicationView;
    }
    private void speak(String s) {
        String text = s;
        // mEditText.setText(null);
        tts.setPitch((float) 1.25);
        tts.setSpeechRate((float) 1.25);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }
    private void populateList()
    {
        myList=db.getAllDataTable4();
        final String string[]= new String[myList.size()];



        //  myBookList=db.getAllBooks();
        int i = 0;

        for (TableInfo t : myList) {
            string[i] = new String(t.getSentence());
            Log.d("List Book ", string[i]);
            i++;
        }
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.row_design, string);


    }
    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Communication");
    }


}
