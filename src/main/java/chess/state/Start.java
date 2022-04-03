package chess.state;

import chess.chessBoard.Board;
import chess.game.Player;

import static chess.state.Command.*;

public final class Start extends Running {

    public Start(final Board board) {
        super(board);
        initBoard();
    }

    public static State initState(final String command) {
        if (!END.isUserInput(command)) {
            throw new IllegalArgumentException("[ERROR] 아직 게임이 시작되지 않았습니다.");
        }
        return new Start(new Board());
    }

    @Override
    public State proceed(final String command) {
        if (END.startsWith(command)) {
            return new End(board);
        }
        if (MOVE.startsWith(command)) {
            return new Turn(command, board, Player.WHITE);
        }
        throw new IllegalArgumentException("[ERROR] 올바른 명령을 입력해주세요.");
    }

    private void initBoard() {
        board.initBoard();
        board.createBlackPieces();
        board.createWhitePieces();
    }
}
