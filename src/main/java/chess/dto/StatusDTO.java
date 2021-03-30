package chess.dto;

import chess.domain.ChessGame;
import chess.domain.Team;

public class StatusDTO {
    private final String turn;
    private final Double blackScore;
    private final Double whiteScore;
    private final boolean ends;
    private final String winner;

    public StatusDTO(String turn, ChessGame chessGame) {
        this.turn = turn;
        this.blackScore = chessGame.scoreByTeam(Team.BLACK);
        this.whiteScore = chessGame.scoreByTeam(Team.WHITE);
        this.ends = chessGame.isPlaying();
        this.winner = winner(chessGame.winner());
    }

    private String winner(Team winner) {
        if (winner == null) {
            return "NONE";
        }
        return winner.name();
    }
}
