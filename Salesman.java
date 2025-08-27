import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Salesman {
    // Kann GUI-Methoden verwenden
    private GUI_TSP gui;
    // ArrayLists werden initialisiert
    ArrayList<Stadt> city = new ArrayList<Stadt>();
    ArrayList<Stadt> cityCopy = new ArrayList<Stadt>();
    ArrayList<Stadt> oddVertices = new ArrayList<Stadt>();
    ArrayList<Stadt> newTour;
    // Methoden zur Unterstützung
    Stadt currentS;
    Stadt bestStadt;
    Double TourLänge=0.0;
    // Doubles werden auf eine Nachkommazahl gerundet
    private static final DecimalFormat df = new DecimalFormat("0.0");
    private static final DecimalFormat da = new DecimalFormat("0");
    
    public Salesman(GUI_TSP gui) // GUI wird hinzugefügt
    {
       this.gui=gui;
    }
  
    public void hinzufügen(int xS,int yS)
    {
        // Städte werden der ArrayList hinzugefügt
        city.add(new Stadt(xS, yS));
    }

    public void SetZero()
    {
        // Um Algorithmen zurückzusetzen
        for(int i=0;i<city.size();i++)
        {
            city.get(i).besuchteStäte.clear();
            city.get(i).setVerknuepft(false);
            cityCopy.clear();
        }
    }
    
    public double getKanteLaengeTour(ArrayList<Stadt> cities)
    {
        // Die Längen von Kanten, die in der ArrayList cities sind, werden zusammenaddiert und in die GUI hinzugefügt
       TourLänge=0.00;
       for(int i=0; i < cities.size()-1;i++)
       {
           TourLänge += getKanteLaenge(cities.get(i),cities.get(i+1));
       }
       gui.kanteLaenge.setText("Länge:"+df.format(TourLänge));
       return TourLänge;
    }
    
    public double getKanteLaenge(Stadt stadt1, Stadt stadt2)
    {
        // Die Längen der Kanten zwischen zwei Städten werden mit dem Pythagoras berechnet.
        int startX= stadt1.getX();
        int startY =stadt1.getY();
        int endX = stadt2.getX();
        int endY = stadt2.getY();
        int deltaX = endX - startX;  
        int deltaY = endY - startY;  

        double vectorXY = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);
        return Math.sqrt(vectorXY);
    }

    public void nearestN()
    {
        long bTime = System.currentTimeMillis(); // Zeit in Millisekunden wird berechnet
        SetZero();
        if(!city.isEmpty()) // Wenn city nicht leer ist
        {
            currentS=city.get(0); // Man beginnt bei der 0. Stadt
            cityCopy.add(city.get(0)); // Die 0. Stadt wird zu cityCopy hinzugefügt
            for(int a=0;a<city.size();a++)
            {
                currentS.setVerknuepft(true); // CurrentS wird auf true gesetzt, da es bereits verbunden ist
                double nn=Double.MAX_VALUE;
                int zaehler=0; // Zähler
                {
                    for (int i = 0; i < city.size(); i++)
                    {
                        // Man sucht nach der nächsten Stadt, die nicht verbunden ist
                        if (city.get(i).istVerknuepft() != true && currentS != city.get(i)) 
                        {
                            double KantenL = getKanteLaenge(currentS,city.get(i));
                            if (KantenL < nn) 
                            {
                                nn = KantenL;
                                bestStadt = city.get(i);
                            }
                        }
                        else
                        {
                            zaehler++; // Wenn keine Stadt gefunden wurde, wird der Zähler um 1 erhöht
                        }
                    }
                    if (zaehler != city.size()) {
                        // Falls der Zähler nicht so groß ist wie die Anzahl der Städte (es gibt noch Städte, die nicht verbunden sind)
                        gui.ZeichneKante(currentS.getX(), currentS.getY(),bestStadt.getX(), bestStadt.getY(), gui.Screen.getGraphics(),Color.black);
                        currentS.addCity(bestStadt); // wird zu den besuchten Städten hinzugefügt
                        cityCopy.add(bestStadt); // wird zu cityCopy hinzugefügt
                        currentS=bestStadt; // Current Stadt wird mit Best Stadt ersetzt 
                        getKanteLaengeTour(cityCopy);
                    }
                    else
                    {
                        // Falls der Zähler so groß ist wie die Anzahl der Städte, wird die letzte Stadt mit der ersten Stadt verbunden
                        gui.ZeichneKante(currentS.getX(), currentS.getY(),city.get(0).getX(), city.get(0).getY(), gui.Screen.getGraphics(),Color.black);
                        /* die erste Stadt wird zu den besuchten Städten und zu cityCopy hinzugefügt */
                        currentS.addCity(city.get(0));
                        cityCopy.add(city.get(0));
                        currentS.setVerknuepft(true);
                        getKanteLaengeTour(cityCopy);
                    }
                }
            }
        }
        else return;
        long differenceTime = System.currentTimeMillis()- bTime; // Die insgesamte Zeit wird berechnet und in der GUI angezeigt
        gui.timer.setText("Zeit:"+da.format(differenceTime)+" ms");
    }

    public void ChristofidesAlgo()
    {
        long bTime = System.currentTimeMillis(); // Zeit berechnen
        SetZero();
        MST(); // Anwendet minimalen Spannbaum
        long differenceTime = System.currentTimeMillis()- bTime; // Zeit berechnen
        gui.timer.setText("Zeit:"+da.format(differenceTime)+" ms");
    }


    public void MST() {
        double nn = Double.MAX_VALUE;
        Stadt currentSindex = null;
        if (!city.isEmpty()) { // Wenn die Liste nicht leer ist
            currentS = city.get(0); // Die erste Stadt
            currentS.setVerknuepft(true); // Die erste Stadt wird als verbunden markiert
            for (int b = 0; b < city.size() - 1; b++) {
                for (int i = 0; i < city.size(); i++) {
                    /* Im MST durchsuchen wir alle verbundenen Städte und finden die kürzeste Strecke, um uns zu verbinden */
                    if (city.get(i).istVerknuepft() == true) { // Wenn die Stadt verbunden ist
                        currentS = city.get(i);
    
                        for (int a = 0; a < city.size(); a++) { // Alle verbundenen Städte durchlaufen
                            if (city.get(a).istVerknuepft() != true && currentS != city.get(a)) { // Finden einer unverbundenen Stadt mit der geringsten Kantenlänge
                                double KantenL = getKanteLaenge(currentS, city.get(a));
                                if (KantenL < nn) {
                                    nn = KantenL;
                                    bestStadt = city.get(a);
                                    currentSindex = currentS;
                                }
                            }
                        }
                    }
                }
                // Hinzufügen und Markieren der neuen Stadt als verbunden
                currentSindex.addCity(bestStadt);
                bestStadt.setVerknuepft(true);
                /* Die Kante wird gezeichnet */
                gui.ZeichneKante(currentSindex.getX(), currentSindex.getY(), bestStadt.getX(), bestStadt.getY(), gui.Screen.getGraphics(), Color.black);
                nn = Double.MAX_VALUE;
                hatOddVertices();
            }
        } else {
            return;
        }
    }
    public void hatOddVertices() {
        // Städte mit ungerader Anzahl von Verbindungen werden ermittelt
        for (int i = 0; i < city.size(); i++) ;
        {
            if (city.get(0).besuchteStäte.size() % 2 != 0) {
                oddVertices.add(city.get(0));
            }
        }
    }
    
