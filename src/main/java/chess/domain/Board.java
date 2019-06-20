package chess.domain;

import java.util.Map;

public class Board {
    private final Map<Position, Square> map;

    public Board(final Map<Position, Square> map) {
        this.map = map;
    }

    public boolean action(final Position origin, final Position target) {
        if (hasObstacle(origin, target)) {
            return false;
        }
        //장애물 있으면 못움직임

        Square originSquare = map.get(origin);
        Square targetSquare = map.get(target);

        if (targetSquare.isEmpty()) {
            return isValidMove(originSquare, targetSquare);
        }
        return isValidAttack(originSquare, targetSquare);
    }

    public boolean isValidAttack(final Square origin, final Square target) {
        if (!origin.isValidAttack(target)) {
            return false;
        }
        origin.attackPiece(target);
        return true;
    }
    // 움직일 수 있니 물어보고
    //변환하고
    //true반환
    public boolean isValidMove(final Square origin, final Square target) {
        if (!origin.isValidMove(target)) {
            return false;
        }
        origin.movePiece(target);
        return true;
    }

    private boolean hasObstacle(final Position origin, final Position target) {
        for (Position route : origin.findRoutes(target)) {
            final boolean empty = map.get(route).isEmpty();
            if (!empty) {
                return true;
            }
        }
        return false;
    }

//    public boolean isValidMove(Square origin, Square target) {
//        return getMovableLiss(origin).contains(target.getPosition());
//    }
//
//    public List<Position> getMovableLiss(Square origin) {
//        List<Position> positions = new ArrayList<>();
//
//        for (Square target : map.values()) {
//            if (origin.move(target) && !map.containsValue(origin)) {
//                positions.add(target.getPosition());
//            }
//        }
//        return positions;
//    }
}
