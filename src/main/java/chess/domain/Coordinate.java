package chess.domain;

class Coordinate {

    private final Rank rank;
    private final File file;

    public Coordinate(int rankValue, char fileValue) {
        this.rank = new Rank(rankValue);
        this.file = new File(fileValue);
    }
}
