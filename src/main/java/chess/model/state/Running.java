package chess.model.state;

import chess.model.board.GameScore;
import chess.model.command.Command;
import chess.model.Team;
import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.Map;

public class Running implements State {

    protected final Board board;

    public Running(final Board board) {
        this.board = board;
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
    public GameScore getScores() {
        throw new IllegalArgumentException("[ERROR] 점수를 집계 할 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        return board.getBoard();
    }

    @Override
    public State execute(final Command command) {
        if (!command.isStart()) {
            return command.executeTo(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임이 진행 중 이여서 새로운 게임을 시작 할 수 없습니다.");
    }
}
