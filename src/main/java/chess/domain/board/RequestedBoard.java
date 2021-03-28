package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class RequestedBoard {

    private RequestedBoard() {
    }

    public static Map<Position, Piece> emptyBoard() {
        return new HashMap<>();
    }

    public static Map<Position, Piece> board() {
        InitializedBoard initializedBoard = new InitializedBoard();

        return initializedBoard.locatePieces();
    }
}
