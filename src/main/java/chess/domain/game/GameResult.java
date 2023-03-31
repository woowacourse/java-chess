package chess.domain.game;

import chess.domain.Price;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.position.Position;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private final static int PAWN_SPECIAL_COUNT = 2;

    private final Map<Position, Piece> board;

    public GameResult(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static GameResult from(final Board board) {
        return new GameResult(board.getBoard());
    }

    public Side calculateAdvantageSide() {
        final double whiteSidePrice = calculatePriceBySide(Side.WHITE);
        final double blackSidePrice = calculatePriceBySide(Side.BLACK);

        if (whiteSidePrice > blackSidePrice) {
            return Side.WHITE;
        }
        if (whiteSidePrice < blackSidePrice) {
            return Side.BLACK;
        }
        return Side.NEUTRALITY;
    }

    public double calculatePriceBySide(final Side side) {
        final Price totalPrice = calculateTotalPriceBySide(side);
        final Long totalCountOfPawnInFile = calculateTotalCountOfPawnInFile(side);

        final Price price = totalPrice.subtractPawnSpecialPrice(totalCountOfPawnInFile);
        return price.getValue();
    }

    private Price calculateTotalPriceBySide(final Side side) {
        return board.values().stream()
                .filter(piece -> piece.side() == side)
                .map(Piece::price)
                .reduce(Price.from(0), Price::add);
    }

    private Long calculateTotalCountOfPawnInFile(final Side side) {
        final Map<Integer, Long> fileIndexByPawnCount = getFileIndexByPawnCount(side);

        return fileIndexByPawnCount.values().stream()
                .filter(pawnCount -> pawnCount >= PAWN_SPECIAL_COUNT)
                .reduce(0L, Long::sum);
    }

    private Map<Integer, Long> getFileIndexByPawnCount(final Side side) {
        return board.keySet().stream()
                .filter(position -> board.get(position).isPawn())
                .filter(position -> board.get(position).side() == side)
                .collect(Collectors.groupingBy(Position::fileIndex, Collectors.counting()));
    }
}
