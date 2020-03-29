package chess.domain.piece;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.exception.InvalidMovementException;
import chess.domain.player.PlayerColor;

public class Pawn extends GamePiece {

    public Pawn(PlayerColor playerColor) {
        super("p", Arrays.stream(Column.values()).map(file -> Position.of(file, Row.TWO)).collect(Collectors.toList()),
               Arrays.asList(Direction.NE, Direction.NW), 1, 1, playerColor);
    }

    @Override
    public void validatePath(Map<Position, GamePiece> board, Position source, Position target) {
        // direction 수정
        List<Direction> killDirections = directions;
        Direction moveDirection = Direction.N;
        if (playerColor.equals(PlayerColor.BLACK)) {
            killDirections = Arrays.asList(Direction.SE, Direction.SW);
            moveDirection = Direction.S;
        }

        // target 위치에 기물이 있는 경우
        if (board.get(target) != EmptyPiece.getInstance()) {
            killDirections.stream()
                    .map(direction -> source.pathTo(direction, moveCount))
                    .filter(eachPath -> eachPath.contains(target))
                    .findFirst()
                    .orElseThrow(() -> new InvalidMovementException("이동할 수 없는 경로입니다."));
            return;
        }

        //target 위치에 기물이 없는 경우
        List<Position> path = source.pathTo(moveDirection, moveCount);
        if (originalPositions.contains(source)) {
            path = source.pathTo(Direction.N, 2);
        }

        if (!path.contains(target)) {
            throw new InvalidMovementException("이동할 수 없는 경로입니다.");
        }

        for (Position position : path) {
            if (board.get(position) != EmptyPiece.getInstance()) {
                throw new InvalidMovementException("경로에 기물이 존재합니다.");
            }
        }
    }




}
