package chess.domain.state;

import chess.domain.board.ChessBoard;

public class End extends AbstractChessState {

    protected End(final ChessBoard chessBoard) {
        super(chessBoard);
    }

    @Override
    public ChessState command(final Command command) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean runnable() {
        return false;
    }
}
