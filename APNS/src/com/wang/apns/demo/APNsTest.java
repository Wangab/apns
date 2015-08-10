package com.wang.apns.demo;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import javapns.notification.AppleNotificationServer;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PayloadPerDevice;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import javapns.notification.ResponsePacket;
import javapns.notification.transmission.NotificationProgressListener;
import javapns.notification.transmission.NotificationThread;
import javapns.notification.transmission.NotificationThreads;

public class APNsTest {

    public static void main(String[] args) {
	APNLa();
//	APNsSend();
    }
    
    public static void APNLa()
    {
     	String[] devices = {
     			"fc9d58904ce14457eb4efbe8993c9abd950ff24126a43f6cc26c41ee683925cd",
     			"657b1030847569928b7f250a296ad3b665681e5646c0de1ef4c87a182c94eed0"
     			};


     	try {

    	       
     	List<PushedNotification> notifications = Push.alert("Hello World!", "F:/NQWorkSpace/push.p12", "123456", false, devices); 


     	for (PushedNotification notification : notifications) {
     	     if (notification.isSuccessful()) {
     	             /* Apple accepted the notification and should deliver it */  
     	             System.out.println("Push notification sent successfully to: " +
     	                                             notification.getDevice().getToken());
     	             /* Still need to query the Feedback Service regularly */  
     	     } else {
     	             String invalidToken = notification.getDevice().getToken();
     	             /* Add code here to remove invalidToken from your database */  

     	             /* Find out more about what the problem was */  
     	             Exception theProblem = notification.getException();
     	             theProblem.printStackTrace();

     	             /* If the problem was an error-response packet returned by Apple, get it */  
     	             ResponsePacket theErrorResponse = notification.getResponse();
     	             if (theErrorResponse != null) {
     	                     System.out.println(theErrorResponse.getMessage());
     	             }
     	     }
     	}

     	} catch (KeystoreException e) {
     	/* A critical problem occurred while trying to use your keystore */  
     	e.printStackTrace();

     	} catch (CommunicationException e) {
     	/* A critical communication error occurred while trying to contact Apple servers */  
     	e.printStackTrace();
     	}
    }
}
