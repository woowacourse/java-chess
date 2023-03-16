package chess.domain.state;

import chess.domain.board.ChessBoard;
import chess.domain.state.command.Command;

public class End extends AbstractChessState {

    protected End(final ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public ChessState command(final Command command) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public boolean runnable() {
        return false;
    }
}
