package chess.domain.piece.strategy;

import chess.domain.board.Square;
import chess.domain.dto.BoardDto;
import chess.domain.piece.Color;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class RookMoveStrategy implements MoveStrategy {
    private static List<Direction> movableDirections = Direction.linearDirection();

    @Override
    public boolean canMove(BoardDto boardDto, Position from, Position to) {
        Direction direction = Direction.from(from.calculateXDegree(to), from.calculateYDegree(to));

        if (!isProperDirection(direction)) {
            throw new IllegalArgumentException("움직일 수 없는 방향입니다.");
        }

        validateBlockedRoute(boardDto, getRoute(direction, from, to));

        if (boardDto.findByPosition(to).hasPiece()) {
            validateSameColorPiece(boardDto, from, to);
        }

        return true;
    }

    private void validateBlockedRoute(BoardDto boardDto, List<Position> route) {
        boolean blocked = route.stream()
                .map(boardDto::findByPosition)
                .anyMatch(Square::hasPiece);

        if (blocked) {
            throw new IllegalArgumentException("중간에 말이 있어 행마할 수 없습니다.");
        }
    }

    private void validateSameColorPiece(BoardDto boardDto, Position from, Position to) {
        Color fromColor = boardDto.findByPosition(from).getPiece().getColor();
        Color toColor = boardDto.findByPosition(to).getPiece().getColor();

        if (fromColor == toColor) {
            throw new IllegalArgumentException("동일한 진영의 말이 있어서 행마할 수 없습니.");
        }
    }

    private List<Position> getRoute(Direction direction, Position from, Position to) {
        List<Position> route = new ArrayList<>();
        Position currentPosition = from.getNextPosition(direction);

        while (!currentPosition.equals(to)) {
            route.add(currentPosition);
            currentPosition = currentPosition.getNextPosition(direction);
        }
        return route;
    }

    private boolean isProperDirection(Direction direction) {
        return movableDirections.contains(direction);
    }
}
