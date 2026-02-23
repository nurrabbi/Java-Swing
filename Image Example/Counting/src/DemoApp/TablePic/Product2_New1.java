/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoApp.TablePic;

/**
 *
 * @author Ud
 */
public class Product2 {

    private String id;
    private String name;

    private byte[] Image;

    public Product2() {
    }

    public Product2(String Id, String Name, byte[] image) {

        this.id = Id;
        this.name = Name;

        this.Image = image;

    }

    public String getID() {
        return id;
    }

    public void setID(String ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public byte[] getMyImage() {
        return Image;
    }
}
