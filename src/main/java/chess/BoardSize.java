package chess;

public enum BoardSize {
    BOUND(0, 7);

    private final int minIndex;
    private final int maxIndex;

    BoardSize(int minIndex, int maxIndex) {
        this.minIndex = minIndex;
        this.maxIndex = maxIndex;
    }

    public int getMinIndex() {
        return minIndex;
    }

    public int getMaxIndex() {
        return maxIndex;
    }
}
