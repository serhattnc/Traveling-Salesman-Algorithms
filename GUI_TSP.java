import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;
public class GUI_TSP implements ActionListener
{
    /* Die Verschiedene Variablen werden hier eingefügt vor allem Labels, Buttons, Panels und die Variablen die dazu dienen*/
    Double laenge=0.00; // die Kanten Längen
    long time=0; // Zeit in ms 
    private Salesman salesman= new Salesman(this); // Salesman Klasse wird hier erstellt
    JFrame fenster = new JFrame(); //das Hauptfenster
    /*3 Panels werden erstellt*/
    JPanel Werte = new JPanel();
    JPanel Screen; 
    JPanel Einstellungen = new JPanel(); 
    /*Insgeasmmt 5 Buttons */
    JButton NearesNButton = new JButton("Nearest Neighbour");
    JButton ChristofidesB = new JButton("Christofides");
    JButton TWO_OPT = new JButton("2-Opt");
    JButton randomB = new JButton("Zufällig");
    JButton clear = new JButton("Löschen");
    /*Labeln um Zeit und ms zusehen */
    JLabel timer = new JLabel("Zeit:"+time);
    JLabel kanteLaenge = new JLabel("Länge:"+laenge);
    int choice; //decider für Knöpfe wird bei der Knöpfen von Algorithmen wichtig 
    Random r = new Random();
    /*ComboBox für Stadtanzahl */
    JComboBox<String> stadtAnzahl= new JComboBox<String>(new String[] { "10", "15", "20", "30", "40", "50","100","150","200","500","1000","5000","10000"});
    JComboBox<String> LinieDicke= new JComboBox<String>(new String[]  { "1","2", "3"});
    JComboBox<String> StadtBreite= new JComboBox<String>(new String[] { "4","5", "6", "7", "8", "9"});
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    String getSA="0";
    
