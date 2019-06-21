package chess.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Game {
    private Map<Point, Piece> board;

    public Game(Map<Point, Piece> board) {
        this.board = board;
    }

    public void move(Point start, Point end) {
        checkBlank(start);
        List<Point> candidatePoints = board.get(start).getCandidatePoints(start, end);
        checkMove(candidatePoints);
        for (Point candidatePoint : candidatePoints) {
            checkRoute(end, candidatePoint);
        }
        checkDestination(start, end);
        movePiece(start, end);
    }

    private void movePiece(Point start, Point end) {
        Piece piece = board.get(start);
        board.put(end, piece);
        board.remove(start);
    }

    private void checkDestination(Point start, Point end) {
        if (board.containsKey(end)) {
            Piece startPiece = board.get(start);
            Piece endPiece = board.get(end);
            checkSameTeam(startPiece, endPiece);
            kill(!startPiece.isSameTeamPiece(endPiece), end);
        }
    }

    private void kill(boolean canKill, Point end) {
        if (canKill) {
            board.remove(end);
        }
    }

    private void checkSameTeam(Piece startPiece, Piece endPiece) {
        if (startPiece.isSameTeamPiece(endPiece)) {
            throw new IllegalArgumentException("이동 불가능 합니다.3");
        }
    }

    private void checkRoute(Point end, Point candidatePoint) {
        if (board.containsKey(candidatePoint) && !candidatePoint.equals(end)) {
            throw new IllegalArgumentException("이동 불가능 합니다.2");
        }
    }

    private void checkMove(List<Point> candidatePoints) {
        if (candidatePoints.size() == 0) throw new IllegalArgumentException("이동 불가능 합니다.1");
    }

    private void checkBlank(Point start) {
        if (!board.containsKey(start)) throw new IllegalArgumentException("말이 없습니다.");
    }

    public Map<Point, Piece> getBoard() {
        return board;
    }

    public double calculateScore(Team team) {
        return board.values().stream()
                .filter(d -> d.isSameTeam(team))
                .mapToDouble(Piece::getScore)
                .sum();
    }

    public boolean isKingAlive() {
        List<Piece> pieces = board.values().stream()
                .filter(d -> d instanceof King)
                .collect(Collectors.toList());
        return pieces.size() == 2;
    }
}
