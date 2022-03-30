package web.dao;

import chess.Score;
import chess.piece.Color;

public class ScoreDao {

    private Score whiteScore;
    private Score blackScore;

    public Score findScoreByColor(Color color) {
        if (color == Color.WHITE) {
            return whiteScore;
        }
        return blackScore;
    }

    public void saveScoreByColor(Score score, Color color) {
        if (color == Color.WHITE) {
            this.whiteScore = score;
        }
        if (color == Color.BLACK) {
            this.blackScore = score;
        }
    }

    public void updateScoreByColor(Score score, Color color) {
        if (color == Color.WHITE) {
            this.whiteScore = score;
        }
        if (color == Color.BLACK) {
            this.blackScore = score;
        }
    }

    public void deleteAll() {
        this.whiteScore = null;
        this.blackScore = null;
    }
}
