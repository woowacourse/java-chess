package chessgame;

public class Queen implements Pieces{
    private static final String ORIGINAL_NAME = "q";

    private final String name;

    private Queen(String name) {
        this.name = name;
    }

    public static Queen from(Team team) {
        return new Queen(team.calculate(ORIGINAL_NAME));
    }

    @Override
    public String toString() {
        return name;
    }
}
