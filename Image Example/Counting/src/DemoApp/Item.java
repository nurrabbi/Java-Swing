/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DemoApp;

/**
 *
 * @author Ud
 */
public class Item {

    private String id;
    private String name;

    private byte[] image;

    public Item(String ID, String NAME, byte[] IMAGE) {
        this.id = ID;
        this.name = NAME;

        this.image = IMAGE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public byte[] getImage() {
        return image;
    }
}
