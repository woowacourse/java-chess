package chess.dto;

import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;

public class GameStatusDto {

    private static final String BLACK = "b";
    private static final String WHITE = "w";
    private static final String NONE = "n";

    private final String isOngoing;
    private final String winner;

    public GameStatusDto(ChessGame chessGame) {
        isOngoing = Boolean.toString(chessGame.isOngoing());
        winner = winnerByGameStatus(chessGame);
    }

    private static String winnerByGameStatus(ChessGame chessGame) {
        if (!chessGame.isOngoing()) {
            return encodedWinner(chessGame.winner());
        }
        return NONE;
    }

    private static String encodedWinner(Team team) {
        if (team.isBlack()) {
            return BLACK;
        }
        if (team.isWhite()) {
            return WHITE;
        }
        return NONE;
    }

    public String getIsOngoing() {
        return isOngoing;
    }

    public String getWinner() {
        return winner;
    }
}
