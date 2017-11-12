/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpango.idcheck;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author jmulutu
 */
public class MyCon implements Runnable {

    Socket incoming;
    ServerSocket s;

    public MyCon(Socket incoming, ServerSocket s) {
        this.incoming = incoming;
        this.s = s;
    }

    @Override
    public void run() {
        process();
    }

    public String processRequest(String requestData) {
        String result = null;
        if (requestData.equals("22413338")) {
            result = "ID|22413338|JACKSON|MULUTU AKIDUA|12-02-1982|KENYAN|NAIROBI";
        }
        return result;
    }

    public void process() {
        try {
            DataInputStream din = new DataInputStream(incoming.getInputStream());
            DataOutputStream dout = new DataOutputStream(incoming.getOutputStream());
            String inp = null;
            boolean isDone = true;

            while (isDone && ((inp = din.readUTF()) != null)) {
                if (inp.trim().equals("BYE")) {
                    System.out.println("THANKS FOR CONNECTING...Bye for now");
                    isDone = false;
                    s.close();
                }
                String requestdata = inp.trim();
                String result = processRequest(requestdata);
                dout.writeUTF(result);
                //incoming.close();
                dout.flush();
                //din.close();
                isDone = false;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            try {
                s.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

}
