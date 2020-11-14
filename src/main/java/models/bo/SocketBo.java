package models.bo;

import jdk.internal.util.xml.impl.Input;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class SocketBo {
    private static SocketBo socketBo;

    private SocketBo(){}

    public static SocketBo getInstance() {
        if(socketBo == null){
            socketBo = new SocketBo();
        }
        return socketBo;
    }

}
