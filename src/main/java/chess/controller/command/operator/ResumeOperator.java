package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.controller.command.CommandType;
import chess.controller.command.RunningCommand;
import chess.domain.ChessGame;
import chess.renderer.CommendRenderer;
import chess.view.OutputView;

import java.sql.SQLException;
import java.util.List;

public class ResumeOperator extends Operator {
    private static final int COMMAND_INDEX = 0;

    public ResumeOperator(final ChessController chessController, final ChessGame chessGame) {
        super(chessController, chessGame);
    }

    @Override
    public boolean operate(final List<String> command) throws SQLException {
        if (!CommendRenderer.isSame(command.get(COMMAND_INDEX), CommandType.RESUME)) {
            final Operator next = new StartOperator(chessController, chessGame);
            return next.operate(command);
        }
        chessController.setCommend(new RunningCommand(chessController));
        chessGame.resumeNotation();
        OutputView.printChessBoard(chessGame.getChessboard());
        return true;
    }
}
