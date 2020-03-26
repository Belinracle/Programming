import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        ServerSocket ssoc=new ServerSocket(7890);
        Socket soc =ssoc.accept();
        Scanner scan= new Scanner(System.in);
        BufferedReader bin = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        BufferedWriter bout = new BufferedWriter(new OutputStreamWriter(soc.getOutputStream()));
        while (true){
            bout.write(scan.nextLine());
            System.out.println(bin.readLine());
            bout.write(scan.nextLine());
        }
    }
}
