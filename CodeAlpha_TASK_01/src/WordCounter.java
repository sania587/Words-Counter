import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class WordCounter extends JFrame {
    private JTextArea Text_Area;
    private JLabel Word_Count_Label;
    
    
  //main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WordCounter();
            }
        });
    }
    
    //counter
    private int countWords(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    
    
    //text label
    public JLabel Title_label( JLabel title_label)
    {
    	title_label.setFont(new Font("Arial", Font.BOLD, 20));
    	title_label.setForeground(Color.WHITE);
    	title_label.setHorizontalAlignment(SwingConstants.CENTER);
    	return title_label;
    }
    
    
    
    //counter button
    public JButton Count_Button(JButton count_Button)
    {
    	 count_Button.setFont(new Font("Arial", Font.PLAIN, 14));
         count_Button.setBackground(new Color(255, 165, 0));
         count_Button.setForeground(Color.WHITE);
         count_Button.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseEntered(java.awt.event.MouseEvent evt) {
             	count_Button.setBackground(Color.GREEN);
                 count_Button.setForeground(Color.GRAY);
             }

             public void mouseExited(java.awt.event.MouseEvent evt) {
                 count_Button.setBackground(new Color(255, 165, 0));
                 count_Button.setForeground(Color.WHITE);
             }
         });
         count_Button.setBorder(BorderFactory.createRaisedBevelBorder());
         return count_Button;
    }
    
    
    
    //Reset button
    public JButton Reset_Button(JButton resetButton)
    {
    	 resetButton.setFont(new Font("Arial", Font.PLAIN, 14));
         resetButton.setBackground(new Color(128, 0, 128)); // Dark purple
         resetButton.setForeground(Color.WHITE); // White text color
         resetButton.setBorder(BorderFactory.createRaisedBevelBorder());

         resetButton.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseEntered(MouseEvent e) {
                 resetButton.setBackground(Color.LIGHT_GRAY); // Lighter purple
                 resetButton.setForeground(Color.BLACK); // Black text color
             }

             @Override
             public void mouseExited(MouseEvent e) {
                 resetButton.setBackground(new Color(128, 0, 128)); // Dark purple
                 resetButton.setForeground(Color.WHITE); // White text color
             }
         });
         return resetButton;
    }
    
    
    
     //counting
    public WordCounter() {
        setTitle("Word Counter");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel Background_Panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int w = getWidth();
                int h = getHeight();
                Color color1 = new Color(135, 206, 235); // Light blue
                Color color2 = new Color(176, 224, 230); // Lighter blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, w, h);
            }
        };
        Background_Panel.setLayout(new BorderLayout());

        // Title of the screen label
        JLabel title_label = new JLabel("~: Word Counter :~");
        title_label=Title_label(title_label);   //function call
        Background_Panel.add(title_label, BorderLayout.NORTH);

        //setting the text box space
        Text_Area = new JTextArea(4, 30); 
        JScrollPane scroll_Pane = new JScrollPane(Text_Area);

        Word_Count_Label = new JLabel("Word Count: 0");
        Word_Count_Label.setFont(new Font("Arial", Font.BOLD, 18));
        Word_Count_Label.setForeground(Color.WHITE);

        //counter button
        JButton count_Button = new JButton("Count Words");
        count_Button=Count_Button(count_Button);
        count_Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = Text_Area.getText();
                int wordCount = countWords(text);
                Word_Count_Label.setText("Word Count: " + wordCount);
            }
        });

        //reset button
        JButton resetButton = new JButton("Reset");
        resetButton= Reset_Button(resetButton);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Text_Area.setText(""); // Clear the text area
                Word_Count_Label.setText("Word Count: 0"); // Reset word count label
            }
        });

        //adding buttons in pannel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(135, 206, 235));
        buttonPanel.add(count_Button);
        buttonPanel.add(resetButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(135, 206, 235));
        mainPanel.add(scroll_Pane, BorderLayout.CENTER);
        mainPanel.add(Word_Count_Label, BorderLayout.NORTH);

        Background_Panel.add(buttonPanel, BorderLayout.SOUTH);
        Background_Panel.add(mainPanel, BorderLayout.CENTER);

        add(Background_Panel);

        //text area setting
        Text_Area.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateWordCount();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateWordCount();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateWordCount();
            }

            private void updateWordCount() {
                String text = Text_Area.getText();
                int wordCount = countWords(text);
                //wordCountLabel.setText("Word Count: " + wordCount);
            }
        });

        setVisible(true);
    }

    
}
