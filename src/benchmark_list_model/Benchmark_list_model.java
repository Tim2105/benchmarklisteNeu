package benchmark_list_model;
import benchmark_list.*;
import java.util.Observable;

public class Benchmark_list_model extends Observable
{

    private SortableList<Benchmark_list_ergebnis> liste;
    private int nr;
    private long letzteZeit;
    
    public Benchmark_list_model() 
    {
        liste = new SortableList<Benchmark_list_ergebnis>();
    }
    
    /**
     * erstellt und fügt ein neues Benchmarkergebnis in die Liste ein
     * @param scoreGPU GPU score des Ergebnisses
     * @param scoreCPU CPU score des Ergebnisses
     */
    public void hinzufuegen(int scoreGPU, int scoreCPU)
    {
        liste.append(new Benchmark_list_ergebnis(scoreGPU, scoreCPU, nr + 1));
        nr++;
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die List mithilfe des Insertionsorts
     */
    public void insertionsort()
    {
        long l = System.nanoTime();
        liste.insertionsort();
        letzteZeit = System.nanoTime() - l;
        setChanged();
        notifyObservers();
    }
    
    /**
     * entfernt ein Objekt aus der Liste
     * @param nummer das zu entfernende Objekt basierend auf der im Fenster angezeigten Nummer
     */
    public void entfernen(int nummer)
    {
        liste.toFirst();
        
        while(liste.hasAccess())
        {
            if(nummer == Integer.valueOf(liste.getContent().getNummer()))
            {
                liste.remove();
                this.nr --;
                break;
            }
            
            liste.next();
        }
        setChanged();
        notifyObservers();
    }
   
    /**
     * sortiert die Liste mithilfe der direkten Übersetzung des Selectionsorts
     */
    public void sortiere()
    {
        long l = System.nanoTime();      
        liste.selectionsort1();
        letzteZeit = System.nanoTime() - l;
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die Liste mithilfe der abstrakten Übersetzung des Selectionsorts
     */
    public void sel2()
    {
        long l = System.nanoTime(); 
        liste.selectionsort2();
        letzteZeit = System.nanoTime() - l;
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die Liste mithilfe des Quicksorts
     */
    public void quicksort()
    {
        long l = System.nanoTime(); 
        liste.quicksort();
        letzteZeit = System.nanoTime() - l;
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die Liste mithilfe des Bogosorts
     */
    public void bogosort()
    {
        long l = System.nanoTime(); 
        liste.bogosort();
        letzteZeit = System.nanoTime() - l;
        setChanged();
        notifyObservers();
    }
    
    /**
     * erstellt einen, die Liste repräsentativen, String und gibt diesen zurück
     * @return den String
     */
    @Override
    public String toString()
    {
        String string = "";
        liste.toFirst();
        
        while(liste.hasAccess())
        {
            string += liste.getContent().toString();
            liste.next();
        }
        
        
        return string;
    }
    
    /**
     * mischt die Liste
     */
    public void mischen()
    {
        liste.mischen();
        
        setChanged();
        notifyObservers();
    }

    /**
     * gibt die nächste Verfügbare Nummer zurück
     * @return 
     */
    public int getNr() 
    {
        return nr + 1;
    }
    
    /**
     * erstellt n Elemente und fügt sie in die Liste ein
     * @param anzahl der elemente
     */
    public void erstelleElemente(int elemente)
    {
        for(int i = 0; i < elemente; i++)
            this.hinzufuegen((int)Math.round(Math.random() * 200) + 200, (int)Math.round(Math.random() * 200) + 200);
    }
    
    /**
     * gibt letzteZeit zurück
     * @return letzteZeit
     */
    public long getZeit()
    {
        return letzteZeit;
    }
    
    /**
     * löscht alle Elemente aus der Liste und setzte die Nummer auf 0 zurück
     */
    public void reset()
    {
        liste.toFirst();
        while(liste.hasAccess())
            liste.remove();
        nr = 0;
        
        setChanged();
        notifyObservers();
    }
    
}
