package chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieceMap;

    public Board() {
        final Map<Position, Piece> initBoard = new HashMap<>();
        Arrays.stream(PieceType.values())
            .forEach(pieceType -> {
                pieceType.getInitialPositions()
                    .forEach((key, value) -> value
                        .forEach(position -> {
                            initBoard.put(position, new Piece(key, pieceType));
                        }));
            });
        this.pieceMap = initBoard;
    }

    public Map<Position, Piece> getPieceMap() {
        return pieceMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        List<Row> reversedRows = new ArrayList<>(Arrays.asList(Row.values()));
        Collections.reverse(reversedRows);
        reversedRows.forEach(r -> {
            Arrays.stream(Column.values())
                .forEach(c -> {
                    final Position position = new Position(r, c);
                    final Piece piece = pieceMap.get(position);
                    if (piece == null) {
                        sb.append(".");
                    } else {
                        sb.append(piece.getPieceType()
                            .getSymbol());
                    }
                });
            sb.append("\n");
        });
        return sb.toString();
    }
}
