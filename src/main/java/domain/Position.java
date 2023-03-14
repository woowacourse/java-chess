package domain;

public class Position {
    private final Rank rank;
    private final Column column;

    public Position(Rank rank, Column column) {
        this.rank = rank;
        this.column = column;
    }


    @Override
    public String toString() {
        return column.name() + rank.name();
    }
}
