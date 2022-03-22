package chess.domain;

import chess.domain.piece.*;

public class Board {
    private final Pieces pieces;

    public Board(Pieces pieces) {
        this.pieces = pieces;
    }
}
