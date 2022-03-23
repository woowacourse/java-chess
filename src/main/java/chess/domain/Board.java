package chess.domain;

import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private static final int COLOR_CRITERIA = 15;
    private static final String PIECE_ORDER = "RNBQKBNRPPPPPPPPpppppppprnbqkbnr";
    private static final Map<Position, Piece> startBoard = new LinkedHashMap<>();

    private final Map<Position, Piece> board;

    static {
        List<Position> collect = getInitialPositions();
        List<String> pieceOrders = Arrays.stream(PIECE_ORDER.split(""))
            .collect(Collectors.toList());

        for (int i = 0; i < pieceOrders.size(); i++) {
            startBoard.put(collect.get(i), PieceConvertor.of(pieceOrders.get(i), checkColor(i)));
        }
    }

    private static List<Position> getInitialPositions() {
        return Row.initialRows()
            .stream()
            .flatMap(getPositionStream())
            .collect(Collectors.toList());
    }

    private static Function<Row, Stream<? extends Position>> getPositionStream() {
        return row ->
            Arrays.stream(Column.values())
                .map(column -> Position.of(row, column));
    }

    private static Color checkColor(int index) {
        if (index > COLOR_CRITERIA) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public Board() {
        this.board = new LinkedHashMap<>(startBoard);
    }

    public Map<Position, Piece> getBoard() {
        return new LinkedHashMap<>(board);
    }
}
