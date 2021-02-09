import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class AnaMenu extends JPanel implements ActionListener {
	JTextField cityName;
	JButton b1,b2;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel model;
	static JFrame f;


public AnaMenu() throws SQLException {
	
	
	setLayout(null);
	setOpaque(true);
	
	cityName = new JTextField("Enter city name");
	cityName.setSize(115,20);
	cityName.setLocation(0,0);
	cityName.setOpaque(true);
	b1=new JButton("Add"); 
	b1.setSize(100,20);
	b1.setLocation(118, 0);
	b2=new JButton("Query the selected one"); 
	b2.setSize(200, 20);
	b2.setLocation(222, 0);
	
	
	b1.addActionListener(this);
	b2.addActionListener(this);
	
	

	add(cityName);
	add(b1);
	add(b2);
	
	
	
	cityName.addMouseListener(new MouseAdapter(){
	    
	    public void mouseClicked(MouseEvent e){
	    	cityName.setText("");
	    }
	});
	 

}



public static void main(String[] args) throws SQLException {
	
	f=new JFrame("Weather Forecast");
	AnaMenu panel1 = new AnaMenu();
	f.add(panel1);
	f.setSize(800,600);
	panel1.createTable();
	f.setVisible(true);

	
}


public void createTable() throws SQLException {
	
	Connection connection = null;
	Statement statement;
    	String query;
    	ResultSet rs;
	    
	    Object rowData[][] = {{"", "", "",""}};
	    Object columnNames[] = {"ID", "City", "Temperature","Type"};
	 
	    
	    model = new DefaultTableModel(rowData, columnNames);
	    table = new JTable(model);
	   
	    
	    try {
	    	connection = DriverManager.getConnection("jdbc:sqlite:database_path");
			statement = connection.createStatement();
	    } catch (Exception e1) {
	      System.err.println("Exception: " + e1.getMessage());
	    }
	    

		    query = "SELECT * FROM veri";
		    statement = connection.createStatement();
		    rs = statement.executeQuery(query);
		
		    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    scrollPane = new JScrollPane(table);
		    f.add(scrollPane, BorderLayout.AFTER_LAST_LINE);
		    f.setVisible(true);
		 
		    
		    model.removeRow(0);
		    Object[] rows;
		    
			while (rs.next()) {
			 
			  rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4)};
			  model.addRow(rows);
			  
			 
			  
			  }
				
			}

public void actionPerformed(ActionEvent e) {
	
	String hostAddress="https://api.weatherapi.com/v1/current.json";
	String data="key=API_key&q=";		//API key
	Object obj;
	
	
	
	if(e.getActionCommand().equalsIgnoreCase("Query the selected one")) {
		
		Connect c=new Connect();
		
		
		try {
			
			obj=new JSONParser().parse(JSONRead.sendGetRequest(hostAddress, data,(String) table.getValueAt(table.getSelectedRow(), 1)));
			
			System.out.println(table.getSelectedRow());
			JSONObject jo = (JSONObject) obj ;
			JSONObject location = (JSONObject) jo.get("current");
			double temp = (double) location.get("temp_c");    
			JSONObject condition = (JSONObject) location.get("condition");  
			String type = (String) condition.get("text"); 

			
			c.Update(temp,type,(String)table.getValueAt(table.getSelectedRow(), 1));
			AnaMenu panel1 = new AnaMenu();
			panel1.createTable();
			panel1.recreate();
			

			System.out.println(temp);
			System.out.println(type);

			
		} catch (ParseException | SQLException e1) {
			e1.printStackTrace();
			}
		}


	else if(e.getActionCommand().equalsIgnoreCase("Add")) {
			Connect c=new Connect();
			try {
				
				obj=new JSONParser().parse(JSONRead.sendGetRequest(hostAddress, data, cityName.getText()));
				JSONObject jo = (JSONObject) obj;

				JSONObject location = (JSONObject) jo.get("current");
		        double temp = (double) location.get("temp_c");    
		        JSONObject condition = (JSONObject) location.get("condition");  
		        String type = (String) condition.get("text");  
		       
				c.insert(cityName.getText(),temp,type);
				
				AnaMenu panel1 = new AnaMenu();
				
				panel1.createTable();
				panel1.recreate();
				

			} catch (ParseException e1) {
				e1.printStackTrace();
				} catch (SQLException e1) {
				
				e1.printStackTrace();
				}
			}
}


	public void recreate() {

	  try {
		 
		
		 f.getContentPane().removeAll();
		 setLayout(null);
		 f.add(cityName);
		 f.add(b1);
		 f.add(b2);
		 f.add(scrollPane);
		 f.repaint();
		 AnaMenu a =new AnaMenu();
		 f.add(a);
		 f.repaint();
		
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	}
	
}


