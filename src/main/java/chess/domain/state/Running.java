package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.MatchResult;
import chess.domain.board.Score;
import chess.domain.board.ScoreResult;
import chess.domain.piece.Color;

import java.util.HashMap;
import java.util.Map;

abstract class Running extends Ready {

    Running(Board board) {
        super(board);
    }

    public abstract State movePiece(Position src, Position dest);

    @Override
    public final ScoreResult getScore() {
        double whiteScore = board.calculateScore(Color.WHITE);
        double blackScore = board.calculateScore(Color.BLACK);

        Map<Color, Score> scoreByColor = new HashMap<>();
        scoreByColor.put(Color.WHITE, new Score(whiteScore));
        scoreByColor.put(Color.BLACK, new Score(blackScore));

        return new ScoreResult(scoreByColor);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
