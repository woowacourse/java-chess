package chessgame;

public class King {
    private static final String ORIGINAL_NAME = "k";

    private final String name;

    private King(String name) {
        this.name = name;
    }

    public static King from(Team team) {
        return new King(team.calculate(ORIGINAL_NAME));
    }

    @Override
    public String toString() {
        return name;
    }
}
