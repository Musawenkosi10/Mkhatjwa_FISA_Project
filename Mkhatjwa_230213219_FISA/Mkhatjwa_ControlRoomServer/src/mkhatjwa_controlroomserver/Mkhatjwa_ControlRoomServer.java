package mkhatjwa_controlroomserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class Mkhatjwa_ControlRoomServer {
    public static void main(String[] args){
        String clientType="";
        String address="No alarms";
        boolean dispatch=false;
        String guardAction="";
        try{
            ServerSocket ss=new ServerSocket(4321);
            System.out.println("server started...");
            while(true){
                Socket s=ss.accept();
                DataInputStream dis=new DataInputStream(s.getInputStream());
                DataOutputStream dos=new DataOutputStream(s.getOutputStream());
                clientType=dis.readUTF();
                if(clientType.equalsIgnoreCase("Home")){
                    address=dis.readUTF();
                    System.out.println("home sent: "+address);
                    if(!address.equalsIgnoreCase("No alarms")){
                        int x=JOptionPane.showConfirmDialog(null,"Must a guard be dispatched to the client?","Dispatch",JOptionPane.YES_NO_CANCEL_OPTION);
                        if(x==JOptionPane.YES_OPTION){
                            dispatch=true;
                        }else{
                            dispatch=false;
                        }
                    }
                }else if(clientType.equalsIgnoreCase("Guard")){
                    if(dispatch){
                        dos.writeUTF(address);
                    }else{
                        dos.writeUTF("No alarms");
                    }
                    dos.flush();
                    guardAction=dis.readUTF();
                    if(!guardAction.contains("No action")){
                        System.out.println("guard: "+guardAction);
                    }
                    if(guardAction.equalsIgnoreCase("House is save")||guardAction.equalsIgnoreCase("House is saved")){
                        address="No alarms";
                        dispatch=false;
                        System.out.println("alarm cleared");
                    }
                }
                dis.close();
                dos.close();
                s.close();
            }
        }catch(IOException e){
            System.out.println("error: "+e.getMessage());
        }
    }
}
