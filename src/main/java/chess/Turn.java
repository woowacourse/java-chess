package chess;

public class Turn {
    private final Team team;

    public Turn(Team team) {
        this.team = team;
    }

    public static Turn init(){
        return new Turn(Team.WHITE);
    }

    public boolean isCurrentTeam(Team team) {
        return this.team.equals(team);
    }

    public Turn change() {
        if(team.equals(Team.BLACK)){
            return new Turn(Team.WHITE);
        }
        return new Turn(Team.BLACK);
    }
}
