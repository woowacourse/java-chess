package chess.domain;

import chess.domain.piece.Piece;
import chess.domain.postion.Position;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private Map<Position, Piece> cells;

    public Board(final Map<Position, Piece> cells) {
       this.cells = cells;
    }

    public Board movePiece(Position source, Position target) {
        validateSource(source);
        Piece piece = cells.get(source);

        piece.canMove(source, target);
        Direction direction =  Direction.beMoveDirection(
                piece.possibleDirections(), source, target);

        isMovablePath(direction, source ,target);

        cells.remove(source);
        cells.put(target, piece);

        final var newCells = new HashMap<>(cells);
        return new Board(newCells);
    }

    private void validateSource(Position position) {
        if(!cells.containsKey(position)) {
            throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
        }
    }

    private void isMovablePath(Direction direction, Position source, Position target) {
        Position currentPosition = source;
        while(!currentPosition.equals(target)) {
            hasPieceInPath(currentPosition);
            currentPosition = currentPosition.from(direction);
        }
    }

    private void hasPieceInPath(Position position) {
        if(cells.containsKey(position)) {
            throw new IllegalArgumentException("경로에 기물이 존재합니다.");
        }
    }

    public Map<Position, Piece> cells() {
        return cells;
    }
}
