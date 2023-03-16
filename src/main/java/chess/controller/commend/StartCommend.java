package chess.controller.commend;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.view.validator.ValidateType;

public class StartCommend extends Commend {
    public StartCommend(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) {
        if (CommendType.match(inputView.requestCommend(ValidateType.START).get(0)).equals(CommendType.END)) {
            this.chessController.setCommend(new EndCommend(chessController));
            return true;
        }
        ;
        outputView.printChessBoard(chessGame.getChessboard());
        this.chessController.setCommend(new RunningCommend(chessController));
        return true;
    }
}
