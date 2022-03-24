package chess.domain.board;

import chess.domain.Color;
import chess.domain.PieceConvertor;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFactory {
    private static final int COLOR_CRITERIA = 15;
    private static final String PIECE_ORDER = "RNBQKBNRPPPPPPPPpppppppprnbqkbnr";
    private static final Map<Position, Piece> startBoard = new LinkedHashMap<>();

    private BoardFactory() {
    }

    static {
        List<Position> collect = getInitialPositions();
        List<String> pieceOrders = Arrays.stream(PIECE_ORDER.split(""))
                .collect(Collectors.toList());

        for (int i = 0; i < pieceOrders.size(); i++) {
            startBoard.put(collect.get(i), PieceConvertor.of(pieceOrders.get(i), checkColor(i)));
        }
    }

    public static Board newInstance() {
        return new Board(startBoard);
    }

    public static Board newInstance(Map<Position, Piece> board) {
        return new Board(board);
    }

    private static List<Position> getInitialPositions() {
        return Rank.initialRows()
                .stream()
                .flatMap(getPositionStream())
                .collect(Collectors.toList());
    }

    private static Function<Rank, Stream<? extends Position>> getPositionStream() {
        return rank ->
                Arrays.stream(File.values())
                        .map(file -> Position.of(rank, file));
    }

    private static Color checkColor(int index) {
        if (index > COLOR_CRITERIA) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
