package chess.domain;

public class PositionInformation {

    private final Position position;
    private final TeamColor teamColor;

    public PositionInformation(Position position, TeamColor teamColor) {
        this.position = position;
        this.teamColor = teamColor;
    }

    public Position position() {
        return position;
    }

    public TeamColor teamColor() {
        return teamColor;
    }

    public boolean isSameTeam(TeamColor teamColor) {
        return this.teamColor.equals(teamColor);
    }

    public boolean isEnemyTeam(TeamColor teamColor) {
        return this.teamColor.isEnemy(teamColor);
    }
}
