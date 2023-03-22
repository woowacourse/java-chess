package chessgame.domain;

import java.util.Collections;
import java.util.Map;

import chessgame.domain.piece.Knight;
import chessgame.domain.piece.Pawn;
import chessgame.domain.piece.Piece;
import chessgame.domain.point.Point;
import chessgame.util.ChessBoardFactory;

public class Board {
    private final Map<Point, Piece> board;

    public Board() {
        this.board = ChessBoardFactory.create();
    }

    public Map<Point, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public boolean move(Point source, Point target, Team turn) {
        Piece piece = checkSource(source, target, turn);
        boolean hasTarget = checkTarget(target, turn);
        if (piece instanceof Pawn) {
            return isPawnMove(source, target, (Pawn)piece, hasTarget);
        }
        if (piece instanceof Knight && piece.isMovable(source, target)) {
            movePiece(source, target, piece);
            return true;
        }
        return followPieceRoute(source, target, piece);
    }

    public Piece checkSource(Point source, Point target, Team turn) {
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

    public boolean checkTarget(Point target, Team turn) {
        if (board.containsKey(target) && turn == board.get(target).team()) {
            throw new IllegalArgumentException("자기팀 기물을 잡을 수 없습니다.");
        }
        return board.containsKey(target);
    }

    private boolean isPawnMove(Point source, Point target, Pawn piece, boolean hasTarget) {
        if (piece.isAttack(source, target) && hasTarget) {
            movePiece(source, target, piece);
            return true;
        }
        if (piece.isMovable(source, target) && !hasTarget) {
            movePiece(source, target, piece);
            return true;
        }
        throw new IllegalArgumentException(piece.failMoveMsg());
    }

    private void movePiece(Point source, Point target, Piece piece) {
        board.put(target, piece);
        board.remove(source);
    }

    private boolean followPieceRoute(Point source, Point target, Piece piece) {
        if (!piece.isMovable(source, target)) {
            throw new IllegalArgumentException(piece.failMoveMsg());
        }
        if (checkRoute(source, target)) {
            movePiece(source, target, piece);
            return true;
        }
        throw new IllegalArgumentException(piece.failMoveMsg());
    }

    public boolean checkRoute(Point source, Point target) {
        int distance = source.maxDistance(target);
        int fileMove = target.fileMove(source, distance);
        int rankMove = target.rankMove(source, distance);

        Point point = source;
        for (int i = 0; i < distance - 1; i++) {
            point = point.move(fileMove, rankMove);
            if (board.containsKey(point)) {
                throw new IllegalArgumentException("기물을 건너 뛸 수 없습니다.");
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }
}
