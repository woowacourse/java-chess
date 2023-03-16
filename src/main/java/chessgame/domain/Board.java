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

    public void move(Point source, Point target) {
        Piece piece = board.get(source);
        checkSource(source);
        boolean hasTarget =checkTarget(target);
        if (piece.isMovable(source, target)) {
            if(piece instanceof Pawn && hasTarget){
                return;
            }
            if (!(piece instanceof Knight) && checkRoute(source, target)) {
                board.put(target, piece);
                board.remove(source);
            }
        } else if(piece instanceof Pawn && hasTarget){
            if(((Pawn)piece).isAttack(source, target)){
                board.put(target, piece);
                board.remove(source);
            }
        }
    }

    @Override
    public String toString() {
        return "Board{" + "board=" + board + '}';
    }

    // TODO: 우리팀인지 확인하는 로직 추가하기
    public boolean checkSource(Point source) {
        return board.containsKey(source);
    }

    public boolean checkTarget(Point target) {
        return board.containsKey(target);
    }

    public boolean checkRoute(Point source, Point target) {
        Point point = source;
        int fileDifference = source.makeFileDifference(target) * -1;
        int rankDifference = source.makeRankDifference(target) * -1;

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
}
