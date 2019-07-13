package com.example.scray.breakthesilencev3;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.Locale;


public class ImageButtonFragment extends Fragment {


View imageView;
TextToSpeech tts;
    private ImageButton button1,button2,button3,button4;
    private ImageButton button5,button6,button7,button8;
    private ImageButton button9,button10,button11,button12;
    private ImageButton button13,button14;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        imageView = inflater.inflate(R.layout.fragment_image_button, container, false);
        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(new Locale("bn_BD"));

                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                       // sayItButton.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }

        });

        button1 =(ImageButton) imageView.findViewById(R.id.button1);
        button2 = (ImageButton)imageView.findViewById(R.id.button2);
        button3 =(ImageButton) imageView.findViewById(R.id.button3);
        button4 =(ImageButton) imageView.findViewById(R.id.button4);
        button5 =(ImageButton) imageView.findViewById(R.id.button5);
        button6 = (ImageButton)imageView.findViewById(R.id.button6);
        button7 =(ImageButton) imageView.findViewById(R.id.button7);
        button8 =(ImageButton) imageView.findViewById(R.id.button8);
        button9 =(ImageButton) imageView.findViewById(R.id.button9);
        button10 = (ImageButton)imageView.findViewById(R.id.button10);
        button11 =(ImageButton) imageView.findViewById(R.id.button11);
        button12 =(ImageButton) imageView.findViewById(R.id.button12);
        button13 =(ImageButton) imageView.findViewById(R.id.button13);
        button14 =(ImageButton) imageView.findViewById(R.id.button14);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আজকে আমি অনুপস্থিত ছিলাম";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আপনি একজন প্রাপ্তবয়স্ক মানুষ ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "জিনিসটি নিয়ে আসো";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আমি অভিযোগ করতে চাই ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আমি শুনতে পারি না ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "সে একজন অসৎ মানুষ ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "সে আমাকে আঘাত করেছে ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আপনি আমাকে অগ্রাহ্য করছেন ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আপনি আমাকে অপমান করছে।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "সে আমাকে অতিক্রম করে গেছেন ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আমি কি আপনার অনুমতি পেতে পারি ?";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আমি আত্মরক্ষা করেছি";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s = "আপনি আমাকে অত্যাচার করছেন ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = "আমি বাথরুমে যেতে চাই ।";
                //System.out.println(s);
                // Toast.makeText(getActivity(),s,Toast.LENGTH_LONG);
                speak(s);
            }
        });

        return imageView;
    }
    private void speak(String s) {
        String text = s;
        // mEditText.setText(null);
        tts.setPitch((float) 1.15);
        tts.setSpeechRate((float) 1.15);
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Image");
    }

    // TODO: Rename method, update argument and hook method into UI event
}