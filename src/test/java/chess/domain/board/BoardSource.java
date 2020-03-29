package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardSource {
    private Map<Position, Piece> boardSource;

    public BoardSource() {
        this.boardSource = new HashMap<>();
    }

    public void addPiece(Position position, Piece piece) {
        boardSource.put(position, piece);
    }

    public void addRow(List<Piece> pieces, Row row) {
        for (Piece piece : pieces) {
            for (Column column : Column.values()) {
                Position position = Position.of(column, row);
                addPiece(position, piece);
            }
        }
    }

    public Map<Position, Piece> getSource() {
        return boardSource;
    }
}
