package chess.view.commend;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.view.validator.ValidateType;

import java.util.List;

public class StartCommend extends Commend {
    public StartCommend(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) {
        if (CommendType.match(inputView.requestCommend(List.of(ValidateType.START)).get(0)).equals(CommendType.END)) {
            this.chessController.setCommend(new EndCommend(chessController));
            return true;
        }
        ;
        outputView.printChessBoard(chessGame.getChessboard());
        this.chessController.setCommend(new RunningCommend(chessController));
        return true;
    }
}