//    public void MaxMatching()
//    {
    
//     /*MaxMatching funktioniert nicht aber die generelle Idee dahinter ist, dass man die oddVertices mit Minimal-Weight-Perfect-Matching verbindet, aber 
//      *,weil es nicht Funktionirt und Fehler Verursacht wird es Kommentiert
//     */

//     Graph<Stadt, DefaultWeightedEdge> graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
//     for(int i=0;i<oddVertices.size();i++)
//     {
//         graph.addVertex(oddVertices.get(i));
//     }
//     for (int i = 0; i < oddVertices.size(); i++) {
//         for (int j = i + 1; j < oddVertices.size(); j++) {
//             Stadt city1 = oddVertices.get(i);
//             Stadt city2 = oddVertices.get(j);
//             double distance = getKanteLaenge(city1, city2);
//             DefaultWeightedEdge edge = graph.addEdge(city1, city2);
//             graph.setEdgeWeight(edge, distance);
//         }
//     }
//      KolmogorovWeightedPerfectMatching<Stadt, DefaultWeightedEdge> matching = new KolmogorovWeightedPerfectMatching<>(graph);
//      matching.getMatching();

//      for (DefaultWeightedEdge edge : matching.getMatching())
//      {
//        System.out.println("Matched edge: " + graph.getEdgeSource(edge) + " -> " + graph.getEdgeTarget(edge));
//      }
//    }
public ArrayList<Stadt> swap(ArrayList<Stadt> cities, int i, int j) {
    // Dient zum Vertauschen der Städte i und j in copyCity
    // newTour wird erstellt
    newTour = new ArrayList<Stadt>();

    // Bis zu i werden alle Städte hinzugefügt
    int size = cities.size();
    for (int c = 0; c <= i - 1; c++) {
        newTour.add(cities.get(c));
    }

    // Reihenfolge zwischen i und j umkehren und zu newTour hinzufügen
    int dec = 0;
    for (int c = i; c <= j; c++) {
        newTour.add(cities.get(j - dec));
        dec++;
    }

    // Array von Punkt j bis Ende an newTour anhängen
    for (int c = j + 1; c < size; c++) {
        newTour.add(cities.get(c));
    }

    return newTour;
}

