package chess.dto;

import chess.domain.ChessGame;
import chess.domain.Team;

public final class StatusDTO {
    private final String turn;
    private final Double blackScore;
    private final Double whiteScore;
    private final boolean ends;
    private final String winner;
    private final String loser;

    public StatusDTO(ChessGame chessGame, UsersDTO users) {
        this.turn = chessGame.turn().name();
        this.blackScore = chessGame.scoreByTeam(Team.BLACK);
        this.whiteScore = chessGame.scoreByTeam(Team.WHITE);
        this.ends = chessGame.isPlaying();
        this.winner = winner(chessGame.winner(), users);
        this.loser = loser(users);
    }

    private String winner(Team winner, UsersDTO users) {
        if (Team.WHITE.equals(winner)) {
            return users.getWhiteUser();
        }
        if (Team.BLACK.equals(winner)) {
            return users.getBlackUser();
        }
        return "NONE";
    }

    private String loser(UsersDTO users) {
        if (users.getBlackUser().equals(winner)) {
            return users.getWhiteUser();
        }
        if (users.getWhiteUser().equals(winner)) {
            return users.getBlackUser();
        }
        return "NONE";
    }
}
