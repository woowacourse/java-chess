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
        return strategy.movablePositions(source, directions, this);
    }

    private void validateTurnOfPiece(final Piece currentPiece) {
        if (currentPiece.isNotSameColor(turn)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
    }

    private void updateBoard(final Position source, final Position target, final Piece currentPiece) {
        Piece targetPiece = squares.get(target);
        if (targetPiece.isNotNone()) {
            currentPiece.isSameColor(targetPiece.color());
            squares.remove(target);
            squares.put(target, currentPiece);
            squares.put(source, new None(Color.NONE, Type.NONE));
        }
        squares.put(target, currentPiece);
        squares.put(source, new None(Color.NONE, Type.NONE));
    }

    public Piece findPieceByPosition(final Position position) {
        return squares.get(position);
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
