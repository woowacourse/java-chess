package chess.domain;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class ChessGame {
    private static Board board = new Board();
    private static Team team = Team.WHITE;

    public void play(Position start, Position end) {
        if (board.movable(start, end, team)) {
            board.move(start, end);
        }
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
