package chess.model.state;

import chess.model.board.Board;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.state.running.WhiteTurn;
import java.util.List;
import java.util.Map;

public class Ready implements State {

    private final Board board;

    public Ready() {
        this.board = new Board();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State proceed(List<String> inputs) {
        Command command = Command.of(inputs.get(0));
        if (command.isStart()) {
            return new WhiteTurn(board);
        }
        throw new IllegalArgumentException("[ERROR] 게임을 시작하기 위한 명령어가 아닙니다.");
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new IllegalArgumentException("[ERROR] 아직 게임이 시작되지 않아 데이터를 가져 올 수 없습니다.");
    }
}
