package chessgame.domain;

import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;

import java.util.Collections;
import java.util.Map;

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
        if (piece instanceof Pawn) {
            executePawnMove(source, target, piece, hasTarget);
            return;
        }
        executeMove(source, target, piece);
    }

    private void executePawnMove(Point source, Point target, Piece piece, boolean hasTarget) {
        if (piece.isMovable(source, target)) {
            checkPawnMove(piece, hasTarget);
            followPieceRoute(source, target, piece);
            return;
        }
        checkPawnAttack(source, target, piece, hasTarget);
    }

    private void executeMove(Point source, Point target, Piece piece) {
        if (piece.isMovable(source, target)) {
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

    private boolean checkRoute(Point source, Point target) {
        Point point = source;
        int eachFileMove = target.eachFileMove(source);
        int eachRankMove = target.eachRankMove(source);
        int distance = target.calculateDistance(source);
        while (distance-- > 0) {
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

    private void checkPawnAttack(Point source, Point target, Piece piece, Boolean hasTarget) {
        if (((Pawn) piece).isAttack(source, target) && hasTarget) {
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
