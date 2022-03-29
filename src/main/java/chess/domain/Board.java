package chess.domain;

import chess.domain.piece.Nothing;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.postion.Position;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private Map<Position, Piece> cells;

    public Board(final Map<Position, Piece> cells) {
        this.cells = cells;
    }

    public Board movePiece(Position source, Position target) {
        validateSource(source);
        Piece piece = cells.get(source);
        validateMoving(piece, source, target);

        cells.remove(source);
        cells.put(target, piece);

        final var newCells = new HashMap<>(cells);
        return new Board(newCells);
    }

    private void validateSource(Position position) {
        if (!cells.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void validateMoving(Piece piece, Position source, Position target) {
        piece.canMove(source, target);
        List<Direction> directions = piece.possibleDirections();

        Direction direction = Direction.beMoveDirection(directions, source, target);

        Piece pieceInTarget = cells.getOrDefault(target, new Nothing());

        piece.checkPawn(source, target, direction, pieceInTarget);

        isMovablePath(direction, source, target);
    }

    private void isMovablePath(Direction direction, Position source, Position target) {
        Position currentPosition = source.from(direction);
        while (!currentPosition.equals(target)) {
            hasPieceInPath(currentPosition);
            currentPosition = currentPosition.from(direction);
        }
    }

    private void hasPieceInPath(Position position) {
        if (cells.containsKey(position)) {
            throw new IllegalArgumentException("경로에 기물이 존재합니다.");
        }
    }

    public Map<Position, Piece> cells() {
        return cells;
    }
}
