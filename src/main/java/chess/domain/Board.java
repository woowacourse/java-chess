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

        Square originSquare = map.get(origin);
        Square targetSquare = map.get(target);

        if (originSquare.isSameTeam(targetSquare)) {
            return false;
        }
//        else if (targetSquare.isEmpty()) {
        return isMovable(originSquare, targetSquare);
//        }
//        return isAttackAble(originSquare, targetSquare);
    }

//    private boolean isAttackAble(final Square origin, final Square target) {
//        if (origin.isAttackAble(target)) {
//
//        }
//    }

    public boolean isMovable(final Square origin, final Square target) {
        if (!origin.isMovable(target)) {
            return false;
        }
        origin.movePiece(target);
        return true;
    }

    private boolean hasObstacle(final Position origin, final Position target) {
        for (Position route : origin.findRoutes(target)) {
            if (!map.get(route).isEmpty()) {
                return true;
            }
        }
        return false;
    }

//    public boolean isMovable(Square origin, Square target) {
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
