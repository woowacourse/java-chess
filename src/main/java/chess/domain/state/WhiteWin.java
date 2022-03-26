package chess.domain.state;

import chess.domain.ChessBoard;

public class WhiteWin extends Finished {

    protected WhiteWin(ChessBoard chessBoard) {
        super(chessBoard);
    }
}
