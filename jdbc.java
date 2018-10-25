import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.*;
import java.sql.*;
public class jdbc extends Frame implements ActionListener
{
Button b1;
TextField tf1,tf2,tf3,tf4;
String msg;
public jdbc()
{msg=" ";
b1=new Button("sign for petition");
tf1=new TextField(20);
tf2=new TextField(20);
tf3=new TextField(3);

setVisible(true);
setSize(500,500);
add(new Label("NAME"));
add(tf1);
add(new Label("EMAIL"));
add(tf2);
add(new Label("AGE"));
add(tf3);
add(b1);

b1.addActionListener(this);
setLayout(new FlowLayout());
}
public void actionPerformed(ActionEvent ae)
{if(ae.getSource()==b1)
	{
 String username="system";
 String password="charan1999";
String msg2=tf2.getText(); 
String msg1=tf1.getText();
String msg3=tf3.getText();
int ag=Integer.parseInt(msg3);
try
{
String url="jdbc:oracle:thin:@localhost:1521:xe";
 Class.forName("oracle.jdbc.driver.OracleDriver"); 
 Connection conn=DriverManager.getConnection(url,username,password);
Statement state = conn.createStatement();

String sql="insert into judica values(?,?,?)";
PreparedStatement stat = conn.prepareStatement(sql);        
stat.setString(1,msg1);
stat.setString(2,msg2);
stat.setInt(3,ag);
   stat.execute();

String sqll="select * from judica";

       ResultSet result = stat.executeQuery(sqll);
       while (result.next())
       {
msg=msg+result.getString(1)+" |"+result.getString(2)+"|"+result.getString(3);
           
                                               
                                           try
                                       {
                                       File fou=new File("messag.txt");
                                       FileWriter fout=new FileWriter(fou);
                                      fout.write(msg);
                                     }catch(IOException ioe)
                                    {ioe.printStackTrace();
		                              }           
              
		}    
       conn.close();
stat.close();
state.close();
	}
catch(Exception c)
{}
}
}
	
public static void main(String args[])
{
jdbc obj=new jdbc();
obj.setTitle("JUSTICE FOR ASIFA");
obj.setVisible(true);
}

}