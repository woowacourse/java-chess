package chess.domain;

public abstract class Piece {

    private String team;
    private final String name;

    public Piece(String team, String name) {
        this.team = team;
        this.name = name;
    }

    public boolean isBlackTeam() {
        return team.equals("Black");
    }

    public String getName() {
        return name;
    }
}
