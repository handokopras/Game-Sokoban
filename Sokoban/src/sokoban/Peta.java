/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Aweng
 */
public class Peta extends JPanel {

    private ArrayList tembok = new ArrayList();
    private ArrayList bola = new ArrayList();
    private ArrayList gawang = new ArrayList();
    private ArrayList map = new ArrayList();
    private Pemain soko;
    private int lebar = 0;
    private int tinggi = 0;
    private int jarak = 20;

    private File Alamatpeta;
    private ArrayList Allperintah = new ArrayList();

    public Peta(File file) {
        setPeta(file);
    }

    public void setPeta(File file) {
        try {
            if (file != null) {
                FileInputStream input = new FileInputStream(file);
                Alamatpeta = file;
                int posisiX = 0;
                int posisiY = 0;
                Tembok wall;
                Bola b;
                Gawang a;
                int data;
                while ((data = input.read()) != -1) {
                    char item = (char) data;
                    if (item == '\n') {
                        posisiY += jarak;
                        lebar = posisiX;
                        posisiX = 0;
                    } else if (item == '#') {
                        wall = new Tembok(posisiX, posisiY);
                        tembok.add(wall);
                        posisiX += jarak;
                    } else if (item == 'x') {
                        b = new Bola(posisiX, posisiY);
                        bola.add(b);
                        posisiX += jarak;
                    } else if (item == 'o') {
                        a = new Gawang(posisiX, posisiY);
                        gawang.add(a);
                        posisiX += jarak;
                    } else if (item == '@') {
                        soko = new Pemain(posisiX, posisiY);
                        posisiX += jarak;
                    } else if (item == '.') {
                        posisiX += jarak;
                    }
                    tinggi = posisiY;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Peta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);	   
        
        g.setColor(new Color(255, 255, 255));
        g.fillRect(0, 0, this.getLebar(), this.getTinggi());
        map.addAll(tembok);
        map.addAll(gawang);
        map.addAll(bola);
        map.add(soko);
        for (int i = 0; i < map.size(); i++) {
            if (map.get(i) != null) {
                Pixel item = (Pixel) map.get(i);
                g.drawImage(item.getImage(), item.getPosisiX(), item.getPosisiY(), this);
            }
        }
    }

    public int getLebar() {
        return this.lebar;
    }

    public int getTinggi() {
        return this.tinggi;
    }

    public void PerintahGerak(String input) {
        String in[] = input.split(" ");
        if (in.length > 2) {
            JOptionPane.showMessageDialog(null, "Jumlah kata lebih dari 2");
        } else if (in.length == 2) {
            if (in[0].matches("[udrlz]")) {
                Allperintah.add(input);
                if (in[0].equalsIgnoreCase("u")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(soko, "u")) {
                            return;
                        } else if (cekBolaPemainTembok("u")) {
                            return;
                        } else {
                            soko.Gerak(0, -jarak);
                            repaint();
                        }

                    }
                } else if (in[0].equalsIgnoreCase("d")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(soko, "d")) {
                            return;
                        } else if (cekBolaPemainTembok("d")) {
                            return;
                        } else {
                            soko.Gerak(0, jarak);
                            repaint();
                        }
                    }
                } else if (in[0].equalsIgnoreCase("r")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(soko, "r")) {
                            return;
                        } else if (cekBolaPemainTembok("r")) {
                            return;
                        } else {
                            soko.Gerak(jarak, 0);
                            repaint();
                        }
                    }
                } else if (in[0].equalsIgnoreCase("l")) {
                    for (int i = 0; i < Integer.parseInt(String.valueOf(in[1])); i++) {
                        if (cekObjekNabrakTembok(soko, "l")) {
                            return;
                        } else if (cekBolaPemainTembok("l")) {
                            return;
                        } else {
                            soko.Gerak(-jarak, 0);
                            repaint();
                        }
                    }
                } else if (in[0].equalsIgnoreCase("z")) {
//                    return "undo " + in[1];
                } else {
                    JOptionPane.showMessageDialog(null, "Kata Tidak Dikenal");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Kata Tidak Dikenal");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Jumlah kata hanya satu");
        }
    }

    private boolean cekObjekNabrakTembok(Pixel pemain, String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);
                if (pemain.PosisiKiriObjek(wall)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);
                if (pemain.PosisiKananObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);
                if (pemain.PosisiAtasObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < tembok.size(); i++) {
                Tembok wall = (Tembok) tembok.get(i);
                if (pemain.PosisiBawahObjek(wall)) {
                    bantu = true;
                    break;
                }
            }
        }
        return bantu;
    }

    private boolean cekBolaNabrakBola(Pixel objek, String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol = (Bola) bola.get(i);
                if (objek.PosisiKiriObjek(bol)) {
                    bantu = true;
                    break;
                }
            }
        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol = (Bola) bola.get(i);
                if (objek.PosisiKananObjek(bol)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol = (Bola) bola.get(i);
                if (objek.PosisiAtasObjek(bol)) {
                    bantu = true;
                    break;
                }
            }

        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol = (Bola) bola.get(i);
                if (objek.PosisiBawahObjek(bol)) {
                    bantu = true;
                    break;
                }
            }
        }
        return bantu;
    }

    private boolean cekBolaPemainTembok(String input) {
        boolean bantu = false;
        if (input.equalsIgnoreCase("l")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol1 = (Bola) bola.get(i);
                if (soko.PosisiKiriObjek(bol1)) {
                    if (cekBolaNabrakBola(bol1, "l")) {
                        bantu = true;
                        break;
                    } else if (cekObjekNabrakTembok(bol1, "l")) {
                        bantu = true;
                        break;
                    } else {
                        bol1.Gerak(-jarak, 0);
                        isCompleted();
                    }
                }
            }
        } else if (input.equalsIgnoreCase("r")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol1 = (Bola) bola.get(i);
                if (soko.PosisiKananObjek(bol1)) {
                    if (cekBolaNabrakBola(bol1, "r")) {
                        bantu = true;
                        break;
                    } else if (cekObjekNabrakTembok(bol1, "r")) {
                        bantu = true;
                        break;
                    } else {
                        bol1.Gerak(jarak, 0);
                        isCompleted();
                    }
                }
            }
        } else if (input.equalsIgnoreCase("u")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol1 = (Bola) bola.get(i);
                if (soko.PosisiAtasObjek(bol1)) {
                    if (cekBolaNabrakBola(bol1, "u")) {
                        bantu = true;
                        break;
                    } else if (cekObjekNabrakTembok(bol1, "u")) {
                        bantu = true;
                        break;
                    } else {
                        bol1.Gerak(0, -jarak);
                        isCompleted();
                    }
                }
            }
        } else if (input.equalsIgnoreCase("d")) {
            for (int i = 0; i < bola.size(); i++) {
                Bola bol1 = (Bola) bola.get(i);
                if (soko.PosisiBawahObjek(bol1)) {
                    if (cekBolaNabrakBola(bol1, "d")) {
                        bantu = true;
                        break;
                    } else if (cekObjekNabrakTembok(bol1, "d")) {
                        bantu = true;
                        break;
                    } else {

                        bol1.Gerak(0, jarak);;
                        isCompleted();
                    }
                }
            }
        }
        return bantu;
    }

    public void isCompleted() {
        int jumBola = bola.size();
        int goal = 0;
        for (int i = 0; i < bola.size(); i++) {
            Bola bol = (Bola) bola.get(i);
            for (int j = 0; j < gawang.size(); j++) {
                Gawang gaw = (Gawang) gawang.get(j);
                if (bol.getPosisiX() == gaw.getPosisiX() && bol.getPosisiY() == gaw.getPosisiY()) {
                    goal += 1;
                }
            }
        }
        if (goal == jumBola) {
            JOptionPane.showMessageDialog(null, "Selamat anda berhasil menyelesaikan game ini.");
        }
    }

    public void restartLevel() {
        Allperintah.clear();
        gawang.clear();
        bola.clear();
        tembok.clear();
        map.clear();
        setPeta(Alamatpeta);
        repaint();
    }

    public String getTeksPerintah() {
        String bantu = "";
        for (int i = 0; i < Allperintah.size(); i++) {
            bantu = bantu + Allperintah.get(i) + " ";
        }
        return bantu;
    }
}
