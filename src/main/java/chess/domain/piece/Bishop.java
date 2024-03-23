package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;
import chess.domain.board.Direction;

public class Bishop extends AbstractPiece {

    private final static List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.LEFT_DOWN,
            Direction.LEFT_UP,
            Direction.RIGHT_DOWN,
            Direction.RIGHT_UP
    );

    public Bishop(Team team) {
        super(PieceType.BISHOP, team);
    }

    @Override
    public List<Coordinate> findMovablePath(Coordinate start, Coordinate destination) {
        return POSSIBLE_DIRECTIONS.stream()
                .map(possibleDirection -> possibleDirection.createPath(start))
                .filter(coordinates -> coordinates.contains(destination))
                .findFirst()
                .orElse(Collections.emptyList());
    }

    @Override
    public void validateMovable(Coordinate source, Coordinate target, Board board) {
        Piece targetPiece = board.findByCoordinate(target);
        if (source.equals(target)) {
            throw new IllegalStateException("제자리 이동은 할 수 없습니다.");
        }

        if (isSameTeam(targetPiece)) {
            throw new IllegalStateException("아군 기물은 공격할 수 없습니다.");
        }

        List<Coordinate> possiblePath = createPath(source, target, board);
        if (!possiblePath.contains(target)) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        List<Coordinate> movablePath = createMovablePath(possiblePath, board);
        if (!movablePath.contains(target)) {
            throw new IllegalStateException("기물로 막혀있어 이동할 수 없습니다.");
        }
    }

    private List<Coordinate> createPath(Coordinate source, Coordinate target, Board board) {
        return POSSIBLE_DIRECTIONS.stream()
                .map(possibleDirection -> possibleDirection.createPath(source))
                .filter(coordinates -> coordinates.contains(target))
                .findFirst()
                .orElse(Collections.emptyList());
    }

    private List<Coordinate> createMovablePath(List<Coordinate> possiblePath, Board board) {
        List<Coordinate> movablePath = new ArrayList<>();

        for (Coordinate coordinate : possiblePath) {
            if (board.isPiecePresent(coordinate)) {
                break;
            }

            movablePath.add(coordinate);
        }

        return movablePath;
    }
}
