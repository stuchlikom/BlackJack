import java.util.ArrayList;

class Participant{

    private ArrayList<Card> cardsHand = new ArrayList<>();

    public String toString(){
        String allCards = "";

        for (Card card: cardsHand){
        allCards = allCards + card.toName();
        } // Ende for

        return allCards;
    } // Ende toString

    public int getSum(){
        int sum = 0;

        for (Card card: cardsHand){
            sum = sum + card.getValue();
        } // Ende for
        
        return sum;
    } // Ende getSum

    public void takeCard(Card card){
        cardsHand.add(card);

    } // Ende takeCard

    public void aceEleven(){
        cardsHand.get(cardsHand.size() - 1).setValue(11);

    } // Ende aceEleven



} // Ende class