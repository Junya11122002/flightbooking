import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FlightList extends JFrame {

    //Variables
    private int pageNum = 1;

    private final int LIST_LENGTH = 10;
    
    public FlightList(String title, String iconImage)
    {
        ImageIcon icon = new ImageIcon(iconImage);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(2, 2));
        this.setSize(1200, 700);
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0x035392));
        this.setVisible(true);

        //Removes content from the frame
        this.getContentPane().removeAll();
        
        this.repaint(); //Refreshes erased content
        this.revalidate();  //Refreshes added content
        
        //Title
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(100, 100));
        titlePanel.setBackground(new Color(0xFFFFFF));

        JLabel myLabel = new JLabel();
        myLabel.setText("Flight List");
        myLabel.setForeground(new Color(0x035392));
        myLabel.setFont(new Font("Arial", Font.BOLD, 40));
        myLabel.setHorizontalAlignment(JLabel.CENTER);
        myLabel.setVerticalAlignment(JLabel.CENTER);

        titlePanel.add(myLabel);
        this.add(titlePanel, BorderLayout.NORTH);

        //Side
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        sidePanel.setPreferredSize(new Dimension(200, 100));
        sidePanel.setBackground(new Color(0xFFFFFF));

        this.add(sidePanel, BorderLayout.WEST);

        //List
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout(2, 2));
        listPanel.setBackground(new Color(0x035392));

        //Flights *in list panel*
        JPanel flightListPanel = new JPanel();    
        flightListPanel.setLayout(new GridLayout(LIST_LENGTH, 1, 2, 2));
        flightListPanel.setPreferredSize(new Dimension(100, 100));
        flightListPanel.setBackground(new Color(0xFFFFFF));

        listPanel.add(flightListPanel);

        this.add(listPanel);

        //Fill List
        fillList(flightListPanel);

        //Page *in list panel*
        JPanel pagePanel = new JPanel();    
        pagePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        pagePanel.setPreferredSize(new Dimension(100, 40));
        pagePanel.setBackground(new Color(0xFFFFFF));

        listPanel.add(pagePanel, BorderLayout.SOUTH);

        JLabel pageLabel = new JLabel();    //Page number
        pageLabel.setText("Page " + pageNum);
        pageLabel.setForeground(new Color(0x035392));
        pageLabel.setFont(new Font("Arial", Font.BOLD, 12));

        JButton backPageButton = new JButton("<<");     //Back button
        backPageButton.setPreferredSize(new Dimension(60, 30));
        backPageButton.setFont(new Font("Arial", Font.BOLD, 10));
        backPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                if (pageNum > 1)
                {
                    pageNum--;
                    pageLabel.setText("Page " + pageNum);
                    fillList(flightListPanel);
                }
            }
        });

        JButton nextPageButton = new JButton(">>");     //Next button
        nextPageButton.setPreferredSize(new Dimension(60, 30));
        nextPageButton.setFont(new Font("Arial", Font.BOLD, 10));
        nextPageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                ReadFlightsCSV fileReader = new ReadFlightsCSV();
                if (pageNum < Math.ceil((double)fileReader.getFileLength() / LIST_LENGTH))
                {
                    pageNum++;
                    pageLabel.setText("Page " + pageNum);
                    fillList(flightListPanel);
                }
            }
        });

        pagePanel.add(backPageButton);
        pagePanel.add(pageLabel);
        pagePanel.add(nextPageButton);

        //Refreshes added content
        this.revalidate();  
        
    }

    private JPanel newFlight(String flightInfo)
    {
        JPanel flightPanel = new JPanel();
        flightPanel.setLayout(new BorderLayout());
        //flightPanel1.setPreferredSize(new Dimension(100, 60));
        flightPanel.setBackground(new Color(0xEEEEEE));
        
        JLabel flightLabel = new JLabel();
        flightLabel.setText(flightInfo);
        flightLabel.setForeground(new Color(0x035392));
        flightLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        flightLabel.setHorizontalAlignment(JLabel.LEFT);
        flightLabel.setVerticalAlignment(JLabel.CENTER);
        flightPanel.add(flightLabel);

        JButton flightButton = new JButton("Book");
        flightButton.setPreferredSize(new Dimension(200, 20));
        flightPanel.add(flightButton, BorderLayout.EAST);
        flightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                System.out.println(flightInfo);
            }
        });

        return flightPanel;
    }

    private void fillList(JPanel listPanel)
    {
        listPanel.removeAll();

        ReadFlightsCSV readCSV = new ReadFlightsCSV();
        Flight[] flights = readCSV.getNextFlight();

        for (int i = 0; i < LIST_LENGTH; i++)
        {
            int listIndex = i + (10 * (pageNum - 1));
            if (listIndex >= flights.length)
                break;
            //System.out.println(flights[i].airportName);
            String info = flights[listIndex].airportName + "   |   " + flights[listIndex].arrivalTime + "   |   " + flights[listIndex].takeOffTime;
            listPanel.add(newFlight(info));
        }

        this.repaint();
        this.revalidate();  
    }

    
}
