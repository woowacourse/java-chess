package chess.domain;

public enum Team {

    WHITE("백팀"),
    BLACK("흑팀"),
    NEUTRALITY("중립");

    public boolean isBlack() {
        return this == Team.BLACK;
    }

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isNeutrality(Team neutrality) {
        return this == NEUTRALITY;
    }
}
