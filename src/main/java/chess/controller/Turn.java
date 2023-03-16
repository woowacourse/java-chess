package chess.controller;

import static chess.model.piece.PieceColor.BLACK;
import static chess.model.piece.PieceColor.WHITE;

import chess.model.Color;
import chess.model.piece.PieceColor;
import java.util.List;

public class Turn {

    private static final List<PieceColor> COLORS = List.of(WHITE, BLACK);

    private int turn;

    public Turn() {
        this.turn = 0;
    }

    public PieceColor getTurn() {
        final int colorIndex = turn++ % 2;
        return COLORS.get(colorIndex);
    }
}
