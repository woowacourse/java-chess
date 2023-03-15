package techcourse.fp.chess.domain;

//public class Bishop extends Piece {
//
//    public Bishop(final Side side) {
//        super(side);
//    }
//
//    @Override
//    public boolean isMovable(final Position sourcePosition, final Position targetPosition) {
//        if (sourcePosition.equals(targetPosition)) {
//            return false;
//        }
//        return calculateFileDifference(sourcePosition, targetPosition) ==
//                calculateRankDifference(sourcePosition, targetPosition);
//    }
//
//    private int calculateFileDifference(final Position sourcePosition, final Position targetPosition) {
//        return Math.abs(sourcePosition.getFileOrder() - targetPosition.getFileOrder());
//    }
//
//    private int calculateRankDifference(final Position sourcePosition, final Position targetPosition) {
//        return Math.abs(sourcePosition.getRankOrder() - targetPosition.getRankOrder());
//    }
//}
