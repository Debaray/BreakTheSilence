package com.example.scray.breakthesilencev3.Fragments;

import com.example.scray.breakthesilencev3.Notifications.MyResponse;
import com.example.scray.breakthesilencev3.Notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAABrhKJUU:APA91bHX8dTluiYTIzJ0SqOAFY-H6kXgQoT-SFdJUwuIp-HFDw1P2s5c17VkQdj_65wSwS1jLjV9YifPVYfI_HuI3TztWSw4n2ODry-CO0RjF_FvjiRylG54tY00HYR0ha7bv78I19m5"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);

    @POST("fcm/send")
    Call<MyResponse> sendNotificationRequest(@Body Sender sender);
}
