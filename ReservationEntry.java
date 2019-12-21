
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pete
 */
public class ReservationEntry {
    private String faculty;
    private String room;
    private Date date;
    private int seats;
    private Timestamp timestamp;
    
    public ReservationEntry(String faculty1, String room1, Date date1, int seats1, Timestamp timestamp1) 
    {
        faculty = faculty1;
        room = room1;
        date = date1;
        seats = seats1;
        timestamp = timestamp1;
       
    }
    public String getFaculty(){
        return faculty;
    }
    public String getRoom() {
        return room;
    }
    public Date getDate(){
        return date;
        
    }
    public int getSeats(){
        return seats;
    }
    public Timestamp getTimestamp(){
        return timestamp;
    }
   

       
        
    }

