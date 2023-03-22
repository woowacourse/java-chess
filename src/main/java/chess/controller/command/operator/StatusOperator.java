package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.controller.command.CommandType;
import chess.domain.ChessGame;
import chess.renderer.CommendRenderer;
import chess.view.OutputView;

import java.util.List;

public class StatusOperator extends Operator {
    public StatusOperator(ChessController chessController, ChessGame chessGame) {
        super(chessController, chessGame);
    }

    @Override
    public boolean operate(List<String> command) {
        if (!CommendRenderer.isSame(command.get(0), CommandType.STATUS)) {
            Operator next = new MoveOperator(chessController, chessGame);
            return next.operate(command);
        }
        OutputView.printScore(chessGame.getChessboard(), chessGame.getTurn());
        return true;
    }
}
