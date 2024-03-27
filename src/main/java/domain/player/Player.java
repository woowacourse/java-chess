package domain.player;

public class Player {

    private final PlayerName name;

    public Player(final PlayerName name) {
        this.name = name;
    }

    public String getName() {
        return name.getName();
    }
}
