/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ChrisGoodman
 */
public class logoutGUI extends JPanel
{
    
    private JButton exitJB;
    private JButton userManualJB;
    
    logoutGUI()
    {
        this.setSize(400, 400);
		
                    
        setLayout(new GridBagLayout());

        
        exitJB       = new JButton("Exit");
        userManualJB = new JButton("User Manual");


            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;

            // -- Add Buttons --
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.gridy++;
            gbc.gridy++;
            add(exitJB, gbc);
            gbc.gridy++;
            add(userManualJB, gbc);
            
            //-- Add listeners to buttons --
            //-- LOGOUT --
                     
            
            //-- EXIT --
            exitJB.addActionListener(new ActionListener()     //Action for submit button
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {   
                    System.exit(1);
                }
                
            }); 
                       
        this.setVisible(true);

    
    
    }
}

