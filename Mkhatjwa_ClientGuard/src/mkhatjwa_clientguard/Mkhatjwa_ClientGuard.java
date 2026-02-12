package mkhatjwa_clientguard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Mkhatjwa_ClientGuard extends JFrame implements ActionListener {
    JLabel label1=new JLabel("Waiting...");
    JButton btn1=new JButton();
    JButton btn2=new JButton();
    String address="No alarms";
    char phase=0;

    public Mkhatjwa_ClientGuard(){
        setTitle("Guard");
        setSize(300,220);
        setLayout(null);
        label1.setBounds(30,20,240,30);
        btn1.setBounds(30,70,200,30);
        btn2.setBounds(30,110,200,30);
        add(label1);
        add(btn1);
        add(btn2);
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn1.setVisible(false);
        btn2.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        Timer t=new Timer(3000,e->poll());
        t.start();
    }

    void poll(){
        try{
            Socket s=new Socket("localhost",4321);
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            DataInputStream dis=new DataInputStream(s.getInputStream());
            dos.writeUTF("Guard");
            dos.flush();
            address=dis.readUTF();
            label1.setText(address);
            if(address.equals("No alarms")){
                btn1.setVisible(false);
                btn2.setVisible(false);
                dos.writeUTF("No action");
            }else{
                if(phase==0){
                    btn1.setText("On my way");
                    btn1.setVisible(true);
                    btn2.setVisible(false);
                    dos.writeUTF("No action");
                }else if(phase==1){
                    btn1.setText("Arrived");
                    btn1.setVisible(true);
                    btn2.setVisible(false);
                    dos.writeUTF("No action");
                }else if(phase==2){
                    btn1.setText("House is save");
                    btn2.setText("Send backup");
                    btn1.setVisible(true);
                    btn2.setVisible(true);
                    dos.writeUTF("No action");
                }
            }
            dos.flush();
            dis.close();
            dos.close();
            s.close();
        }catch(IOException e){
            System.out.println("guard poll error: "+e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e){
        try{
            Socket s=new Socket("localhost",4321);
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            DataInputStream dis=new DataInputStream(s.getInputStream());
            dos.writeUTF("Guard");
            dos.flush();
            address=dis.readUTF();
            label1.setText(address);
            if(e.getSource()==btn1){
                if(phase==0){
                    dos.writeUTF("Guard on his way");
                    btn1.setText("Arrived");
                    phase=1;
                }else if(phase==1){
                    dos.writeUTF("Guard arrived");
                    btn1.setText("House is save");
                    btn2.setText("Send backup");
                    btn2.setVisible(true);
                    phase=2;
                }else if(phase==2){
                    dos.writeUTF("House is save");
                    btn1.setVisible(false);
                    btn2.setVisible(false);
                    phase=0;
                }
            }else if(e.getSource()==btn2){
                dos.writeUTF("Send backup");
            }
            dos.flush();
            dis.close();
            dos.close();
            s.close();
        }catch(IOException ex){
            System.out.println("action error: "+ex.getMessage());
        }
    }

    public static void main(String[] args){
        new Mkhatjwa_ClientGuard();
    }
}



