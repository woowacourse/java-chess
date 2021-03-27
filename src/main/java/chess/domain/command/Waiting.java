package chess.domain.command;

import chess.domain.ChessBoard;
import chess.domain.piece.info.Color;

public class Waiting implements Command {
    @Override
    public Command move(ChessBoard chessBoard, Color turn) {
        throw new UnsupportedOperationException(NOT_EXECUTE_ERROR);
    }

    @Override
    public Command changeWaiting() {
        throw new UnsupportedOperationException(NOT_EXECUTE_ERROR);
    }

    @Override
    public Command changeRunningCommand(String input) {
        String[] splitInput = input.split(" ");
        if ("move".equals(splitInput[0])) {
            return new Move(splitInput);
        }
        if ("status".equals(splitInput[0])) {
            return new Status();
        }
        if ("end".equals(splitInput[0])) {
            return new End();
        }
        throw new UnsupportedOperationException(COMMAND_ERROR);
    }
}
