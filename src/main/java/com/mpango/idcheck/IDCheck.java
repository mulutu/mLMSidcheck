/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpango.idcheck;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmulutu
 */
public class IDCheck {

    ServerSocket s;

    public void go() {
        try {
            s = new ServerSocket(4444);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            Socket incoming;
            try {
                incoming = s.accept();
                Thread t = new Thread(new MyCon(incoming, s ));
                t.start();
            } catch (IOException ex) {
                Logger.getLogger(IDCheck.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        new IDCheck().go();
    }
}
