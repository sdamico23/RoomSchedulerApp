/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Pete
 */
public class ReservationQueries {
            private static Connection connection;
    private static ArrayList<String> reservationsByDate = new ArrayList<String>();
    private static ArrayList<String> reservationsByFaculty = new ArrayList<String>();
    private static ArrayList<Integer> rooms = new ArrayList<Integer>();
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement cancelReservation;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement deleteReservation;
    private static ResultSet resultSet;


    //returns facult members that have rooms reserved on that date
        public static ArrayList<String> getReservationsByDate(Date date)
    {
        String out;
        connection = DBConnection.getConnection();
        ArrayList<String> reservationsByDate = new ArrayList<String>();
        try
        {
            getReservationsByDate = connection.prepareStatement("select faculty,room from reservations where date = (?)");
            getReservationsByDate.setDate(1,date);
            resultSet = getReservationsByDate.executeQuery();
            
            while(resultSet.next())
            {
               out = resultSet.getString(1) + " reserved " + resultSet.getString(2) ;
               reservationsByDate.add(out);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservationsByDate;
    
}
     
    

   public static ArrayList<WaitlistEntry> getReservedByRoom(String room)
    {
    
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> reservationsByRoom = new ArrayList<WaitlistEntry>();
        try
        {
            getRoomsReservedByDate = connection.prepareStatement("select faculty,date,seats,timestamp from reservations where room = (?)");
            getRoomsReservedByDate.setString(1,room);
            resultSet = getRoomsReservedByDate.executeQuery();
            
            while(resultSet.next())
            {
                
               WaitlistEntry wlEntry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
               reservationsByRoom.add(wlEntry);
            
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservationsByRoom;
        
    
}
        public static ArrayList<String> getJustRoomsReservedByDate(Date date)
    {
    
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            getRoomsReservedByDate = connection.prepareStatement("select (room) from reservations where date = (?)");
            getRoomsReservedByDate.setDate(1,date);
            resultSet = getRoomsReservedByDate.executeQuery();
            
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return rooms;
        
    
}
            //fix
      public static void addReservationEntry(String faculty, Date date, int seats, String room)
    {

        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        connection = DBConnection.getConnection();
        try
        {
            addReservationEntry = connection.prepareStatement("insert into reservations (faculty,date,seats,room, timestamp) values (?,?,?,?,?)");
            addReservationEntry.setString(1, faculty);
            addReservationEntry.setDate(2,date);
            addReservationEntry.setInt(3, seats);
            addReservationEntry.setString(4,room);
            addReservationEntry.setTimestamp(5, currentTimestamp);
            
            addReservationEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
      //needs work faculty, date seats required
        public static void cancelReservation(String faculty, Date date)
    {
        connection = DBConnection.getConnection();
        try
        {
            cancelReservation = connection.prepareStatement("delete from reservations where faculty = (?) and date = (?)");
            cancelReservation.setString(1, faculty);
            cancelReservation.setDate(2,date);
            cancelReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
    }
       
      public static void deleteReservation(String faculty, Date date)
    {
        connection = DBConnection.getConnection();
        try
        {
            deleteReservation = connection.prepareStatement("delete from reservations where date= (?) and faculty = (?)");
            deleteReservation.setDate(1, date);
            deleteReservation.setString(2, faculty);
            deleteReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
    }
      
       public static ArrayList<String> getReservationsByFaculty(String faculty)
    {
        
    
        connection = DBConnection.getConnection();
        ArrayList<String> reservationsByFaculty = new ArrayList<String>();
        String out;
        try
        {
            getReservationsByFaculty = connection.prepareStatement("select (room,date) from reservations where FACULTY = (?)");
            getReservationsByFaculty.setString(1,faculty);
            resultSet = getRoomsReservedByDate.executeQuery();
            
            while(resultSet.next())
            {
               out = faculty + "reserved " + resultSet.getString(1) + " on" + resultSet.getString(2);
                reservationsByFaculty.add(out);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return reservationsByFaculty;
    
}
     public static void cancelReservationByRoom(String room)
    {
        connection = DBConnection.getConnection();
        try
        {
            cancelReservation = connection.prepareStatement("delete from reservations where room = (?)");
            cancelReservation.setString(1, room);
            cancelReservation.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
    }
}
