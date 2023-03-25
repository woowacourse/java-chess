package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.controller.command.CommandType;
import chess.domain.ChessGame;
import chess.renderer.CommendRenderer;
import chess.view.OutputView;

import java.sql.SQLException;
import java.util.List;

public class StatusOperator extends Operator {

    private static final int COMMAND_INDEX = 0;

    public StatusOperator(ChessController chessController, ChessGame chessGame) {
        super(chessController, chessGame);
    }

    @Override
    public boolean operate(List<String> command) throws SQLException {
        if (!CommendRenderer.isSame(command.get(COMMAND_INDEX), CommandType.STATUS)) {
            Operator next = new MoveOperator(chessController, chessGame);
            return next.operate(command);
        }
        OutputView.printScore(chessGame.getChessboard(), chessGame.getTurn());
        return true;
    }
}
