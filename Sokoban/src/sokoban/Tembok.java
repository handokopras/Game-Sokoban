/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author Aweng
 */
public class Tembok extends Pixel {
    public Tembok(int x, int y) {
        super(x, y);//Mengakses constructor superclass (pixel) oleh subclass (Tembok) dan lsg di set nilai xy Tembok 
        URL loc = this.getClass().getResource("tembok.jpg");
        ImageIcon g = new ImageIcon(loc);
        Image image = g.getImage();
        this.setImage(image);

    }
}
