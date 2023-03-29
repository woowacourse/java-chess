package chess.controller.command;

import chess.controller.ChessController;
import chess.controller.command.operator.Operator;
import chess.controller.command.operator.StartOperator;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.validator.ValidateType;

import java.sql.SQLException;
import java.util.List;

public class StartCommand extends Command {
    public StartCommand(final ChessController chessController) {
        super(chessController);
    }

    @Override
    public boolean operate(final ChessGame chessGame) throws SQLException {
        final Operator operator = new StartOperator(chessController, chessGame);
        return operator.operate(InputView.requestCommand(List.of(ValidateType.START)));
    }
}
