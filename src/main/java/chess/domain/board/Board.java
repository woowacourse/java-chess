package chess.domain.board;

import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Board {
    Map<Point, Optional<Piece>> points;

    public Board(Map<Point, Optional<Piece>> points) {
        this.points = new HashMap<>(points);
    }

    public Optional<Piece> get(Point point) {
        return points.get(point);
    }

//    public void tryMove(Point prev, Point next) {
//        Optional<Piece> piece = points.get(prev);
//        if (piece.isPresent()) {
//            if(piece.get().isMovable(prev, next) && isPieceInWay(prev,next)){
//                move(prev,next);
//            }
//        }
//    }

    public void move(Point prev, Point next) {
        points.put(next, points.get(prev));
        points.put(prev, Optional.empty());
    }
//    private boolean isPieceInWay(Point prev, Point next){
//        if()
//    }

}
