
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//for add room, when new room is added, check if someone on waitlist should be reserved, checktimestamp, delete waitlist if 

//for add date, use date spinner, video on how to in week 13
//drop room: drop room from room table, take any reservations and see if you can give them a new room on that date, if not waitlist, take all reservations and put them on waitlist and see use code to take them off waitlist 
//cancel reservation: delete reservation (or delete off waitlist), check to see if someone else gets a reservation

/**
 *
 * @author Pete
 */
public class DBConnection {

    private static Connection connection;
    private static final String user = "java";
    private static final String password = "java";
    private static final String database = "jdbc:derby://localhost:1527/RoomSchedulerDBStephenDamicosvd5607";

    public static Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(database, user, password);
            } catch (SQLException e)
            {
                e.printStackTrace();
                System.out.println("Could not open database.");
                System.exit(1);

            }
        }
        return connection;
    }

}
    

