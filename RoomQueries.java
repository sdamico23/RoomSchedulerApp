
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class RoomQueries {
        private static Connection connection;
    private static ArrayList<String> names = new ArrayList<String>();
    private static ArrayList<Integer> seats = new ArrayList<Integer>();
    private static PreparedStatement addRoom;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement dropRoom;
    private static PreparedStatement allRooms;
    private static ResultSet resultSet;
 //does the table name need to be capitalized?
    //does this method return name and seats?
        public static ArrayList<String> getAllPossibleRooms(int seats)
    {
        connection = DBConnection.getConnection();
        ArrayList<String> names = new ArrayList<String>();
        try
        {
            getAllPossibleRooms = connection.prepareStatement("select name from rooms where seats >=(?)order by seats");
             getAllPossibleRooms.setInt(1, seats);
            resultSet = getAllPossibleRooms.executeQuery();
            
            while(resultSet.next())
            {
                names.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return names;
    
}
        
      public static void addRoom(String room, int seats)
    {
        connection = DBConnection.getConnection();
        try
        {
            addRoom = connection.prepareStatement("insert into rooms (name,seats) values (?,?)");
            addRoom.setString(1, room);
            addRoom.setInt(2,seats);
            addRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }

     public static void dropRoom(String room)
    {
        connection = DBConnection.getConnection();
        try
        {
            dropRoom = connection.prepareStatement("delete from rooms where name=(?)");
            dropRoom.setString(1,room);
            dropRoom.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
     public static ArrayList<String> getAllRooms(){
        connection = DBConnection.getConnection();
         ArrayList<String> rooms = new ArrayList<String>();
        try
        {
            allRooms = connection.prepareStatement("select (NAME) from rooms");
            resultSet = allRooms.executeQuery();
            
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
     
}