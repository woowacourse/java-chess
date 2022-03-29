package chess.model.state;

import chess.model.command.Command;
import chess.model.Team;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class Finished implements State {

    private final Board board;

    public Finished(final Board board) {
        this.board = board;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public boolean isSleep() {
        return false;
    }

    @Override
    public Map<Team, Double> getScores() {
        throw new IllegalArgumentException("[ERROR] 점수를 집계 할 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new IllegalArgumentException("[ERROR] 게임이 종료되어 체스판을 확인 할 수 없습니다.");
    }

    @Override
    public State execute(final Command command) {
        return command.executeTo(board);
    }
}
