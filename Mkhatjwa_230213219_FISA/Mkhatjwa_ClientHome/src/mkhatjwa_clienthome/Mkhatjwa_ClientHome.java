package mkhatjwa_clienthome;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Mkhatjwa_ClientHome extends Application {
    int step=0;
    boolean armed=false;
    String code="132";
    TextField txtAdd=new TextField();

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage st){
        GridPane g=new GridPane();
        g.setPadding(new Insets(10));
        g.setVgap(10);
        g.setHgap(10);
        g.setAlignment(Pos.CENTER);

        Button b1=new Button("1");
        Button b2=new Button("2");
        Button b3=new Button("3");
        Button bArm=new Button("Arm/Disarm");
        Button bMove=new Button("Move");
        txtAdd.setPromptText("Enter house address");

        g.add(txtAdd,0,0,3,1);
        g.add(b1,0,1);
        g.add(b3,1,1);
        g.add(b2,2,1);
        g.add(bArm,0,2);
        g.add(bMove,1,2);

        b1.setOnAction(e->press("1"));
        b2.setOnAction(e->press("2"));
        b3.setOnAction(e->press("3"));
        bArm.setOnAction(e->toggle());
        bMove.setOnAction(e->{
            if(armed) sendAlarm();
            else System.out.println("not armed");
        });

        Scene sc=new Scene(g,300,200);
        st.setScene(sc);
        st.setTitle("Home");
        st.show();
    }

    void press(String n){
        if(code.charAt(step)==n.charAt(0)){
            step++;
            if(step==code.length()){
                toggle();
                step=0;
            }
        }else{
            step=0;
        }
    }

    void toggle(){
        armed=!armed;
        System.out.println("armed: "+armed);
    }

    void sendAlarm(){
        try{
            Socket s=new Socket("localhost",4321);
            DataOutputStream dos=new DataOutputStream(s.getOutputStream());
            dos.writeUTF("Home");
            String adr=txtAdd.getText();
            if(adr==null||adr.trim().isEmpty()) adr="No alarms";
            dos.writeUTF(adr);
            dos.flush();
            dos.close();
            s.close();
            System.out.println("sent alarm for "+adr);
        }catch(IOException e){
            System.out.println("error: "+e.getMessage());
        }
    }
}

