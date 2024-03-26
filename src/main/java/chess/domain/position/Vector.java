package chess.domain.position;

public enum Vector {
    UP_UP_RIGHT(1, 2),
    UP_UP_LEFT(-1, 2),
    DOWN_DOWN_RIGHT(1, -2),
    DOWN_DOWN_LEFT(-1, -2),
    RIGHT_RIGHT_UP(2, 1),
    RIGHT_RIGHT_DOWN(2, -1),
    LEFT_LEFT_UP(-2, 1),
    LEFT_LEFT_DOWN(-2, -1);
    private final int fileVector;
    private final int rankVector;

    Vector(int fileVector, int rankVector) {
        this.fileVector = fileVector;
        this.rankVector = rankVector;
    }

    public int getFileVector() {
        return fileVector;
    }

    public int getRankVector() {
        return rankVector;
    }
}
