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
import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author Pete
 */
public class WaitlistQueries {
    private static Connection connection;
    private static ArrayList<String> waitlistbyDate = new ArrayList<String>();
    private static ArrayList<Integer> waitlistByFaculty = new ArrayList<Integer>();
    private static PreparedStatement getWaitlistByDate;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement cancelWaitlistEntry;
    private static PreparedStatement deleteWaitlistEntry;
    private static ResultSet resultSet;

    
      public static ArrayList<String> getWaitlistSeatsByDate(Date date)
    {
    
        connection = DBConnection.getConnection();
        ArrayList<String> seats = new ArrayList<String>();
        try
        {
            getWaitlistByDate = connection.prepareStatement("select (seats) from waitlist where DATE = (?) order by date, timestamp");
            getWaitlistByDate.setDate(1,date);
            resultSet = getWaitlistByDate.executeQuery();
            
            while(resultSet.next())
            {
         
                seats.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return seats;
        
    
}
           
       public static ArrayList<String> getWaitListByFaculty(String faculty)
    {
      
    
        connection = DBConnection.getConnection();
        ArrayList<String> waitlistByFaculty = new ArrayList<String>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select (date,seats) from waitlist where FACULTY = (?) order by date,timestamp");
            getWaitlistByFaculty.setString(1,faculty);
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                String out;
                out = faculty + " waitlisted for  " + resultSet.getDate(1)+ " for " + resultSet.getInt(2) + " seats";
                waitlistByFaculty.add(out);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlistByFaculty;
    
}  
       //work!
      public static void addWaitlistEntry(String faculty, Date date, int seats)
    {
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        connection = DBConnection.getConnection();
        try
        {
            addWaitlistEntry = connection.prepareStatement("insert into waitlist (faculty, date, seats, timestamp) values (?, ?, ?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setDate(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4,currentTimestamp);
            addWaitlistEntry.executeUpdate();

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
      
        public static void cancelWaitlistEntry(String faculty, Date date)
    {
        connection = DBConnection.getConnection();
        try
        {
            cancelWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = (?) and date = (?)");
            cancelWaitlistEntry.setString(1, faculty);
            cancelWaitlistEntry.setDate(2,date);
            cancelWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
    }
        //delete *?
      public static void deleteWaitlistEntry(String faculty, Date date)
    {
        connection = DBConnection.getConnection();
        try
        {
            deleteWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = (?) and date = (?)");
            deleteWaitlistEntry.setString(1, faculty);
            deleteWaitlistEntry.setDate(2,date);
            deleteWaitlistEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
            
        }
    }
        public static ArrayList<String> getWaitlist()
    {
      
    
        connection = DBConnection.getConnection();
        ArrayList<String> waitlist = new ArrayList<String>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select faculty, date, seats from waitlist order by date,timestamp");
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                String out;
                out = resultSet.getString(1) + " for "+ resultSet.getDate(2) + " "+ resultSet.getInt(3) + " seats";
                waitlist.add(out);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
}
     public static ArrayList<WaitlistEntry> getWaitlistEntries()
    {
      
    
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select faculty, date, seats, timestamp from waitlist order by date,timestamp");
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                WaitlistEntry wlEntry;
                wlEntry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlist.add(wlEntry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
}
      public static ArrayList<WaitlistEntry> getWaitlistEntriesByDate(Date date)
    {
      
    
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<WaitlistEntry>();
        try
        {
            getWaitlistByFaculty = connection.prepareStatement("select * from waitlist where date = (?) order by date, timestamp");
            getWaitlistByFaculty.setDate(1,date);
            resultSet = getWaitlistByFaculty.executeQuery();
            
            while(resultSet.next())
            {
                WaitlistEntry wlEntry;
                wlEntry = new WaitlistEntry(resultSet.getString(1), resultSet.getDate(2), resultSet.getInt(3), resultSet.getTimestamp(4));
                waitlist.add(wlEntry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist;
}
}
