package benchmark_list_model;


public class SortableList <ContentType extends ComparableContent> extends List<ContentType> {

    private boolean teilweiseSortiert, sortiert;
    
  public SortableList() {
    super();
  }
  
   /**
    * führt direkte Übersetzung des Selectionsorts auf Liste aus
    */
  public void selectionsort1()
  {
      ListNode min, links, obj;
      links = first;
      while(links != last)
      {
          obj = links.getNextNode();
          min = links;
          while(obj != null)
          {
              if(obj.getContentObject().isLess(min.getContentObject()))
                  min = obj;
              
              obj = obj.getNextNode();
          }
          swap(links, min);
          links = links.getNextNode();
      }
      this.toFirst();
      teilweiseSortiert = true;
      sortiert = true;
  }
  
  /**
   * führt abstrakte Übersetzung des Selectionsorts auf Liste aus
   */
  public void selectionsort2()
  {
      SortableList liste = new SortableList();
      ListNode obj, min;
      while(!this.isEmpty())
      {
          min = first;
        obj = min.getNextNode();
          while(obj != null)
          {
              if(obj.getContentObject().isLess(min.getContentObject()))
                  min = obj;
              
              obj = obj.getNextNode();
          }
          liste.append(min.getContentObject());
          current = min;
          this.remove();
      }
      this.concat(liste);
      this.toFirst();
      teilweiseSortiert = true;
      sortiert = true;
  }
 
  /**
   * führt Quicksort auf Liste aus und wählt das erste Element als pivot
   * Wenn ein Teil der Liste sortiert, ist das erste Element wahrscheinlich das kleinste und somit wird das letzte genommen, welches vermutlich neu hinzugefügt wurde
   */
 public void quicksort()
 {
     if(first != last && !sortiert)
     {
         ContentType pivot;
        if(teilweiseSortiert)
            pivot = last.getContentObject();
        else
            pivot = first.getContentObject();
        
        SortableList<ContentType> hilfsliste = new SortableList();
        current = first;
        this.remove();
        while(this.hasAccess())
        {
            while(this.hasAccess())
            {
                if(pivot.isLess(current.getContentObject()))
                {
                    hilfsliste.append(current.getContentObject());
                    this.remove();
                }
                else
                    this.next();
            }
        }
            hilfsliste.quicksort();
            this.quicksort();
            this.append(pivot);
            this.concat(hilfsliste);            
     }
     this.toFirst();
     teilweiseSortiert = true;
     sortiert = true;
 }
 
 /**
  * führt Insertionsort auf Liste aus
  */
 public void insertionsort()
 {
     current = first;
     SortableList<ContentType> hilfsliste = new SortableList();
     while(current != null)
     {
         hilfsliste.toFirst();
         while(hilfsliste.hasAccess())
         {
             if(current.getContentObject().isLess(hilfsliste.getContent()))
             {
                 hilfsliste.insert(current.getContentObject());
                 break;
             }
             hilfsliste.next();
         }
         if(!hilfsliste.hasAccess())
             hilfsliste.append(current.getContentObject());
         this.remove();
     }
     
     this.concat(hilfsliste);
     current = first;
     teilweiseSortiert = true;
     sortiert = true;
 }
 
 /**
  * ermittelt die Länge der Liste und gibt diese zurück
  * @return Länge
  */
  private int length()
  {
      int i = 0;
      ListNode obj = first;
      while(obj != null)
      {
          i++;
          obj = obj.getNextNode();
      }
      return i;
  }
  
  /**
   * mischt die Liste
   */
  public void mischen()
  {
      this.toFirst();
      for(int i = 0; i < 3; i++)
        while(this.hasAccess())
        {
            if(Math.round(Math.random() * 4) == 1 && current.getNextNode() != null)
                this.swap(current, current.getNextNode());
            else if(Math.round(Math.random() * 4) == 2)
                this.swap(current, first);
            else if(Math.round(Math.random() * 4) == 3)
                this.swap(first, last);
            else if(Math.round(Math.random() * 4) == 4)
                this.swap(current, last);

            this.next();
        }
      this.toFirst();
      teilweiseSortiert = false;
  }
  
  /**
   * führt bogosort auf Liste aus
   */
  public void bogosort()
  {
      while(!this.sortiert())
          this.mischen();
      teilweiseSortiert = true;
      sortiert = true;
  }
  
  /**
   * überprüft, ob die liste sortiert ist
   * @return liste sortiert?
   */
  private boolean sortiert()
  {
      current = first;
      while(current.getNextNode() != null)
      {
          if(!current.getContentObject().isLess(current.getNextNode().getContentObject()))
          {
              current = first;
              return false;
          }
          current = current.getNextNode();
      }
      current = first;
      return true;
  }
  
  @Override
  public void append(ContentType pContent) {
    if (pContent != null) { // Nichts tun, wenn es keine Inhalt gibt.

      if (this.isEmpty()) { // Fall: An leere Liste anfuegen.
        this.insert(pContent);
      } else { // Fall: An nicht-leere Liste anfuegen.

        // Neuen Knoten erstellen.
        ListNode newNode = new ListNode(pContent); 

        last.setNextNode(newNode);
        last = newNode; // Letzten Knoten aktualisieren.
      }

    }
    sortiert = false;
  }
  
}

