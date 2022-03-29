package chess.controller;

import static chess.model.Team.BLACK;
import static chess.model.Team.WHITE;

import chess.model.Team;
import chess.model.board.Board;
import chess.model.state.Sleep;
import chess.model.state.State;
import java.util.LinkedHashMap;
import java.util.Map;

public class Status implements Command {

    @Override
    public State executeTo(Board board) {
        Map<Team, Double> scores = new LinkedHashMap<>();
        scores.put(WHITE, board.calculateScore(WHITE));
        scores.put(BLACK, board.calculateScore(BLACK));
        return new Sleep(board, scores);
    }

    @Override
    public boolean isStart() {
        return false;
    }
}
