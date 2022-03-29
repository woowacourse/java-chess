package chess.state;

import chess.board.Board;
import chess.Player;
import chess.Position;

import java.util.List;

public class WhiteTurn extends Running {

    protected WhiteTurn(String command, Board board) {
        super(board);
        List<String> option = List.of(command.split(" "));
        Position source = Position.from(option.get(1));
        Position target = Position.from(option.get(2));
        checkTurn(source);
        board.move(source, target);
    }

    private void checkTurn(Position source) {
        if (!board.checkRightTurn(Player.WHITE, source)) {
            throw new IllegalArgumentException("[ERROR] 상대편 기물은 선택 할 수 없습니다.");
        }
    }

    @Override
    public State proceed(String command) {
        if (command.startsWith("end") || board.isKilledKing()) {
            return new End(board);
        }
        if (command.startsWith("move")) {
            return new BlackTurn(command, board);
        }
        throw new IllegalArgumentException("[ERROR] 올바른 명령이 아닙니다.");
    }
}