    public GUI_TSP() {

        //Fenstereinstellungen  
        fenster.setTitle("Problem des Handlungsreisenden");
        fenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fenster.setLayout(null);

        //Einstellungen Panel
        Einstellungen.setLayout(null);
        Einstellungen.setBackground( new Color(191,191,191) );
        Einstellungen.setBounds(0 , 0 , (int)Math.round(0.2*screenSize.width) , screenSize.height);
        fenster.add(Einstellungen);

        //Werte Panel
        Werte.setLayout(null);
        Werte.setBackground(new Color(191,191,191));
        Werte.setBounds((int)Math.round(0.2*screenSize.width), 0, screenSize.width-((int)Math.round(0.2*screenSize.width)),(int)Math.round(0.05*screenSize.height) );
        fenster.add(Werte);
        /* Labels weden in das Panel Werte hinzugefügt*/
        Werte.add(kanteLaenge);
        kanteLaenge.setBounds((int)Math.round(0.35*screenSize.width),(int)Math.round(0.005*screenSize.height),300,50);
        kanteLaenge.setFont(new Font("Tahoma",Font.BOLD,17));

        Werte.add(timer);
        timer.setBounds((int)Math.round(0.10*screenSize.width),(int)Math.round(0.005*screenSize.height),300,50);
        timer.setFont(new Font("Tahoma",Font.BOLD,17));

        //Screen Panel
        Screen =new JPanel();
        Screen.setLayout(null);
        Screen.setBackground( Color.white );
        Screen.setBounds((int)Math.round(0.2*screenSize.width) ,(int)Math.round(0.05*screenSize.height)  , screenSize.width-((int)Math.round(0.2*screenSize.width)) , screenSize.height-(int)Math.round(0.05*screenSize.height));
        fenster.add(Screen);
         
        // Hinzufügen von Buttons und Labels mit ihren Eigenschaften
        Einstellungen.add(randomB);
        {
            randomB.setBounds((int)Math.round(0.05*screenSize.width), (int)Math.round(0.7*screenSize.height), 180, 50);
            randomB.setFocusPainted(false);
            randomB.setBackground(new Color(5,30,159));
            randomB.setForeground(new Color(250,250,250));
            randomB.setFont(new Font("Tahoma",Font.BOLD,12));
            randomB.addActionListener(this);
        }
        Einstellungen.add(clear);
        {
            clear.setBounds((int)Math.round(0.05*screenSize.width), (int)Math.round(0.75*screenSize.height), 180, 50);
            clear.setFocusPainted(false);
            clear.setBackground(new Color(5,30,159));
            clear.setForeground(new Color(250,250,250));
            clear.setFont(new Font("Tahoma",Font.BOLD,12));
            clear.addActionListener(this);
        }
        Einstellungen.add(NearesNButton);
        {
            NearesNButton.setBounds((int)Math.round(0.05*screenSize.width), (int)Math.round(0.3*screenSize.height), 180, 50);
            NearesNButton.setFocusPainted(false);
            NearesNButton.setBackground(new Color(250,100,100));
            NearesNButton.setForeground(new Color(250,250,250));
            NearesNButton.setFont(new Font("Tahoma",Font.BOLD,12));
            NearesNButton.addActionListener(this);
        }
        Einstellungen.add(ChristofidesB);
        {
            ChristofidesB.setBounds((int)Math.round(0.05*screenSize.width), (int)Math.round(0.35*screenSize.height), 180, 50);
            ChristofidesB.setFocusPainted(false);
            ChristofidesB.setBackground(new Color(250,100,100));
            ChristofidesB.setForeground(new Color(250,250,250));
            ChristofidesB.setFont(new Font("Tahoma",Font.BOLD,12));
            ChristofidesB.addActionListener(this);
        }
        Einstellungen.add(TWO_OPT);
        {
            TWO_OPT.setBounds((int)Math.round(0.05*screenSize.width), (int)Math.round(0.4*screenSize.height), 180, 50);
            TWO_OPT.setFocusPainted(false);
            TWO_OPT.setBackground(new Color(250,100,100));
            TWO_OPT.setForeground(new Color(250,250,250));
            TWO_OPT.setFont(new Font("Tahoma",Font.BOLD,12));
            TWO_OPT.addActionListener(this);
        }

        Einstellungen.add(stadtAnzahl);
        stadtAnzahl.setBounds((int)Math.round(0.2*screenSize.width)/2-30, (int)Math.round(0.1*screenSize.height), 70, 30);
        stadtAnzahl.setFont(new Font("Segoe UI", 0, 15));
        stadtAnzahl.setSelectedItem("10");

        Einstellungen.add(LinieDicke);
        LinieDicke.setBounds((int)Math.round(0.14*screenSize.width)/2-30, (int)Math.round(0.5*screenSize.height), 70, 30);
        LinieDicke.setFont(new Font("Segoe UI", 0, 15));
        LinieDicke.setSelectedItem("2");

        Einstellungen.add(StadtBreite);
        StadtBreite.setBounds((int)Math.round(0.28*screenSize.width)/2-30, (int)Math.round(0.5*screenSize.height), 70, 30);
        StadtBreite.setFont(new Font("Segoe UI", 0, 15));
        StadtBreite.setSelectedItem("7");

        //Fenster wird angezeigt
        fenster.pack();
        fenster.setVisible(true);

         
        //Maximale Fenster größe
        fenster.setSize(screenSize.width, screenSize.height);
        fenster.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    // Ereignisbehandlung für Buttons
    @Override
    public void actionPerformed(ActionEvent e) {
    // wenn Neares Button geclick wird NearestNButton
    if (e.getSource() == NearesNButton) 
    {
        time=0;//time wird auf 0 gesetzt
        /*Falls als letztens nicht auf Random Button und NN Button geklickt wurde, werden alle Städte nur vom Screen gelöscht.
              Länge auf 0 gesetzt und die Städte werden von vorne erstellt, damit, wenn man vorher
              andere Algorithmen angewendet hat, die dann vom Screen gelöscht werden.
        */
        kanteLaenge.setText("Länge:"+ 0.00);
        if (choice !=0 && choice !=1) {
        kanteLaenge.setText("Länge:"+ 0.00);
        Screen.removeAll();
        Screen.revalidate();
        Screen.paint(Screen.getGraphics());
        int getStadBreite = Integer.parseInt(StadtBreite.getSelectedItem().toString()); // die JComboBox  Werte in int umzuwandeln
        for(int i=0;i < salesman.city.size();i++)   
        {
            Graphics2D g2=(Graphics2D)Screen.getGraphics().create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.red);
            g2.fillOval(salesman.city.get(i).getX(),salesman.city.get(i).getY(), getStadBreite, getStadBreite);
        }
        }
        //NN-Algorithmus wird gestartet
        salesman.nearestN();
        choice=1;
    }

    if (e.getSource() == ChristofidesB) {

        time=0;
        kanteLaenge.setText("Länge:"+ 0.00);
        /*Falls als letztens nicht auf Random Button und Christofides Button geklickt wurde, werden alle Städte nur vom Screen gelöscht.
              Länge auf 0 gesetzt und die Städte werden von vorne erstellt, damit, wenn man vorher
              andere Algorithmen angewendet hat, die dann vom Screen gelöscht werden.
        */
        if (choice !=0) {
        Screen.removeAll();
        Screen.revalidate();
        Screen.paint(Screen.getGraphics());
        for(int i=0;i < salesman.city.size();i++)
        {
            Graphics2D g2=(Graphics2D)Screen.getGraphics().create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.red);
            g2.fillOval(salesman.city.get(i).getX(),salesman.city.get(i).getY(), 7, 7);
        }
        }
        choice=2;
        //Christofides Algorithmus wird gestartet
        salesman.ChristofidesAlgo();
    }
    if (e.getSource() == TWO_OPT) {
        time=0;
        
        /*Falls als letztens nicht auf  Neares-Neighbour Button und 2-Opt geklickt wurde, werden alle Städte nur 
            vom Screen gelöscht. Länge auf 0 gesetzt und die Städte werden von vorne erstellt, damit, wenn man vorher
              andere Algorithmen angewendet hat, die dann vom Screen gelöscht werden und Zusätzlich werden die Knoten mit dem
            nächsten Kante verbunden werden, damit es ein random start gibt(Die Knoten sind unsortiert)
        */
        kanteLaenge.setText("Länge:"+ 0.00);
        if (choice !=1 && choice!=3)
        {
            salesman.cityCopy.clear();
            Stadt first=salesman.city.get(0);
            salesman.cityCopy.add(first);
            int i=0;
            for(;i < salesman.city.size()-1;i++)
            {
                ZeichneKante(salesman.city.get(i).getX(),salesman.city.get(i).getY(),salesman.city.get(i+1).getX(), salesman.city.get(i+1).getY(), Screen.getGraphics(),Color.black);
                salesman.city.get(i).addCity( salesman.city.get(i+1));
                salesman.cityCopy.add(salesman.city.get(i+1));
            }
                ZeichneKante(salesman.city.get(i).getX(),salesman.city.get(i).getY(),first.getX(), first.getY(), Screen.getGraphics(),Color.black);
                salesman.city.get(i).addCity(first);
                salesman.cityCopy.add(first);
        }
            //Falls 2-Opt schon werwendet wurde, wird noch eine unnötige rechnung gestoppt, jodoch die Länge bleibt dann ungeändert
            if(choice!=3)
            {
            salesman.twoopt();
            choice=3;
            }
            else salesman.getKanteLaengeTour(salesman.cityCopy);
            
    }
    if (e.getSource() == randomB) {
        /*anwendet removeStadt Methode. Länge wird = 0 und es werden so viele Städte erzeugt wie die Anzahl von 
        stadtAnzahl Variable*/
        removeStadt();
        kanteLaenge.setText("Länge:"+ 0.00);
        getSA = stadtAnzahl.getSelectedItem().toString();
        for (int i = 0; i < Integer.parseInt(getSA); i++)
        {
            
            int x = r.nextInt((int) Math.round(0.78 * screenSize.width)) + 10;
            int y = r.nextInt((int) Math.round(0.84 * screenSize.height)) + 10;
            ZeichneStadt(x, y, Color.red , Screen.getGraphics());
            
        }
        choice=0;
    }

