package chess.domain.state;

import chess.domain.ChessBoard;

public class End extends Finished {

    protected End(ChessBoard chessBoard) {
      super(chessBoard);
    }
}
