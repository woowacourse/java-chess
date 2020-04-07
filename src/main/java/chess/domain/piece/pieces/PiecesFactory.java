package chess.domain.piece.pieces;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.position.Row;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PiecesFactory {
    public static Pieces create() {
        return Arrays.stream(PieceInitializer.values())
                .map(Piece::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Pieces::new));
    }

    public static Pieces create(Map<String, String> rows) {
        List<Piece> pieces = new ArrayList<>();

        for (Column column : Column.values()) {
            String rowString = rows.get(column.getName());
            pieces.addAll(createPiecesByRow(column, rowString));
        }
        return new Pieces(pieces);
    }

    private static List<Piece> createPiecesByRow(Column column, String rowString) {
        List<Piece> pieces = new ArrayList<>();
        List<String> strings = Arrays.asList(rowString.split(""));

        for (Row row : Row.values()) {
            Position position = PositionFactory.of(row, column);
            pieces.add(PieceFactory.of(strings.get(row.getValue() - 1), position));
        }
        pieces.removeIf(Piece::isBlank);

        return pieces;
    }
}
