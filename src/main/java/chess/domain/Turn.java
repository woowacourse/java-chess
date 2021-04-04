package chess.domain;

import chess.domain.piece.Color;

public class Turn {
    private static final Player whitePlayer = new Player(Color.WHITE);
    private static final Player blackPlayer = new Player(Color.BLACK);
    private Player player = whitePlayer;

    public Turn() {
    }

    public Turn(final String text) {
        if (Color.BLACK.name().equals(text)) {
            player = blackPlayer;
        }
    }

    public void next() {
        if (player.equals(blackPlayer)) {
            player = whitePlayer;
            return;
        }
        if (player.equals(whitePlayer)) {
            player = blackPlayer;
        }
    }

    public Player player() {
        return player;
    }
}
