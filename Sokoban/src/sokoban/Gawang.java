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
 * @author admin
 */
public class Gawang extends Pixel {

    public Gawang(int x, int y) {
        super(x, y);//Mengakses constructor superclass (pixel) oleh subclass (Gawang) dan lsg di set nilai xy Gawang 
        URL loc = this.getClass().getResource("box.png");
        ImageIcon g = new ImageIcon(loc);
        Image image = g.getImage();
        this.setImage(image);
    }
}
