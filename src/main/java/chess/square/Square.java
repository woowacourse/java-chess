package chess.square;

public class Square {

    private static Square instance;

    private final Rank rank;
    private final File file;

    private Square(Rank rank, File file) {
        this.rank = rank;
        this.file = file;
    }

    public static Square of(Rank rank, File file) {
        if (instance == null) {
            instance = new Square(rank, file);
        }
        return instance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Square square = (Square) o;

        if (rank != square.rank) {
            return false;
        }
        return file == square.file;
    }

    @Override
    public int hashCode() {
        int result = rank != null ? rank.hashCode() : 0;
        result = 31 * result + (file != null ? file.hashCode() : 0);
        return result;
    }
}
