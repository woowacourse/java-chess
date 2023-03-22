package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.controller.command.CommandType;
import chess.controller.command.RunningCommand;
import chess.domain.ChessGame;
import chess.renderer.CommendRenderer;
import chess.view.OutputView;

import java.util.List;

public class StartOperator extends Operator {
    public StartOperator(ChessController chessController, ChessGame chessGame) {
        super(chessController, chessGame);
    }

    @Override
    public boolean operate(List<String> command) {
        if (!CommendRenderer.isSame(command.get(0), CommandType.START)) {
            Operator next = new StatusOperator(chessController, chessGame);
            return next.operate(command);
        }
        chessController.setCommend(new RunningCommand(chessController));
        OutputView.printChessBoard(chessGame.getChessboard());
        return true;
    }
}
