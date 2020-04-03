package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.PlayerColor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends GamePiece {

    private static final int FIRST_MOVE_COUNT = 2;

    private Direction moveDirection;
    private List<Direction> killDirections;
    private int moveCount;

    public Pawn(PlayerColor playerColor) {
        super("p", Arrays.stream(Column.values()).map(file -> Position.of(file, Row.TWO)).collect(Collectors.toList()),
                1, playerColor);
        moveCount = 1;
        moveDirection = Direction.N;
        killDirections = Arrays.asList(Direction.NW, Direction.NE);
        if (playerColor.equals(PlayerColor.BLACK)) {
            moveDirection = Direction.S;
            killDirections = Arrays.asList(Direction.SE, Direction.SW);
        }
    }

    @Override
    public boolean canMoveTo(Board board, Position source, Position target) {
        if (board.isNotEmpty(target)) {
            return canKill(source, target);
        }

        return canJustMove(board, source, target);
    }

    private boolean canKill(Position source, Position target) {
        return killDirections.stream()
                .map(direction -> source.pathTo(direction, moveCount))
                .anyMatch(eachPath -> eachPath.contains(target));
    }

    private boolean canJustMove(Board board, Position source, Position target) {
        List<Position> path = source.pathTo(moveDirection, moveCount);
        if (originalPositions.contains(source)) {
            path = source.pathTo(moveDirection, FIRST_MOVE_COUNT);
        }

        if (!path.contains(target)) {
            return false;
        }

        return path.subList(0, path.indexOf(target))
                .stream()
                .noneMatch(board::isNotEmpty);
    }
}
