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
public class Bola extends Pixel {

    public Bola(int x, int y) {
        super(x, y);//Mengakses constructor superclass (pixel) oleh subclass (bola) dan lsg di set nilai xy bola 
        URL loc = this.getClass().getResource("bola.jpg");
        ImageIcon g = new ImageIcon(loc);
        Image image = g.getImage();
        this.setImage(image);
    }

    public void Gerak(int x, int y) {
        int nx = this.getPosisiX()+ x;//bergerak kiri atau kanan, tergantung nilai x jika negative maka ke kiri, positive maka ke kanan
        int ny = this.getPosisiY() + y;//bergerak atas atau bawah, tergantung nilai y jika negative maka ke atas, positive maka ke bawah
        this.setPosisiX(nx);
        this.setPosisiY(ny);
    }
}
