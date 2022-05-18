////////////////////////////////////////////////////////////////////
// XIDA CHEN 1217780
// DANILO STOJKOVIC 1222399
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

public class EItem implements MenuItem{
    private itemType type = null;
    private String name;
    private double price;

    public EItem(itemType type, String name, double price){
        this.type = type;
        this.name = name;
        this.price = price;
    }
    
    public itemType getType(){
        return this.type;
    }
    public String getName(){
        return this.name;
    }
    public double getPrice(){
        return this.price;
    }
}
