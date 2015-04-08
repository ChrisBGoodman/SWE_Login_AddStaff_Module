package SWE_Login_AddStaff_Module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author James, Christian
 *
 */
public class add_staff_GUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image1;
	
        private JLabel label1;
	
        private JPanel panel;
	private JTable table;
        private JPanel addStaffPanel;
        private JPanel staffPanel;
	
        private JFrame frame;
	protected JTabbedPane tabPane;
        
        private JTable staffTable;
        private JScrollPane scrollPane; 
        
        private JButton staffJB;
        private JButton uploadStaffJB;
        private JButton logoutJB;

        
        private AdminStaffController asc;

        private ArrayList<Staff> list; //Will hold a list of all Staff Members. Values will be returned as a string
        
       
	protected JComponent makeTextPanel(String text) 
        {
            JPanel panel = new JPanel(false);
            JLabel filler = new JLabel(text);
            panel.setLayout(new GridLayout(1, 1));
            panel.add(filler);
            return panel;
	}
        
        add_staff_GUI() 
            {
		this.setSize(500, 550);
		setLayout(new FlowLayout());
                
                asc = new AdminStaffController();
                
                //- Panel to hold all add Staff interface --
                addStaffPanel = new JPanel();
                addStaffPanel.setLayout(new BorderLayout());
                addStaffPanel.setPreferredSize(new Dimension(640,440));
		
                staffPanel = new JPanel();
                staffPanel.setPreferredSize(new Dimension(600, 400));

        
                staffPanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(10, 10, 10, 10);
                c.fill = GridBagConstraints.BOTH;
                c.weightx = 1;
                c.weighty = 1;
                staffTable = new JTable(100, 3);
                staffTable.setEnabled(false);
        
                list = new ArrayList<Staff>();             //Add items to staff Table
            
                try{
                    list = asc.getStaffList();
                   
                } catch (SQLException ex)
                {Logger.getLogger(add_staff_GUI.class.getName()).log(Level.SEVERE, null, ex);}
        
                
                //Gathers data from the ArrayList of staff and inserts it into the JTable
                writeStaffTable();
        
                
                // -- Adding Scrolling Table -- 
                scrollPane = new JScrollPane(staffTable);
                staffPanel.add(scrollPane, c);
        
                // -- Add Buttons & Listeners --
                staffJB = new JButton("Add Staff");                
                staffJB.setActionCommand("Add Staff");
                staffJB.addActionListener(asc);

                uploadStaffJB = new JButton("Upload Staff");
                uploadStaffJB.setActionCommand("Upload Staff");
                uploadStaffJB.addActionListener(asc);
                
                // -- Add to Staff Panel --
                addStaffPanel.add(staffPanel, BorderLayout.NORTH);
                addStaffPanel.add(uploadStaffJB, BorderLayout.LINE_START);
                addStaffPanel.add(staffJB, BorderLayout.LINE_END);
                
                add(addStaffPanel);
               
		this.setVisible(true);
            } //End Constructor
        
        
        public final void writeStaffTable()
        {
            staffTable.setValueAt("                    NAME", 0, 0);
            staffTable.setValueAt("                    POSITION", 0, 1);
            staffTable.setValueAt("                    EMAIL", 0, 2);
            int y = 0;
            for (int x = 1; x <= list.size(); x++)
                {
                    y = 0;
                    staffTable.setValueAt(list.get(x-1).name.toString(), x, y);
                    y++;
                    staffTable.setValueAt(list.get(x-1).position.toString(), x, y);
                    y++;
                    staffTable.setValueAt(list.get(x-1).email.toString(), x, y); 
                }
        }      
} //End class
