package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
import java.awt.print.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class NotePad1 implements ActionListener {

    JFrame jf,jf2,jf3;
    JMenuBar jmr;
    JMenu file,Edit,Format,Help;
    JMenuItem New,open,save,saveAs,exit,print,pageSetup,cut,copy,paste,replace,date,font,fontColor,textareaBck;
    JTextArea jta;
    JScrollPane jsp;
    File file1;
    JFileChooser jfc;
    String str="Untitled_Notepad";
    JButton jb1,jb2;
    JTextField jt3,jt2;
    JLabel jl1,jl;
    LocalDate ld;
    LocalTime lt;
    String []fontFamilies;
    JComboBox jcb,jcb2,jcb3;
    int font_style1;
    JCheckBoxMenuItem wordWrap;

    NotePad1() {
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        //jf.setSize(500,500);
        jf=new JFrame();
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setTitle(str);
        Image icon = Toolkit.getDefaultToolkit().getImage("D:\\panda.png");
        jf.setIconImage(icon);


        jmr=new JMenuBar();

        file=new JMenu("File");

        New=new JMenuItem("New");
        New.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK));
        New.addActionListener(this);
        save=new JMenuItem("save");
        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK));
        save.addActionListener(this);
        open=new JMenuItem("open");
        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK));
        open.addActionListener(this);
        saveAs=new JMenuItem("Save As");
        saveAs.addActionListener(this);
        exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        print=new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_DOWN_MASK));
        print.addActionListener(this);
        pageSetup=new JMenuItem("Page SetUp");
        pageSetup.addActionListener(this);

        file.add(New);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.addSeparator();
        file.add(print);
        file.add(pageSetup);
        file.addSeparator();
        file.add(exit);

        Edit=new JMenu("Edit");

        cut=new JMenuItem("Cut");
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
        cut.addActionListener(this);
        copy=new JMenuItem("Copy");
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        copy.addActionListener(this);
        paste=new JMenuItem("paste");
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
        paste.addActionListener(this);
        replace=new JMenuItem("Replace");
        replace.addActionListener(this);
        date=new JMenuItem("date");
        date.addActionListener(this);

        Edit.add(cut);
        Edit.add(copy);
        Edit.add(paste);
        Edit.add(replace);
        Edit.addSeparator();
        Edit.add(date);

        Format=new JMenu("Format");

        font=new JMenuItem("font");
        font.addActionListener(this);
        fontColor=new JMenuItem("FontColor");
        fontColor.addActionListener(this);
        textareaBck=new JMenuItem("Background");
        textareaBck.addActionListener(this);
        wordWrap=new JCheckBoxMenuItem("WordWrap..");
        wordWrap.addActionListener(this);

        Format.add(font);
        Format.add(fontColor);
        Format.addSeparator();
        Format.add(textareaBck);
        Format.add(wordWrap);
        //cut=new JMenuItem("Cut");

        Help=new JMenu("Help");


        jmr.add(file);
        jmr.add(Edit);
        jmr.add(Format);
        jf.setJMenuBar(jmr);
        jta=new JTextArea();
        jsp=new JScrollPane(jta);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //jsp.add(jta);
        jf.add(jsp);
        jf.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==New){
           New();

        }
        if(e.getSource()==exit){
            System.exit(0);
        }
        if(e.getSource()==save){
            save();
        }
        if(e.getSource()==open){
            open();
        }
        if(e.getSource()==saveAs){
            save_as();
        }
        if(e.getSource()==pageSetup){
            setPageSetup();

        }
        if(e.getSource()==print){
            printPage();
        }
        if(e.getSource()==cut){
            jta.cut();
        }
        if(e.getSource()==copy){
            jta.copy();
        }
        if(e.getSource()==paste){
            jta.paste();
        }
        if(e.getSource()==replace){
            replace();

        }
        if(e.getSource()==jb1){
            String st1=jt2.getText();
            String st2=jt3.getText();
            String text=jta.getText();
            String str3=text.replace(st1,st2);
            jta.setText(str3);
            jf2.setVisible(false);
        }
        if(e.getSource()==date){

            Date_time();
        }
        if(e.getSource()==font){
            fontFrame();
        }
        if(e.getSource()==fontColor){
            setFontColor();

        }
        if(e.getSource()==textareaBck){
            setBackground();
        }
        if(e.getSource()==jb2){
            changeFont();
        }
        if(e.getSource()==wordWrap){
            boolean b=wordWrap.getState();
            jta.setLineWrap(b);
        }
    }
    public void New(){
        //String text=jta.getText();
        int i=JOptionPane.showConfirmDialog(jf,"do you want to save the file?");
        if(i==0){
            save_as();
            if(!jf.getTitle().equals(str)){
                jf.setTitle(str);
                jta.setText(" ");
            }


        }
        else if(i==1){
            jta.setText(" ");
        }

    }
    public void save() {
        if (jf.getTitle().equals(str)) {
            jfc = new JFileChooser();
            int i = jfc.showSaveDialog(jf);
            if (i == 0) {
                try {
                    String str = jta.getText();
                    byte[] b = str.getBytes();
                    file1 = jfc.getSelectedFile();
                    FileOutputStream fos = new FileOutputStream(file1);
                    fos.write(b);
                    fos.close();
                    jf.setTitle(file1.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(jfc, "provide file name", "File not saved", JOptionPane.WARNING_MESSAGE);
            }

        }
    }


    void open(){
        FileInputStream fis;

        try{

            jfc=new JFileChooser();
            int j=jfc.showOpenDialog(jf);
            if(j==0){
                jta.setText(" ");
                jf.setTitle(str);
                fis=new FileInputStream(jfc.getSelectedFile());
                int i;
                while((i=fis.read())!=-1){
                    jta.append(String.valueOf((char)i));

                }
                jf.setTitle(jfc.getSelectedFile().getName());

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }






    }
    void save_as(){
        jfc=new JFileChooser();
        int i=jfc.showSaveDialog(jf);
        if(i==0){
            try{
                String str=jta.getText();
                byte []b=str.getBytes();
                file1=jfc.getSelectedFile();
                FileOutputStream fos=new FileOutputStream(file1);
                fos.write(b);
                fos.close();
                jf.setTitle(file1.getName());
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(jfc,"provide file name","File not saved",JOptionPane.WARNING_MESSAGE);
        }

    }
    public void setPageSetup(){
        PrinterJob pj = PrinterJob.getPrinterJob();
        PageFormat pf = pj.pageDialog(pj.defaultPage());
    }
    public void printPage(){
        PrinterJob pj = PrinterJob.getPrinterJob();
        if (pj.printDialog()) {
            try {pj.print();}
            catch (PrinterException exc) {
                System.out.println(exc);
            }
        }
    }
    public void replace(){
        jf2=new JFrame("REPLACE");
        jf2.setSize(500,300);
        jf2.setLayout(null);
        //jf2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf2.setVisible(true);

        jl=new JLabel("Find What :");
        jl.setBounds(50,50,80,40);
        jf2.add(jl);

        jt2=new JTextField();
        jt2.addActionListener(this);
        jt2.setBounds(180,50,200,40);
        jf2.add(jt2);

        jl1=new JLabel("Replace with :");
        jl1.setBounds(50,150,100,40);
        jf2.add(jl1);

        jt3=new JTextField();
        jt3.addActionListener(this);
        jt3.setBounds(180,150,200,40);
        jf2.add(jt3);

        jb1=new JButton("Replace");
        jb1.addActionListener(this);
        jb1.setBounds(230,230,100,30);
        jf2.add(jb1);

    }
    void Date_time(){
        ld=LocalDate.now();
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd-MM-yyyy");
        jta.append(dtf.format(ld));
        lt= LocalTime.now();
        DateTimeFormatter dtf2=DateTimeFormatter.ofPattern("HH-mm-ss");
        jta.append("  "+dtf2.format(lt));
    }
    void fontFrame(){
        jf3=new JFrame("Fonts");
        jf3.setSize(700,300);
        //jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf3.setLayout(null);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontFamilies = ge.getAvailableFontFamilyNames();
        jcb=new JComboBox(fontFamilies);
        jcb.setBounds(50,50,200,40);
        jf3.add(jcb);

        String[] str2={"Italic","Bold","Plain"};
        jcb2=new JComboBox(str2);
        jcb2.setBounds(350,50,100,40);
        jf3.add(jcb2);

        Integer[] a={10,12,14,16,18,20,22,24,26,28,30};
        jcb3=new JComboBox(a);
        jcb3.setBounds(550,50,100,40);
        jf3.add(jcb3);

        jb2=new JButton("Ok");
        jb2.setBounds(300,150,80,30);
        jb2.addActionListener(this);
        jf3.add(jb2);
        jf3.setVisible(true);


    }
    void setFontColor(){
        Color c=JColorChooser.showDialog(jf,"SelectFontColour",Color.white);
        jta.setForeground(c);
    }
    void setBackground(){
        Color c=JColorChooser.showDialog(jf,"Select Background Color",Color.darkGray);
        jta.setBackground(c);
    }
    public void changeFont(){
        String font_Family=(String)jcb.getSelectedItem();
        String font_Style=(String)jcb2.getSelectedItem();
        Integer font_Size=(Integer)jcb3.getSelectedItem();
        if(font_Style.equals("Plain")){
            font_style1=Font.PLAIN;
        }
        else if(font_Style.equals("Bold")){
            font_style1=Font.BOLD;
        }
        else if(font_Style.equals("Italic")){
            font_style1=Font.ITALIC;
        }
        Font f=new Font(font_Family,font_style1,font_Size);
        jta.setFont(f);
        jf3.setVisible(false);


    }
}
