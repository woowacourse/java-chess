package chess.domain;

public class ChessGame {
    private static Board board = new Board();
    private static Team team = Team.WHITE;

    public void play(Position start, Position end) {
        board.move(start, end, team);
        team = Team.switchTeam(team);
    }

    public static Team getTeam() {
        return team;
    }

    public double getStatus(Team team) {
        return board.getScore(team);
    }

    public boolean isGameEnd() {
        return board.isKingDead();
    }
}
