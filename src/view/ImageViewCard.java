package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import model.Card;

public class ImageViewCard extends ImageView {
    private Card card;
    public ImageViewCard(Image cards, Card card) {
        super(cards);
        this.card = card;
    }

    public Card getCard() {  return card;  }
}
