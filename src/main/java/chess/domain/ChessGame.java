package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Map;

public class ChessGame {
    public Map<Position, Piece> start() {
        Board board = new Board();
        return board.getSquares();
    }
}
