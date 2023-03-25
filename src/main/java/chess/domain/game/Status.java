package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;

public class Status {

    private final Score whiteScore;
    private final Score blackScore;

    private Status(Score whiteScore, Score blackScore) {
        this.whiteScore = whiteScore;
        this.blackScore = blackScore;
    }

    public static Status from(Map<Position, Piece> pieces) {
        Score whiteScore = Score.calculate(pieces, Color.WHITE);
        Score blackScore = Score.calculate(pieces, Color.BLACK);
        return new Status(whiteScore, blackScore);
    }

    public Score getScore(Color color) {
        if (color == Color.BLACK) {
            return blackScore;
        }
        return whiteScore;
    }

    public Color getWinner() {
        return Score.judgeWinner(blackScore, whiteScore);
    }
}
