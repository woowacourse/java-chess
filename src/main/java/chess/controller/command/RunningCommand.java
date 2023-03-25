package chess.controller.command;

import chess.controller.ChessController;
import chess.controller.command.operator.Operator;
import chess.controller.command.operator.StatusOperator;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.validator.ValidateType;

import java.sql.SQLException;
import java.util.List;

public class RunningCommand extends Command {
    public RunningCommand(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) throws SQLException {
        Operator operator = new StatusOperator(chessController, chessGame);
        return operator.operate(InputView.requestCommand(List.of(ValidateType.PLAY,
                ValidateType.MOVE_SIZE,
                ValidateType.OUT_OF_RANGE)));
    }
}
