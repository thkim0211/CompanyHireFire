import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Write a description of class CompanyManagev5 here.
 * This version is a major update from v4, with completely finished GUI layout and organizing. Probably the Final Version
 * @author (Chris Kim) 
 * @version (2.0.0f)
 * major update!
 * 2  Largest update   .0   final     .0f final
 */
public class CompanyManagev5 extends JPanel implements ListSelectionListener
{
    private JPanel masteradd;
    private JPanel jplist;
    private JPanel jpadd;
    private DefaultListModel defaultmodelList;
    private JPanel panel;
    private JTextArea info;
    private JList jlist;
    private JScrollPane scroll;
    private JButton fireinthehole;
   public CompanyManagev5()
    {
       super();
       //Instantiations of panels and components
       defaultmodelList = new DefaultListModel();
       jlist = new JList(defaultmodelList);
       jlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       jlist.setLayoutOrientation(JList.VERTICAL_WRAP);
       jlist.addListSelectionListener(this);
       scroll = new JScrollPane(jlist);   
       Hire addingpanel = new Hire();
       Fire firepanel = new Fire();
       
       Search searchfor = new Search();
       info = new JTextArea(10,10);
       info.setEditable(false);
       //
       GroupLayout layout = new GroupLayout(this);
       super.setLayout(layout);
       layout.setAutoCreateGaps(true);
       layout.setAutoCreateContainerGaps(true);
       layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(firepanel, 145, 145, 145)
                        .addGap(18, 18, 18)
                        .addComponent(addingpanel,145,145,145))
                    .addComponent(scroll,300,300,300))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(info,150,150,150)
                    .addComponent(searchfor,150,150,150))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addingpanel,150,150,150)
                .addGap(150, 150, 150))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(scroll,200,200,200)
                    .addComponent(info,150,150,150))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(addingpanel,200,200,200))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(searchfor,150,150,150))       
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addingpanel,200,200,200)
                            .addComponent(firepanel, 200, 200, 200))))
                .addContainerGap()));

                
       
       setSize(1000,500);
       setVisible(true);
    }
   public void valueChanged(ListSelectionEvent e)              //credit to docs.oracle.com
    {
        if (e.getValueIsAdjusting() == false) 
        {
            if (jlist.getSelectedIndex() == -1) 
            {              
                fireinthehole.setEnabled(false); // if not selected, disable fire button.
            } 
            else                                                                        
            {
                fillInfo(jlist.getSelectedIndex());
                fireinthehole.setEnabled(true);    //enable the fire button.
            }
        }
    }
   public void fillInfo(int pos)
   {
      Employee jimbo = (Employee)defaultmodelList.getElementAt(pos);
      String sage = String.valueOf(jimbo.accAge());
      String name = jimbo.accName();
      String wage = String.valueOf(jimbo.accWage());
      boolean gender = jimbo.accGender();
      info.setText(name+"\n"+sage+" years\n");
      info.append("Earns $"+wage+"\n");
      if(gender)
      {
         info.append("Male \n");
      }
      else
      {
         info.append("Female \n");
      }
   }
   public class Employee
    {
        private int age;           
        private double wage;
        private String name; 
        private boolean gender;
       Employee()
        {
            age = 0;
            wage = 0.0;
            name = "";
            gender = true;
        }
       Employee(String n, int a, double w,boolean g)
        {
            age = a;
            wage = w;
            name = n;
            gender = g;
            //rank = r;
        }
       Employee(String random)
       {
           Random generator = new Random();
           name = String.valueOf(Character.toChars(generator.nextInt(100)+1));
           age = generator.nextInt(110)+1;
           wage = generator.nextDouble()*1000000+1;
           int genderchoice = generator.nextInt(2)+1;
           if(genderchoice == 1)
           {
               gender = true;
           }
           else
           {
               gender = false;
           }
       }
       public String toString()
       {
            return("Name: "+name);
       }
       public String accName()
       {
            return name;
       }
       public int accAge()
       {
            return age;
       }
       public double accWage()
       {
            return wage;
       }
       public boolean accGender()
       {
           return gender;
       }
    }
   public void modifyListadd(String name, int age, double wage, boolean gender)
   {
        defaultmodelList.addElement(new Employee(name,age,wage,gender));
   }
   public void addRandomList(String r)
   {
       for(int pos=0;pos<10;pos++)
       {
           defaultmodelList.addElement(new Employee(r));
       }
   }
   public void searchName(String n)
   {
       for(int pos=0;pos<defaultmodelList.size();pos++)
       {
          Employee searching = (Employee)defaultmodelList.getElementAt(pos);
          if(searching.accName().equals(n))
          {
             //info.setText("Success");  //one bite of banana
             fillInfo(pos);
          }
       }
   }
   public void modifyListFire(String fire)
   {
        int pos = jlist.getSelectedIndex();
        defaultmodelList.remove(pos);
   }
   private  class Hire extends JPanel 
   {
      private JTextField inputName;
      private JTextField inputAge;
      private JTextField inputWage;
      private JLabel jwage;
      private JLabel jname;
      private JLabel jage;
      private JButton finishinput;
      private JRadioButton male;
      private JRadioButton female;
      private boolean gender;
      private JButton addRandom;
     public Hire()
     {
          super();
          setBorder(BorderFactory.createLineBorder(Color.black));
          jname = new JLabel("Enter name");
          inputName = new JTextField(10);
          jage = new JLabel("Enter age");
          inputAge = new JTextField(3);
          jwage = new JLabel("Enter wage");
          inputWage = new JTextField(5);
          male = new JRadioButton("Male");  
          male.addActionListener(new GenderListen());
          female = new JRadioButton("Female");
          female.addActionListener(new GenderListen());
          addRandom = new JButton("10 Random Employee");
          addRandom.addActionListener(new RandomListener());
          add(male);
          add(female);
          add(jname);
          add(inputName);
          add(jage);
          add(inputAge);
          add(jwage);
          add(inputWage);
          finishinput = new JButton("Press to Add");
          finishinput.addActionListener(new AddListen());
          add(finishinput);
          add(addRandom);
          setPreferredSize(new Dimension(30,30));
          //setBackground(Color.blue);
     }
     public void modGender(boolean g)
     {
         gender = g;
     }
     public void addRandomList(String r)
     {
       for(int pos=0;pos<10;pos++)
       {
           defaultmodelList.addElement(new Employee(r));
       }
     }
     private class RandomListener implements ActionListener
     {
       public void actionPerformed(ActionEvent event)
         {
            String r = "nothing";
            addRandomList(r);
         }  
     }  
     private class AddListen implements ActionListener
      {
         public void actionPerformed(ActionEvent event)
         {
             String name = inputName.getText();
             int infoage = Integer.parseInt(inputAge.getText());
             double infowage = Double.parseDouble(inputWage.getText());
             modifyListadd(name,infoage,infowage,gender); 
         }
      }
     private class GenderListen implements ActionListener
     {
         private String thegender;
         public void actionPerformed(ActionEvent event)
         {
             AbstractButton genderchoice = (AbstractButton)event.getSource();
             thegender = genderchoice.getText();
             if(thegender.equals("Male"))
             {
                  modGender(true);
             }
             else
             {
                 modGender(false);
             }
         }
     }
   }
   private class Fire extends JPanel
   {
         public Fire()
         {
             super();
             setBorder(BorderFactory.createLineBorder(Color.black));
             fireinthehole = new JButton("Click to FIRE");
             fireinthehole.addActionListener(new FireListen());
             fireinthehole.setEnabled(false);
             add(fireinthehole);
             setPreferredSize(new Dimension(25,25));
         }
         private class FireListen implements ActionListener
         {
             public void actionPerformed(ActionEvent e)
             {
                 modifyListFire("Fire");
                 info.setText("");
             }
         }
   }
   private class Search extends JPanel
   {
       private JTextField inputKey; 
       private JLabel inputSearch;
       private JButton enterSearch;
       private String theName;
       public Search()
       {
           super();
           setBorder(BorderFactory.createLineBorder(Color.black));
           inputSearch = new JLabel("Enter Name ");
           inputKey = new JTextField(7);
           enterSearch = new JButton("Search!");
           enterSearch.addActionListener(new SearchListen());
           add(inputSearch);
           add(inputKey);
           add(enterSearch);
           setPreferredSize(new Dimension(10,10));
       }
       private class SearchListen implements ActionListener
       {
           public void actionPerformed(ActionEvent e)
           {
               theName = inputKey.getText();
               searchName(theName);
           }
       }
   }
   public static void main()
    {
        JFrame frame = new JFrame("Company Manage v5 - Chris Kim");      
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new CompanyManagev5());
        frame.setSize(500,500);
        frame.setVisible(true);
    }
}


