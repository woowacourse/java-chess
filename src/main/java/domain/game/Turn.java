package domain.game;

import domain.piece.Color;

public class Turn {

    private static final Turn whiteTurn = new Turn(Color.WHITE);
    private static final Turn blackTurn = new Turn(Color.BLACK);

    private final Color color;

    private Turn(Color color) {
        this.color = color;
    }

    public static Turn makeInitialTurn() {
        return whiteTurn;
    }

    public Turn changeTurn() {
        if (color.isBlack()) {
            return whiteTurn;
        }
        return blackTurn;
    }

    public Color getColor() {
        return color;
    }
}
