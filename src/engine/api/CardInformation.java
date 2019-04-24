package engine.api;

/**
 * CardInformation
 */
public class CardInformation {

    public boolean used = false;

    public CardInformation(){
        
    }
    public CardInformation(CardInformation card){
        this.used = card.used;
    }
}