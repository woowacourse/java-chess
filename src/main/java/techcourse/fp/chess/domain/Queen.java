package techcourse.fp.chess.domain;
//
//public class Queen extends Piece {
//
//    public Queen(final Side side) {
//        super(side);
//    }
//
//    @Override
//    public boolean isMovable(final Position sourcePosition, final Position targetPosition) {
//        if (sourcePosition.equals(targetPosition)) {
//            return false;
//        }
//
//        return isMovableHorizontalAndVertical(sourcePosition, targetPosition) || isMovableDiagonal(sourcePosition, targetPosition);
//    }
//
//    private boolean isMovableHorizontalAndVertical(final Position sourcePosition, final Position targetPosition) {
//        return isSameFileOrder(sourcePosition, targetPosition) || isSameRankOrder(sourcePosition, targetPosition);
//    }
//
//    private boolean isSameRankOrder(final Position sourcePosition, final Position targetPosition) {
//        return sourcePosition.getRankOrder() == targetPosition.getRankOrder();
//    }
//
//    private boolean isSameFileOrder(final Position sourcePosition, final Position targetPosition) {
//        return sourcePosition.getFileOrder() == targetPosition.getFileOrder();
//    }
//
//    private boolean isMovableDiagonal(final Position sourcePosition, final Position targetPosition) {
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
