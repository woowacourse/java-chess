package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.board.position.Position;

import java.util.List;
import java.util.Map;

public class ChessGame {
    public List<Map<Position, Piece>> start() {
        Board board = new Board();
        return board.getSquares();
    }
}
