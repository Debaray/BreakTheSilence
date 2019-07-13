package com.example.scray.breakthesilencev3;

import android.content.Context;

/**
 * Created by masru on 21-Nov-18.
 */

public class AddData {

    public void addDataTable1(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("Ei ricksha Jaben", 0);
        db.insertTable1(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("Mohammadpur Jabo", 0);
        db.insertTable1(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("na 30 taka dibo", 0);
        db.insertTable1(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("na 40 taka dibo", 0);
        db.insertTable1(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("na 50 taka dibo", 0);
        db.insertTable1(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("na 60 taka dibo", 0);
        db.insertTable1(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("achha cholen", 0);
        db.insertTable1(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("baye jaan", 0);
        db.insertTable1(t8.getSentence(),t8.getFreq());
        TableInfo t9 = new TableInfo("dane jaan", 0);
        db.insertTable1(t9.getSentence(),t9.getFreq());
        TableInfo t10 = new TableInfo("shoja jaan", 0);
        db.insertTable1(t10.getSentence(),t10.getFreq());
    }

    public void addDataBanglaTable1(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("এই রিকশা যাবেন", 0);
        db.insertTable1(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("মোহাম্মদপুর যাব", 0);
        db.insertTable1(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("না ৩০ টাকা দিবো ", 0);
        db.insertTable1(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("না ৪০ টাকা দিবো ", 0);
        db.insertTable1(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("না ৫০ টাকা দিবো ", 0);
        db.insertTable1(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("না ৬০ টাকা দিবো ", 0);
        db.insertTable1(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("আচ্ছা চলেন", 0);
        db.insertTable1(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("বায়ে যান", 0);
        db.insertTable1(t8.getSentence(),t8.getFreq());
        TableInfo t9 = new TableInfo("ডানে যান", 0);
        db.insertTable1(t9.getSentence(),t9.getFreq());
        TableInfo t10 = new TableInfo("সোজা যান", 0);
        db.insertTable1(t10.getSentence(),t10.getFreq());
    }
    public void addDataTable2(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("Menu ta diben to", 0);
        db.insertTable2(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("Excuse me ami order dibo", 0);
        db.insertTable2(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("Pizza diben", 0);
        db.insertTable2(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("pasta diben", 0);
        db.insertTable2(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("kachchi", 0);
        db.insertTable2(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("noodles", 0);
        db.insertTable2(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("drinks", 0);
        db.insertTable2(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("bill ta diben please", 0);
        db.insertTable2(t8.getSentence(),t8.getFreq());


    }
    public void addDataBanglaTable2(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("মেনু টা দিবেন তো", 0);
        db.insertTable2(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("এক্সকিউজ মি আমি অরডার দিব", 0);
        db.insertTable2(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("পিজা দিবেন", 0);
        db.insertTable2(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("পাস্তা দিবেন", 0);
        db.insertTable2(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("কাচ্চি দিবেন", 0);
        db.insertTable2(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("নুডলস দিবেন", 0);
        db.insertTable2(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("ড্রিঙ্কস", 0);
        db.insertTable2(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("বিল টা দিবেন প্লিজ", 0);
        db.insertTable2(t8.getSentence(),t8.getFreq());


    }
    public void addDataTable3(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("amake kichhu bhalo shirt dekhan", 0);
        db.insertTable3(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("amak sharee dekhan", 0);
        db.insertTable3(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("amak kamiz dekhan", 0);
        db.insertTable3(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("amak bachchader bhao jama dekhan", 0);
        db.insertTable3(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("amak pant dekhan", 0);
        db.insertTable3(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("amak punjabi dekhan", 0);
        db.insertTable3(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("dam ta ektu koman", 0);
        db.insertTable3(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("ami 1000 taka dibo", 0);
        db.insertTable3(t8.getSentence(),t8.getFreq());
        TableInfo t9 = new TableInfo("500 taka koman bhai", 0);
        db.insertTable3(t9.getSentence(),t9.getFreq());
        TableInfo t10 = new TableInfo("accha pack kore din", 0);
        db.insertTable3(t10.getSentence(),t10.getFreq());

    }
    public void addDataBanglaTable3(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("আমাকে কিছু ভালো শার্ট দেখান", 0);
        db.insertTable3(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("আমাকে শাড়ী দেখান", 0);
        db.insertTable3(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("আমাকে কামিজ দেখান", 0);
        db.insertTable3(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("আমাকে বাচ্চাদের ভালো" +
                " জামা দেখান", 0);
        db.insertTable3(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("আমাকে পেন্ট দেখান", 0);
        db.insertTable3(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("আমাকে পাঞ্জাবি দেখান", 0);
        db.insertTable3(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("দামটা একটু কমান", 0);
        db.insertTable3(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("আমি ১০০০ টাকা দিবো", 0);
        db.insertTable3(t8.getSentence(),t8.getFreq());
        TableInfo t9 = new TableInfo("৫০০ টাকা কমান ভাই", 0);
        db.insertTable3(t9.getSentence(),t9.getFreq());
        TableInfo t10 = new TableInfo("আচ্ছা প্যাক করে দেন", 0);
        db.insertTable3(t10.getSentence(),t10.getFreq());

    }
    public void addDataTable4(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("assalamu alaikum", 0);
        db.insertTable4(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("bhalo achhen?", 0);
        db.insertTable4(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("bashar shobai bhalo?", 0);
        db.insertTable4(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("ami bhalo achhi", 0);
        db.insertTable4(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("onekdin por elen", 0);
        db.insertTable4(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("bhat kheye jaben kintu", 0);
        db.insertTable4(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("abar ashben", 0);
        db.insertTable4(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("khoda hafez", 0);
        db.insertTable4(t8.getSentence(),t8.getFreq());
    }
    public void addDataBanglaTable4(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("আসসালামু আলাইকুম", 0);
        db.insertTable4(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("ভালো আছেন?", 0);
        db.insertTable4(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("বাসার সবাই ভালো?", 0);
        db.insertTable4(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("আমি ভালো আছি", 0);
        db.insertTable4(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("অনেকদিন পর এলেন", 0);
        db.insertTable4(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("খেয়ে যাবেন কিন্তু", 0);
        db.insertTable4(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("আবার আসবেন", 0);
        db.insertTable4(t7.getSentence(),t7.getFreq());
        TableInfo t8 = new TableInfo("খোদা হাফেজ", 0);
        db.insertTable4(t8.getSentence(),t8.getFreq());
    }
    public void addDataTable5(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("amar appointment chhilo", 0);
        db.insertTable5(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("doctor kkhn ashben?", 0);
        db.insertTable5(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("amar serial koto number?", 0);
        db.insertTable5(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("ami notun patient", 0);
        db.insertTable5(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("ami report dekhate eshechhi", 0);
        db.insertTable5(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("visit koto?", 0);
        db.insertTable5(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("doctor ekhane shoptaher kon kon din boshen?", 0);
        db.insertTable5(t7.getSentence(),t7.getFreq());
    }

    public void addDataBanglaTable5(Context context) {
        Database db = new Database(context);
        TableInfo t1 = new TableInfo("আমার এপয়েটমেন্ট ছিল ", 0);
        db.insertTable5(t1.getSentence(),t1.getFreq());
        TableInfo t2 = new TableInfo("ডক্টর কখন আসবেন?", 0);
        db.insertTable5(t2.getSentence(),t2.getFreq());
        TableInfo t3 = new TableInfo("আমার সিরিয়াল কত?", 0);
        db.insertTable5(t3.getSentence(),t3.getFreq());
        TableInfo t4 = new TableInfo("আমি নতুন পেশেন্ট", 0);
        db.insertTable5(t4.getSentence(),t4.getFreq());
        TableInfo t5 = new TableInfo("আমি রেপরত দেখাতে এসেছি", 0);
        db.insertTable5(t5.getSentence(),t5.getFreq());
        TableInfo t6 = new TableInfo("ভিসিট কত?", 0);
        db.insertTable5(t6.getSentence(),t6.getFreq());
        TableInfo t7 = new TableInfo("ডক্টর এখানে সপ্তাহের কোন কোন দিন বসেন?", 0);
        db.insertTable5(t7.getSentence(),t7.getFreq());
    }




}
