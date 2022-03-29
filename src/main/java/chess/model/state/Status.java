package chess.model.state;

import chess.model.board.Board;
import chess.model.Team;

import java.util.HashMap;
import java.util.Map;

import static chess.model.Team.BLACK;
import static chess.model.Team.WHITE;

public class Status extends Finished {

    public Status(Board board) {
        super(board);
    }

    @Override
    public Map<Team, Double> calculateScore() {
        Map<Team, Double> scores = new HashMap<>();
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
