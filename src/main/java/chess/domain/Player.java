package chess.domain;

public class Player {

    private final Name name;
    private final Team team;

    public Player(final Name name, final Team team) {
        this.name = name;
        this.team = team;
    }
}