public void twoopt() {
    long bTime = System.currentTimeMillis(); // Zeit wird gemessen
    double bestDist = getKanteLaengeTour(cityCopy); // Die Länge der aktuellen Tour
    double newDist;
    if (!city.isEmpty()) {
        int swaps = 1;
        while (swaps != 0) { // Wenn swaps 0 sind, gibt es keine Verbesserungen
            swaps = 0;
            for (int i = 1; i < cityCopy.size(); i++) {
                for (int j = i + 1; j < cityCopy.size() - 1; j++) {
                    // Es werden z.B. die Längen von AB + CD mit AC + BD verglichen. Falls AB + CD größer ist, werden die Kanten mit swap vertauscht
                    if (getKanteLaenge(cityCopy.get(i), cityCopy.get(i - 1)) +
                            getKanteLaenge(cityCopy.get(j + 1), cityCopy.get(j)) >=
                            (getKanteLaenge(cityCopy.get(i), cityCopy.get(j + 1)) +
                                    getKanteLaenge(cityCopy.get(i - 1), cityCopy.get(j)))) {

                        newTour = swap(cityCopy, i, j);

                        newDist = getKanteLaengeTour(newTour);
                        // Falls die neue Distanz kleiner ist, wird newTour die neue Tour
                        if (newDist < bestDist) {
                            cityCopy = newTour;
                            bestDist = newDist;
                            swaps++;

                        }
                    }
                }
            }
        }
        // Länge wird hinzugefügt
        getKanteLaengeTour(cityCopy);
        // Screen wird neu erstellt
        gui.Screen.removeAll();
        gui.Screen.revalidate();
        gui.Screen.paint(gui.Screen.getGraphics());
        gui.ZeichnealleStädte();
        gui.ZeichnealleKanten(cityCopy);
        long differenceTime = System.currentTimeMillis() - bTime;
        gui.timer.setText("Zeit:" + da.format(differenceTime) + " ms");
    }
}
}