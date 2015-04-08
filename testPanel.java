/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author ChrisGoodman
 */
public class testPanel extends JPanel
{
    
    testPanel()
    {
		this.setSize(500, 550);
		setLayout(new FlowLayout());
                
                JButton jb = new JButton("Testing");
                add(jb);
        
	
        this.setVisible(true);
    }
    
}

