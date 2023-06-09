package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Gmail extends Email {
    private ArrayList<Mail>inbox = new ArrayList<>();

    private ArrayList<Mail>trash = new ArrayList<>();

    private int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);

        this.inboxCapacity = inboxCapacity;

    }

    public ArrayList<Mail> getInbox() {
        return inbox;
    }

    public void setInbox(ArrayList<Mail> inbox) {
        this.inbox = inbox;
    }

    public ArrayList<Mail> getTrash() {
        return trash;
    }

    public void setTrash(ArrayList<Mail> trash) {
        this.trash = trash;
    }

    public void setInboxCapacity(int inboxCapacity) {
        this.inboxCapacity = inboxCapacity;
    }

    public void receiveMail(Date date, String sender, String message){
        if(inbox.size() == inboxCapacity) {
            Mail mail = inbox.get(0);
            trash.add(mail);
            inbox.remove(0);
        }
            Mail newmail = new Mail(date,sender,message);
            inbox.add(newmail);

        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

    }

    public void deleteMail(String message){
        for(int i = 0;i<inbox.size();i++){
            if(inbox.get(i).getMessage().equals(message)){
                trash.add(inbox.get(i));
                inbox.remove(i);
                break;
            }
        }
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage(){
        if(inbox.isEmpty()){
            return null;
        }else{
            return inbox.get(inbox.size()-1).getMessage();

        }
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox

    }

    public String findOldestMessage(){
        if(inbox.isEmpty()){
            return null;
        }
        else{
            return inbox.get(0).getMessage();

        }

        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox

    }

    public int findMailsBetweenDates(Date start, Date end){
        int cnt = 0;
        for(Mail mail : inbox){
            if(mail.getDate().compareTo(start) >=0 && mail.getDate().compareTo(end)<=0){
                cnt++;

            }
        }
        return cnt;
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();

    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        return this.inboxCapacity;
        // Return the maximum number of mails that can be stored in the inbox
    }
}
