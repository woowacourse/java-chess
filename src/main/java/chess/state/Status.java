package chess.state;

import chess.board.Board;
import chess.Player;

import java.util.HashMap;
import java.util.Map;

import static chess.Player.BLACK;
import static chess.Player.WHITE;

public class Status extends Finished {

    public Status(Board board) {
        super(board);
    }

    @Override
    public Map<Player, Double> calculateScore() {
        Map<Player, Double> scores = new HashMap<>();
        scores.put(WHITE, board.calculateScore(WHITE));
        scores.put(BLACK, board.calculateScore(BLACK));
        return scores;
    }

    @Override
    public State proceed(String command) {
        throw new IllegalArgumentException("[ERROR] 올바른 명령을 입력해주세요.");
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
