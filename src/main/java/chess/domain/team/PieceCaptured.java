package chess.domain.team;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;

public final class PieceCaptured {
    private final List<Piece> pieceCaptured;

    public PieceCaptured() {
        pieceCaptured = new ArrayList<>();
    }

    public void add(final Piece piece) {
        pieceCaptured.add(piece);
    }
}
