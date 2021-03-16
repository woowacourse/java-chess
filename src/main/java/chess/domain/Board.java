package chess.domain;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.position.Horizontal;
import chess.domain.position.Position;
import chess.domain.position.Vertical;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard = new HashMap<>();

    public void initializePawn() {
        for (Horizontal horizontal : Horizontal.values()) {
            chessBoard.put(new Position(horizontal, Vertical.of("2")), new Pawn(false));
            chessBoard.put(new Position(horizontal, Vertical.of("7")), new Pawn(true));
        }
    }

    public Map<Position, Piece> unwrap() {
        return chessBoard;
    }
}