    if (e.getSource() == clear) {
        //alles wird ressetet    
        removeStadt();
        salesman.cityCopy.clear();
        kanteLaenge.setText("Länge:"+ 0.00);
        timer.setText("Zeit:"+ 0.00);
        
    }

    }
    
    public void ZeichnealleKanten(ArrayList<Stadt> pStadt)
    {
        //Alle Städte von pStadt werden miteinander im Screen verbindet.
        for(int i=0;i < salesman.city.size();i++)
        {
            ZeichneKante(pStadt.get(i).getX(),pStadt.get(i).getY(),pStadt.get(i+1).getX(), pStadt.get(i+1).getY(), Screen.getGraphics(),Color.black);
        }
            ZeichneKante(pStadt.get(pStadt.size()-1).getX(),pStadt.get(pStadt.size()-1).getY(), pStadt.get(0).getX(), pStadt.get(0).getY(), Screen.getGraphics(),Color.black);
    }
    public void ZeichnealleStädte()
    {
        //Alle Städte weden in Screen angezeigt.
        for(int i=0;i < salesman.city.size();i++)
        {
            int getStadBreite = Integer.parseInt(StadtBreite.getSelectedItem().toString()); // die JComboBox  Werte in int umzuwandeln
            Graphics2D g2=(Graphics2D)Screen.getGraphics().create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.red);
            g2.fillOval(salesman.city.get(i).getX(),salesman.city.get(i).getY(), getStadBreite, getStadBreite);
            
        }
    }
    public void ZeichneStadt(int x,int y,Color c, Graphics g)
    {
       // Die Städte werden mit der Breite von getStadtBreite auf Screen an der Stelle (x|y) erzeugt.
       int getStadBreite = Integer.parseInt(StadtBreite.getSelectedItem().toString()); // die JComboBox  Werte in Integer umzuwandeln
       Graphics2D g2=(Graphics2D)g.create();
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g2.setColor(c);
       g2.fillOval(x, y, getStadBreite, getStadBreite);
       //Die erstellte Stadt wird auch zu Arraylist city in Salesman hinzugefügt
       salesman.hinzufügen(x, y);
    }
    public void removeStadt() {
        //Alles wird vom Screen als auch von Arraylist city gelöscht
        salesman.cityCopy.clear();
        salesman.city.clear();
        Screen.removeAll();
        Screen.revalidate();
        Screen.paint(Screen.getGraphics());
    }
    

   public void ZeichneKante(int startX, int startY, int endX, int endY, Graphics g, Color c)
   {
    //
        int getLinieDicke = Integer.parseInt(LinieDicke.getSelectedItem().toString());//Linedicke einstellen
        Graphics2D g2=(Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(getLinieDicke));
        g2.setColor(c);
        int verschiebung=3;//Je dicker die Kanten sie desto viel mehr verschieben sie sich, daher gibt es eine Variable verschiebung
        if(getLinieDicke<2)//Falls die Dicke <= 2 ist wird die Verschiebung geändert
        {
            verschiebung=2;
        }
        g2.drawLine(startX+verschiebung, startY+verschiebung, endX+verschiebung, endY+verschiebung);
   }   
}
