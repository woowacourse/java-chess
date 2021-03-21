package chess.domain.player.score;

import chess.domain.player.Player;

public class Scores {
    private final Score blackPlayerScore;
    private final Score whitePlayerScore;

    public Scores(Player blackPlayer, Player whitePlayer) {
        blackPlayerScore = blackPlayer.score();
        whitePlayerScore = whitePlayer.score();
    }

    public Score blackPlayerScore() {
        return blackPlayerScore;
    }

    public Score whitePlayerScore() {
        return whitePlayerScore;
    }
}
