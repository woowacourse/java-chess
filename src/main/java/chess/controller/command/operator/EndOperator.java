package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.controller.command.CommandType;
import chess.domain.ChessGame;
import chess.renderer.CommendRenderer;

import java.util.List;

public class EndOperator extends Operator {

    private static final String NO_COMMAND_MESSAGE = "해당되는 명령어가 없습니다.";

    public EndOperator(ChessController chessController, ChessGame chessGame) {
        super(chessController, chessGame);
    }

    @Override
    public boolean operate(List<String> command) {
        if (CommendRenderer.isSame(command.get(0), CommandType.END)) {
            return false;
        }
        throw new IllegalArgumentException(NO_COMMAND_MESSAGE);
    }
}
