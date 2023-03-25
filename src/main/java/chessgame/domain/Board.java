package chessgame.domain;

import chessgame.domain.piece.Piece;
import chessgame.domain.point.File;
import chessgame.domain.point.Point;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {
    private static final double SAME_PAWN_CALCULATE_RATIO = 0.5;
    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(Point source, Point target, Team turn) {
        checkSamePoint(source, target);
        checkSource(source, turn);
        boolean hasTarget = checkTarget(target, turn);
        executeMove(source, target, hasTarget);
    }

    private void executeMove(Point source, Point target, boolean hasTarget) {
        Piece piece = board.get(source);
        if (piece.isMovable(source, target, hasTarget)) {
            followPieceRoute(source, target, piece);
            return;
        }
        throw new IllegalArgumentException("움직일 수 없습니다.");
    }

    private void checkSamePoint(Point source, Point target) {
        if (source == target) {
            throw new IllegalArgumentException("소스와 타켓 좌표가 달라야합니다.");
        }
    }

    private void checkSource(Point source, Team turn) {
        if (isBlockedPiece(source) && turn != board.get(source).team()) {
            throw new IllegalArgumentException(turn.color() + "팀 기물만 움직일 수 있습니다.");
        }
        if (!isBlockedPiece(source)) {
            throw new IllegalArgumentException("해당 좌표에 기물이 존재하지 않습니다.");
        }
    }

    private boolean checkTarget(Point target, Team turn) {
        if (isBlockedPiece(target) && turn == board.get(target).team()) {
            throw new IllegalArgumentException("자기팀 기물을 잡을 수 없습니다.");
        }
        return isBlockedPiece(target);
    }

    private void followPieceRoute(Point source, Point target, Piece piece) {
        if (piece.isKnight()) {
            movePiece(source, target, piece);
            return;
        }
        if (canMoveRoute(source, target)) {
            movePiece(source, target, piece);
            return;
        }
        throw new IllegalArgumentException("불가능한 움직임 입니다.");
    }

    private void movePiece(Point source, Point target, Piece piece) {
        board.put(target, piece);
        board.remove(source);
    }

    private boolean canMoveRoute(Point source, Point target) {
        Point point = source;
        int eachFileMove = target.eachFileMove(source);
        int eachRankMove = target.eachRankMove(source);
        int distance = target.calculateDistance(source);
        while (distance-- > 1) {
            point = point.move(eachFileMove, eachRankMove);
            if (isBlockedPiece(point)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBlockedPiece(Point point) {
        return board.containsKey(point);
    }

    public boolean isExistKing(Team team) {
        return board.values()
                .stream()
                .filter(Piece::isKing)
                .anyMatch(s -> s.team() == team);
    }

    public double calculateScore(Team team) {
        return (board.values()
                .stream()
                .filter(s -> s.team().equals(team))
                .mapToDouble(Piece::getScore).sum() - calculateSameFilePawn(team));
    }

    private double calculateSameFilePawn(Team team) {
        double sameFilePawn = 0;
        for (File file : File.values()) {
            double countPawn = calculateSameTeamPawn(team).keySet()
                    .stream()
                    .filter(point -> point.isVerticalFile(file))
                    .count();
            sameFilePawn = countSameFilePawn(sameFilePawn, countPawn);
        }
        return sameFilePawn * SAME_PAWN_CALCULATE_RATIO;
    }

    private static double countSameFilePawn(double sameFilePawn, double countPawn) {
        if (countPawn >= 2) {
            sameFilePawn += countPawn;
        }
        return sameFilePawn;
    }

    private Map<Point, Piece> calculateSameTeamPawn(Team team) {
        return board.keySet()
                .stream()
                .filter(point -> board.get(point).isPawn() && board.get(point).team() == team)
                .collect(Collectors.toMap(point -> point, board::get));
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
