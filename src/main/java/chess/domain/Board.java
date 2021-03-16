package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class Board {
    private final Map<Position, Piece> chessBoard;

    public Board() {
        chessBoard = BoardInitializer.initializeBoard();
    }

    public Map<Position, Piece> unwrap() {
        return chessBoard;
    }

}
