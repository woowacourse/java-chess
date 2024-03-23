//package domain.piece;
//
//import domain.piece.info.Color;
//import domain.piece.info.Direction;
//import domain.piece.info.Type;
//import domain.piece.movement.Continuous;
//import domain.piece.movement.Movement;
//import java.util.List;
//
//public class Empty extends Piece {
//    public static final Piece INSTANCE = new Empty(Color.NONE, Type.NONE, new Continuous());
//
//    public static final String EMPTY_SQUARES = "비어 있는 칸입니다.";
//
//    public Empty(final Color color, final Type type) {
//        super(color, type);
//    }
//
//    @Override
//    public List<Direction> movableDirections() {
//        throw new UnsupportedOperationException(EMPTY_SQUARES);
//    }
//}
