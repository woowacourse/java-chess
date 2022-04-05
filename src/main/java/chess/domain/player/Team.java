package chess.domain.player;

public enum Team {

    WHITE("WHITE"),
    BLACK("BLACK");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public boolean isMyTeam(String team) {
        return this.name.equals(team);
    }

    public String getName() {
        return name;
    }
}
