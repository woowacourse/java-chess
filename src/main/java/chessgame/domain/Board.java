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
        Piece piece = board.get(source);
        checkSamePoint(source, target);
        checkSource(source, turn);
        boolean hasTarget = checkTarget(target, turn);

        if (piece.isMovable(source, target)) {
            checkPawnMove(piece, hasTarget);
            followPieceRoute(source, target, piece);
        } else if (piece instanceof Pawn) {
            checkPawnAttack(source, target, piece, hasTarget);
        }
    }

    private void checkSamePoint(Point source, Point target) {
        if (source == target) {
            throw new IllegalArgumentException("소스와 타켓 좌표가 달라야합니다.");
        }
    }

    public void checkSource(Point source, Team turn) {
        if (board.containsKey(source) && turn != board.get(source).team()) {
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

    private void checkPawnMove(Piece piece, boolean hasTarget) {
        if (piece instanceof Pawn && hasTarget) {
            throw new IllegalArgumentException("폰은 직진으로 적을 잡을수 없습니다.");
        }
    }

    private void followPieceRoute(Point source, Point target, Piece piece) {
        if (piece instanceof Knight) {
            movePiece(source, target, piece);
            return;
        }
        if (checkRoute(source, target)) {
            movePiece(source, target, piece);
            return;
        }
        throw new IllegalArgumentException("불가능한 움직임 입니다.");
    }

    private void movePiece(Point source, Point target, Piece piece) {
        board.put(target, piece);
        board.remove(source);
    }

    public boolean checkRoute(Point source, Point target) {
        int fileDifference = target.fileDistance(source);
        int rankDifference = target.rankDistance(source);

        int distance = Math.max(Math.abs(fileDifference), Math.abs(rankDifference));

        int fileMove = fileDifference / distance;
        int rankMove = rankDifference / distance;

        Point point = source;
        for (int i = 0; i < distance - 1; i++) {
            point = point.move(fileMove, rankMove);
            if (board.containsKey(point)) {
                return false;
            }
        }
        return true;
    }

    private void checkPawnAttack(Point source, Point target, Piece piece, Boolean hasTarget) {
        if (((Pawn)piece).isAttack(source, target) && hasTarget) {
            movePiece(source, target, piece);
            return;
        }
        throw new IllegalArgumentException("불가능한 움직임 입니다.");
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
