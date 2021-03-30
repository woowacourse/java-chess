package chess.domain.board;

import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardFactory {
    private ChessBoardFactory() {
    }

    public static ChessBoard initializeBoard() {
        Map<String, Position> positions = Position.getPositions();
        Map<Position, Piece> board = new LinkedHashMap<>();
        positions.values().forEach(value -> board.put(value, new Blank()));
        return new ChessBoard(board);
    }
}
