import java.util.ArrayList;

public class Stadt
{
    //die Versciedene Variablen von Stadt werden erstellt 
    public int x;//x Kordinate von der Stadt
    public int y;//y Kordinate von der Stadt
    public boolean verknuepft=false; // ob es verknüpft ist
    public ArrayList<Stadt> besuchteStäte = new ArrayList<Stadt>(); // dies Zeigt an welcher Stadt als nächstes besucht wird
  
    public Stadt(int pX,int pY)//Zum Erstellen wird unbedingt X und Y Koordinaten gebraucht
    {
        x=pX;
        y=pY;
    }
    /* get und set Methoden für die Variablen*/
    public int getX(){return x;}
    public void setX(int newX) {x = newX;}
    public int getY(){return y;}
    public void setY(int newY) {y = newY;}
    public Boolean istVerknuepft(){return verknuepft;}
    public void setVerknuepft(boolean v) {verknuepft = v;}
    public void addCity(Stadt a){besuchteStäte.add(a);}     
}
