package Func;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import GUI.Client;

public class MyApp {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.setVisible(true);
	}
}
