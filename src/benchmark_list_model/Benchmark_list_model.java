
package benchmark_list_model;
import benchmark_list.*;
import java.util.Observable;

public class Benchmark_list_model extends Observable
{

    private SortableList<Benchmark_list_ergebnis> liste;
    private int nr;
    
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
        liste.insertionsort();
        
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
        liste.selectionsort1();
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die Liste mithilfe der abstrakten Übersetzung des Selectionsorts
     */
    public void sel2()
    {
        liste.selectionsort2();
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die Liste mithilfe des Quicksorts
     */
    public void quicksort()
    {
        liste.quicksort();
        
        setChanged();
        notifyObservers();
    }
    
    /**
     * sortiert die Liste mithilfe des Bogosorts
     */
    public void bogosort()
    {
        liste.bogosort();
        
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
    
}
