package chess.model.state;

import chess.model.command.Command;
import chess.model.Team;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Collections;
import java.util.Map;

public class Sleep implements State {

    private final Board board;
    private final Map<Team, Double> scores;

    public Sleep(Board board, Map<Team, Double> scores) {
        this.board = board;
        this.scores = scores;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isSleep() {
        return true;
    }

    @Override
    public Map<Team, Double> getScores() {
        return Collections.unmodifiableMap(scores);
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public State execute(Command command) {
        if (!command.isStart()) {
            return command.executeTo(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임이 진행 중 이여서 새로운 게임을 시작 할 수 없습니다.");
    }
}
