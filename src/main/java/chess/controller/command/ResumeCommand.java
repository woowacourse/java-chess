package chess.controller.command;

import chess.controller.ChessController;
import chess.controller.command.operator.Operator;
import chess.controller.command.operator.ResumeOperator;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.validator.ValidateType;

import java.sql.SQLException;
import java.util.List;

public class ResumeCommand extends Command {
    public ResumeCommand(ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(ChessGame chessGame) throws SQLException {
        Operator operator = new ResumeOperator(chessController, chessGame);
        return operator.operate(InputView.requestCommand(List.of(ValidateType.RESUME)));
    }
}
