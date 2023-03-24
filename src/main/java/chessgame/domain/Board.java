package chessgame.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;
import chessgame.domain.point.Points;

public class Board {
    private final Map<Point, Piece> board;
    private final Map<Team, Double> score;

    public Board() {
        this.board = GameBoardFactory.create();
        this.score = new HashMap<>();
    }

    public void move(Points points, Team turn) {
        Piece piece = checkSourcePiece(points.source(), points.target(), turn);
        boolean hasTarget = checkHavingTarget(points.target(), turn);
        boolean isBlocked = checkRouteBlocked(points);

        if (!piece.isMovable(points, isBlocked, hasTarget)) {
            throw new IllegalArgumentException(piece.failMoveMsg());
        }
        movePiece(points, piece);
    }

    private Piece checkSourcePiece(Point source, Point target, Team turn) {
        if (source == target) {
            throw new IllegalArgumentException("소스와 타켓 좌표가 달라야합니다.");
        }
        if (board.containsKey(source) && turn != board.get(source).team()) {
            throw new IllegalArgumentException(turn.color() + "팀 기물만 움직일 수 있습니다.");
        }
        if (!board.containsKey(source)) {
            throw new IllegalArgumentException("해당 좌표에 기물이 존재하지 않습니다.");
        }
        return board.get(source);
    }

    private boolean checkHavingTarget(Point target, Team turn) {
        if (board.containsKey(target) && turn == board.get(target).team()) {
            throw new IllegalArgumentException("자기팀 기물을 잡을 수 없습니다.");
        }
        return board.containsKey(target);
    }

    private boolean checkRouteBlocked(Points points) {
        int distance = points.maxDistance();
        int fileMove = points.fileMove(distance);
        int rankMove = points.rankMove(distance);

        return isRouteBlocked(points.source(), distance, fileMove, rankMove);
    }

    private boolean isRouteBlocked(Point source, int distance, int fileMove, int rankMove) {
        Point point = source;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < distance - 1; i++) {
            point = point.move(fileMove, rankMove);
            points.add(point);
        }
        return points.stream()
            .anyMatch(board::containsKey);
    }

    private void movePiece(Points points, Piece piece) {
        board.remove(points.source());
        board.put(points.target(), piece);
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void calculateScore() {
        for (Team team : Team.values()) {
            score.put(team, board.values().stream().mapToDouble(piece -> piece.score(team)).sum());
        }
    }

    public Map<Team, Double> score() {
        return Collections.unmodifiableMap(score);
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
