/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SWE_Login_AddStaff_Module;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.LocalDateTime;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 *
 * @author ChrisGoodman
 */
public class scanGUISplash extends JWindow 
{
    private int duration;
    private JPanel mainPanel;
    private JLabel iconLabel;
    private JLabel textLabel;
    private ImageIcon Image;
  
    //-- Constructor with param for duration of the splash time
    scanGUISplash(int x) 
    {
        duration = x;
        showSplash();
    }

    
    public void showSplash() 
    {
        JPanel content = (JPanel)getContentPane();
        content.setBackground(Color.LIGHT_GRAY);

        //-- Set the window's bounds, centering the window --
        int width = 550;
        int height =350;
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width-width)/2;
        int y = (screen.height-height)/2;
        setBounds(x,y,width,height);

        // Build the splash screen -- Shows Scan Time --
        textLabel = new JLabel
        ("Thank's for coming to class today! Scanned in: " + LocalDateTime.now(), JLabel.CENTER);
        
        textLabel.setFont(new Font("Sans-Serif", Font.BOLD, 14));

        Image = new ImageIcon(getClass().getResource("/images/success.jpg"));   
        iconLabel = new JLabel(Image);
        
        // -- Add panels --
        add(iconLabel, BorderLayout.NORTH);
        add(textLabel, BorderLayout.SOUTH);
        
        content.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 6));

        // -- SHOW splash --
        setVisible(true);

        // -- Wait a little while, maybe while loading resources --
        try { Thread.sleep(duration); } catch (Exception e) {}

        // -- HIDE Splash --
        setVisible(false);
  }
}//End Class