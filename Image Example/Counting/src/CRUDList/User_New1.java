/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUDList;

/**
 *
 * @author Ud
 */
public class User {
    
    private int id;
    private String firstName;
    
    
    public User(int ID, String FirstName)
    {
        this.id = ID;
        this.firstName = FirstName;
        
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    
   
}
