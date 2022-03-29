package chess.model.state;

import chess.model.board.Board;

public class Start extends Running {

    public Start(Board board) {
        super(board);
    }

    public static State initState(String command) {
        if (!command.equals("start")) {
            throw new IllegalArgumentException("[ERROR] 아직 게임이 시작되지 않았습니다.");
        }
        return new Start(new Board());
    }

    @Override
    public State proceed(String command) {
        if (command.startsWith("end")) {
            return new End(board);
        }
        if (command.startsWith("move")) {
            return new WhiteTurn(command, board);
        }
        throw new IllegalArgumentException("[ERROR] 올바른 명령을 입력해주세요.");
    }
}
