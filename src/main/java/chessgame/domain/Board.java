package chessgame.domain;

import java.util.Collections;
import java.util.Map;

import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;

public class Board {
    private final Map<Point, Piece> board;

    public Board(Map<Point, Piece> board) {
        this.board = board;
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public void move(Point source, Point target, Team turn) {
        checkSamePoint(source, target);
        Piece piece = board.get(source);
        checkSource(source, turn);
        boolean hasTarget = checkTarget(target, turn);
        if (piece.isMovable(source, target)) {
            if (piece instanceof Pawn && hasTarget) {
                throw new IllegalArgumentException("폰은 직진으로 적을 잡을수 없습니다.");
            }
            if (!(piece instanceof Knight) && checkRoute(source, target)) {
                board.put(target, piece);
                board.remove(source);
                return;
            }
            if (piece instanceof Knight) {
                board.put(target, piece);
                board.remove(source);
                return;
            }
        } else if (piece instanceof Pawn && hasTarget) {
            if (((Pawn)piece).isAttack(source, target)) {
                board.put(target, piece);
                board.remove(source);
                return;
            }
        }
        throw new IllegalArgumentException("불가능한 움직임 입니다.");
    }

    private void checkSamePoint(Point source, Point target) {
        if (source == target) {
            throw new IllegalArgumentException("소스와 타켓 좌표가 달라야합니다.");
        }
    }

    public void checkSource(Point source, Team turn) {
        if (!board.containsKey(source) && turn != board.get(source).team()) {
            throw new IllegalArgumentException(turn.color() + "팀 기물만 움직일 수 있습니다.");
        }

        if (!board.containsKey(source)) {
            throw new IllegalArgumentException("해당 좌표에 기물이 존재하지 않습니다.");
        }

    }

    public boolean checkTarget(Point target, Team turn) {
        if (board.containsKey(target) && turn == board.get(target).team()) {
            throw new IllegalArgumentException("자기팀 기물을 잡을 수 없습니다.");
        }
        return board.containsKey(target);
    }

    public boolean checkRoute(Point source, Point target) {
        Point point = source;
        int fileDifference = target.makeFileDifference(source);
        int rankDifference = target.makeRankDifference(source);

        int distance = Math.max(Math.abs(fileDifference), Math.abs(rankDifference));

        int fileMove = fileDifference / distance;
        int rankMove = rankDifference / distance;

        for (int i = 0; i < distance - 1; i++) {
            point = point.move(fileMove, rankMove);
            if (board.containsKey(point)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
