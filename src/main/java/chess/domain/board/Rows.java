package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.position.Column;
import chess.domain.position.PositionFactory;
import chess.domain.position.Row;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Rows {
    private final List<Piece> pieces;
    private final Column column;

    public Rows(Column column, Pieces pieces) {
        this.column = column;
        this.pieces = Arrays.stream(Row.values())
                .map(row -> PositionFactory.of(row, column))
                .map(pieces::findByPosition)
                .collect(Collectors.toList());
    }

    public String getResources() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Piece piece : pieces) {
            stringBuilder.append(piece.getResource());
        }
        return stringBuilder.toString();
    }

    public Column getColumn() {
        return column;
    }
}
