//package domain.piece;
//
//import domain.piece.info.Color;
//import domain.piece.info.Direction;
//import domain.piece.info.Type;
//import domain.piece.movement.Movement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class Bishop extends Piece {
//
//    public Bishop(final Color color, final Type type) {
//        super(color, type);
//    }
//
//    @Override
//    public List<Direction> movableDirections() {
//        return new ArrayList<>(List.of(
//                Direction.UP_LEFT,
//                Direction.UP_RIGHT,
//                Direction.DOWN_LEFT,
//                Direction.DOWN_RIGHT
//        ));
//    }
//}
