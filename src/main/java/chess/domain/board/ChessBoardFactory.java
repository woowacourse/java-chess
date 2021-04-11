package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoardFactory {
    private ChessBoardFactory() {
    }

    public static Map<Position, Piece> initializeBoard() {
        Map<Position, Piece> board = new LinkedHashMap<>();
        Map<String, Position> positions = Position.getPositions();
        positions.values().forEach(value -> board.put(value, null));
        return board;
    }

    public static Map<Position, Piece> loadBoard(final Map<String, String> boardFromDB) {
        Map<Position, Piece> board = new LinkedHashMap<>();
        for (Map.Entry<String, String> boardFromDBEntry : boardFromDB.entrySet()) {
            board.put(Position.findByString(
                    boardFromDBEntry.getKey()),
                    PieceFactory.createByString(boardFromDBEntry.getValue(), boardFromDBEntry.getKey()));
        }
        return board;
    }
}
