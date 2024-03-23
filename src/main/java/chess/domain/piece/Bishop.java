package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

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
    void validatePieceMoveRule(Coordinate source, Coordinate target, Board board) {
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
            movablePath.add(coordinate);
            if (board.isPiecePresent(coordinate)) {
                break;
            }
        }

        return movablePath;
    }
}
