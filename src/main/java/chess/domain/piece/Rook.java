package chess.domain.piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public class Rook extends AbstractPiece {

    // TODO: 이거 안쓰는 기물들은 뭐지..?
    private static final List<Direction> POSSIBLE_DIRECTIONS = List.of(
            Direction.UP,
            Direction.DOWN,
            Direction.LEFT,
            Direction.RIGHT
    );

    public Rook(Team team) {
        super(PieceType.ROOK, team);
    }

    // TODO: 메서드 추출, 상위 클래스 추출
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

    // TODO: depth 개선
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
