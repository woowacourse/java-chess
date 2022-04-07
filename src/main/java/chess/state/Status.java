package chess.state;

import chess.chessboard.Board;
import chess.game.Player;
import chess.game.Score;

import java.util.HashMap;

public final class Status extends Finished {

    public Status(final Board board) {
        super(board);
    }

    @Override
    public State proceed(final String command) {
        throw new IllegalArgumentException("[ERROR] 올바른 명령을 입력해주세요.");
    }

    @Override
    public boolean isStatus() {
        return true;
    }

    public HashMap<Player, Double> calculateScore() {
        final Score score = new Score();
        score.calculateScore(board);
        return score.getScores();
    }

}
