package chess.domain;

import chess.domain.board.Board;
import chess.domain.position.Position;

public class ChessGame {
    private Board board = new Board();
    private Team team = Team.WHITE;

    public void play(Position source, Position target) {
        if (board.movable(source, target, team)) {
            board.move(source, target);
        }
        team = Team.switchTeam(team);
    }

    public Team getTeam() {
        return team;
    }

    public Board getBoard() {
        return board;
    }

    public double getStatus(Team team) {
        return board.getScore(team);
    }

    public boolean isGameEnd() {
        return board.isKingDead();
    }
}
