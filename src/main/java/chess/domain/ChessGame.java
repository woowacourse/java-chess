package chess.domain;

import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private Team team;

    public ChessGame() {
        board = new Board();
        team = Team.WHITE;
    }

    public Map<Row, Rank> getBoardStatus() {
        return board.getBoard();
    }

    public double getWhiteTeamScore() {
        return Score.calculateScore(board.getBoard(), Team.WHITE).getTotalScore();
    }

    public double getBlackTeamScore() {
        return Score.calculateScore(board.getBoard(), Team.BLACK).getTotalScore();
    }

    public boolean move(String source, String destination) {
        boolean isKingDead = board.movePiece(Position.from(source), Position.from(destination), team);
        team = Team.switchTeam(team);
        return isKingDead;
    }

    public Team getWinnerTeam() {
        return team;
    }
}
