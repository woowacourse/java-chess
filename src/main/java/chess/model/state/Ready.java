package chess.model.state;

import chess.model.command.Command;
import chess.model.Team;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class Ready implements State {

    private final Board board;

    public Ready() {
        this.board = Board.init();
    }

    @Override
    public boolean isFinished() {
        return false;
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
        throw new IllegalArgumentException("[ERROR] 아직 게임이 시작하지 않았습니다.");
    }

    @Override
    public State execute(final Command command) {
        if (command.isStart()) {
            return command.executeTo(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임을 시작하기 위한 명령어가 아닙니다.");
    }
}
