package chess.domain.piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import chess.domain.board.Board;
import chess.domain.board.Coordinate;

public class Pawn extends AbstractPiece {

    private static final List<Map.Entry<Integer, Integer>> FORWARD_WEIGHTS = List.of(
            Map.entry(1, 0),
            Map.entry(2, 0)
    );

    private static final List<Map.Entry<Integer, Integer>> DIAGONAL_WEIGHT = List.of(
            Map.entry(1, -1),
            Map.entry(1, 1)
    );

    //TODO: 상수의 올바른 위치를 생각해보기.
    private static final int INITIAL_BLACK_PAWN_RANK = 7;
    private static final int INITIAL_WHITE_PAWN_RANK = 2;

    public Pawn(Team team) {
        super(PieceType.PAWN, team);
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

        List<Coordinate> diagonalPath = createPath(source, DIAGONAL_WEIGHT);
        List<Coordinate> forwardPath = createPath(source, FORWARD_WEIGHTS);
        if (!(forwardPath.contains(target) || diagonalPath.contains(target))) {
            throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
        }

        //TODO: depth 정리
        if (diagonalPath.contains(target)) {
            if (!board.isPiecePresent(target)) {
                throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
            }

            if (!isEnemy(targetPiece)) {
                throw new IllegalStateException("해당 기물은 주어진 좌표로 이동할 수 없습니다.");
            }
        }

        if (forwardPath.contains(target)) {
            if (Math.abs(source.getRank() - target.getRank()) == 2 &&
                    !(source.getRank() == INITIAL_BLACK_PAWN_RANK || source.getRank() == INITIAL_WHITE_PAWN_RANK)) {
                throw new IllegalStateException("초기 상태의 폰이 아닌 경우, 2칸 이동할 수 없습니다.");
            }

            List<Coordinate> movablePath = createMovablePath(forwardPath, board);
            if (!movablePath.contains(target)) {
                throw new IllegalStateException("기물로 막혀있어 이동할 수 없습니다.");
            }
        }
    }

    public List<Coordinate> createPath(Coordinate source, List<Map.Entry<Integer, Integer>> weights) {
        List<Coordinate> possibleCoordinate = new ArrayList<>();
        int startRank = source.getRank();
        char startFile = source.getFile();

        //TODO: forwardDirection 가중치를 WEIGHTS에 곱한채로 저장하는 방법을 고려해보기
        // 행열의 곱을 이용하는 것은 어떠한가?
        // Weight(x, y).multifly(Weight(x, y)) = Weight(x * x, y * y)
        for (Map.Entry<Integer, Integer> weight : weights) {
            int nextRank = startRank + weight.getKey() * getTeam().getForwardDirection();
            char nextFile = (char) (startFile + weight.getValue());

            try {
                Coordinate coordinate = new Coordinate(nextRank, nextFile);
                possibleCoordinate.add(coordinate);
            } catch (IllegalArgumentException ignored) {
            }
        }

        return possibleCoordinate;
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
