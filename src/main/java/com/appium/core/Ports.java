package com.appium.core;
import java.net.ServerSocket;

public class Ports {
	/**
	 * The getPort() method returns the Local port number 
	 * 
	 * @throws Exception
	 */
    public String getPort() throws Exception
    {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        String port = Integer.toString(socket.getLocalPort());
        socket.close();
        return port;
    }

}