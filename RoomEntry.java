/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Pete
 */
public class RoomEntry { 
    private String name;
    private int seats;
    
    public RoomEntry(String name1, int seats1){
      name = name1;
      seats = seats1;
    
}
   public String getName(){
       return name;
       
   }
   public int getSeats(){
       return seats;
   }
}
