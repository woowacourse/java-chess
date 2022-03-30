package chess.domain.board;

import chess.domain.Color;
import chess.domain.PieceConvertor;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoardFactory implements BoardFactoryStrategy {
    private static final int COLOR_CRITERIA = 15;
    private static final String PIECE_ORDER = "RNBQKBNRPPPPPPPPpppppppprnbqkbnr";
    private static final Map<Position, Piece> startBoard = new LinkedHashMap<>();

    public BoardFactory() {
    }

    static {
        List<Position> initialPositions = getInitialPositions();
        List<String> pieceOrders = Arrays.stream(PIECE_ORDER.split(""))
                .collect(Collectors.toList());

        for (int i = 0; i < pieceOrders.size(); i++) {
            startBoard.put(initialPositions.get(i), PieceConvertor.of(pieceOrders.get(i), checkColor(i)));
        }
    }

    @Override
    public Board createBoard() {
        return new Board(startBoard);
    }

    private static List<Position> getInitialPositions() {
        return Rank.initialRows()
                .stream()
                .flatMap(BoardFactory::getPositionStream)
                .collect(Collectors.toList());
    }

    private static Stream<Position> getPositionStream(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(rank, file));
    }

    private static Color checkColor(int index) {
        if (index > COLOR_CRITERIA) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }
}
