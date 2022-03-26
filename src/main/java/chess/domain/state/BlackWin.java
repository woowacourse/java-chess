package chess.domain.state;

import chess.domain.ChessBoard;

public class BlackWin extends Finished {

    protected BlackWin(ChessBoard chessBoard) {
        super(chessBoard);
    }
}
