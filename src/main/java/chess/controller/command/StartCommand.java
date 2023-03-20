package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.renderer.CommendRenderer;
import chess.view.validator.ValidateType;

import java.util.List;

public class StartCommand extends Command {
    public StartCommand(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) {
        if (CommendRenderer.render(inputView.requestCommend(List.of(ValidateType.START)).get(0)).equals(CommandType.END)) {
            this.chessController.setCommend(new EndCommand(chessController));
            return true;
        }
        ;
        outputView.printChessBoard(chessGame.getChessboard());
        this.chessController.setCommend(new RunningCommand(chessController));
        return true;
    }
}
