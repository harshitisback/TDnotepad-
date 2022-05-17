/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notepad;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import  java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author amank
 */


public class NotePad extends JFrame implements ActionListener{

    /**
     * @param args the command line arguments
     */
    JTextArea area;
    JScrollPane pane;
    String text;
    NotePad(){
        setBounds(0,0,1750,950); // setting the bounds of the jFrame
        
        JMenuBar menubar = new JMenuBar(); // created menu bar 
        JMenu file = new JMenu("TDFile"); // menuElement
        
        JMenuItem newdoc = new JMenuItem("New"); // inserting menu items 
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        newdoc.addActionListener(this);
        
        JMenuItem open = new JMenuItem("Open"); // inserting menu items 
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        open.addActionListener(this);
        
        JMenuItem save = new JMenuItem("Save"); // inserting menu items 
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        save.addActionListener(this);
        
        JMenuItem print = new JMenuItem("Print"); // inserting menu items 
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
        
        JMenuItem exit = new JMenuItem("Exit"); // inserting menu items 
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
        exit.addActionListener(this);
        
        // adding menu items in Menu bar Element 
        // adding on file 
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
//        
        
        
        JMenu edit = new JMenu("TDEdit");
        JMenuItem copy = new JMenuItem("Copy"); // inserting menu items 
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        copy.addActionListener(this);
        
        JMenuItem paste = new JMenuItem("Paste"); // inserting menu items 
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        paste.addActionListener(this);
        
        JMenuItem cut = new JMenuItem("Cut"); // inserting menu items 
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
        cut.addActionListener(this);
        
        JMenuItem selectall  = new JMenuItem("Select All"); // inserting menu items 
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        selectall.addActionListener(this);
        
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectall);
        
        JMenu help = new JMenu("TDHelp");
        
        JMenuItem about = new JMenu("About the TDNotepad");
        help.add(about);
        about.addActionListener(this);
        
        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);
        
        setJMenuBar(menubar);
        
        area = new JTextArea();
        area.setFont(new Font("Arial",Font.PLAIN,20));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        
        pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane, BorderLayout.CENTER); 
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if (ae.getActionCommand().equals("New")) {
            area.setText("");
        } else if (ae.getActionCommand().equals("Open")) {
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restric = new FileNameExtensionFilter("Only .txt files","txt");
            chooser.addChoosableFileFilter(restric);
            
            int action = chooser.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            
            File file = chooser.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
                
            } catch (Exception e) {
                
            }
                       
        }else if (ae.getActionCommand().equals("Save")) {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File filename = new File(saveas.getSelectedFile()+ ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (Exception e) {
            }
        } else if (ae.getActionCommand().equals("Print")) {
            try {
                area.print();
            } catch (Exception e) {
                
            } 
        } else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        }else if (ae.getActionCommand().equals("Copy")){ 
            text = area.getSelectedText(); // it will copy selected text
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if (ae.getActionCommand().equals("Select All")) {
                area.selectAll();
            
        } else if (ae.getActionCommand().equals("About the TDNotepad")) {
            new About().setVisible(true);
        }
    }

    public static void main(String[] args) {
        new NotePad().setVisible(true);
        
    }
    
}
