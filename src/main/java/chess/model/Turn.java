package chess.model;

import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;

import chess.model.piece.PieceColor;
import java.util.List;

public class Turn {

    private static final List<PieceColor> COLORS = List.of(WHITE, BLACK);
    private static final int PLAYER_COUNT = COLORS.size();

    private int count;

    public Turn() {
        this.count = 0;
    }

    public PieceColor findCurrentPlayer() {
        final int colorIndex = count % PLAYER_COUNT;

        return COLORS.get(colorIndex);
    }

    public void next() {
        count++;
    }

    public List<PieceColor> allPlayers() {
        return COLORS;
    }
}
