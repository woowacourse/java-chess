package chess;

import chess.domain.piece.Team;

public enum GameStatus {

    PLAY,
    END,
    BLACK_WIN,
    WHITE_WIN;

    public static GameStatus whenWin(Team team) {
        if (team.isBlack()) {
            return BLACK_WIN;
        }
        return WHITE_WIN;
    }
}
