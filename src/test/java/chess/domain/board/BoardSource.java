package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Placeable;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.Map;

public class BoardSource {
    private Map<Position, Placeable> boardSource;

    public BoardSource() {
        this.boardSource = new HashMap<>();

        fillWithEmpty(boardSource);
    }

    private static void fillWithEmpty(Map<Position, Placeable> board) {
        for (Column column : Column.values()) {
            for (Row row : Row.values()) {
                Position position = Position.of(column, row);
                board.put(position, new Empty());
            }
        }
    }

    public void addPiece(Position position, Placeable placeable) {
        boardSource.put(position, placeable);
    }

    public Map<Position, Placeable> getSource() {
        return boardSource;
    }
}
