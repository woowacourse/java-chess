package chess.controller.commend;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
import chess.view.InputView;

public class EndCommend extends Commend {
    public EndCommend(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) {
        return false;
    }
}
