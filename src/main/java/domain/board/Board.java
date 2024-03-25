package domain.board;

import domain.piece.None;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.Direction;
import domain.piece.info.Type;
import domain.strategy.MoveStrategy;
import java.util.List;
import java.util.Map;

public class Board {
    private static final int BOARD_UPPER_BOUND = 7;
    private static final int BOARD_LOWER_BOUND = 0;

    private Color turn;
    private final Map<Position, Piece> squares;

    public Board(final Map<Position, Piece> squares) {
        this.turn = Color.WHITE;
        this.squares = squares;
    }

    public void moveByPosition(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        validateTurnOfPiece(currentPiece);
        final List<Direction> directions = currentPiece.movableDirections();

        final List<Position> movablePositions = findMovablePositions(source, currentPiece, directions);
        target.isMovable(movablePositions);

        updateBoard(source, target, currentPiece);
        turn = Color.opposite(turn);
    }

    private List<Position> findMovablePositions(final Position source, final Piece currentPiece,
                                                final List<Direction> directions) {
        final MoveStrategy strategy = currentPiece.strategy();
        List<Position> positions = strategy.movablePositions(source, directions, squares);
        return positions.stream()
                .filter(this::isFileInBoard)
                .filter(this::isRankInBoard)
                .toList();
    }

    private void validateTurnOfPiece(final Piece currentPiece) {
        if (currentPiece.isNotSameColor(turn)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    private void updateBoard(final Position source, final Position target, final Piece currentPiece) {
        Piece targetPiece = squares.get(target);
        currentPiece.isSameColor(targetPiece.color());
        squares.put(target, currentPiece);
        squares.put(source, new None(Color.NONE, Type.NONE));
    }

    private boolean isFileInBoard(final Position source) {
        int file = source.fileIndex();
        return file >= BOARD_LOWER_BOUND && file <= BOARD_UPPER_BOUND;
    }

    private boolean isRankInBoard(final Position source) {
        int rank = source.rankIndex();
        return rank >= BOARD_LOWER_BOUND && rank <= BOARD_UPPER_BOUND;
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
