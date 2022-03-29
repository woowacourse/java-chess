package chess.model.state;

import chess.model.board.Board;
import chess.model.Team;
import chess.model.position.Position;

import java.util.List;

public class BlackTurn extends Running {

    protected BlackTurn(String command, Board board) {
        super(board);
        List<String> option = List.of(command.split(" "));
        Position source = Position.from(option.get(1));
        Position target = Position.from(option.get(2));
        checkTurn(source);
        board.move(source, target);
    }

    private void checkTurn(Position source) {
        if (!board.checkRightTurn(Team.BLACK, source)) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 선택 할 수 없습니다.");
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public State proceed(String command) {
        if (command.startsWith("end") || board.isKilledKing()) {
            return new End(board);
        }
        if (command.startsWith("move")) {
            return new WhiteTurn(command, board);
        }
        throw new IllegalArgumentException("[ERROR] 올바른 명령이 아닙니다.");
    }
}
