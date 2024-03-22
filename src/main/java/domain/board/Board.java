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

    public void move(final Position source, final Position target) {
        final Piece currentPiece = squares.get(source);
        if (currentPiece.isNotSameColor(turn)) {
            throw new IllegalArgumentException("현재 차례가 아닙니다.");
        }
        final List<Direction> directions = currentPiece.movableDirections();

        final MoveStrategy strategy = currentPiece.strategy();
        final List<Position> movablePositions = strategy.movablePositions(source, directions, this);

        final boolean targetMovable = movablePositions.stream().anyMatch(position -> position.equals(target));
        validateMovablePosition(targetMovable);
        updateBoard(source, target, currentPiece);
        switchTurn();
    }

    private void switchTurn() {
        turn = turn.reverse();
    }

    private void validateMovablePosition(final boolean targetMovable) {
        if (!targetMovable) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
    }

    private void updateBoard(final Position source, final Position target, final Piece currentPiece) {
        if (squares.get(target).isNotNone()) {
            squares.get(target).isSameColor(currentPiece);
            squares.remove(target);
            squares.put(target, currentPiece);
            squares.put(source, new None(Color.NONE, Type.NONE));
        }
        squares.put(target, currentPiece);
        squares.put(source, new None(Color.NONE, Type.NONE));
    }

    public Map<Position, Piece> squares() {
        return squares;
    }
}
