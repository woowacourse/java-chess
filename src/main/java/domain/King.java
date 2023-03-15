package domain;

public class King implements NewPieceType {

    @Override
    public boolean isReachableByRule(final Position startPosition, final Position endPosition) {
        if (startPosition.add(0, -1).equals(endPosition) ||
                startPosition.add(0, 1).equals(endPosition) ||
                startPosition.add(1, 0).equals(endPosition) ||
                startPosition.add(-1, 0).equals(endPosition) ||
                startPosition.add(1, 1).equals(endPosition) ||
                startPosition.add(-1, 1).equals(endPosition) ||
                startPosition.add(-1, -1).equals(endPosition) ||
                startPosition.add(1, -1).equals(endPosition)
        ) {
            return true;
        }
        return false;
    }
}
