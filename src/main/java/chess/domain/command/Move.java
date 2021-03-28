package chess.domain.command;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

public class Move implements Command {
    private static final String TURN_ERROR = "[ERROR] 현재 턴이 아닌 말은 움직일 수 없습니다.";
    private static final int MOVE_COMMANDS_SIZE = 3;
    private final String[] splitInput;

    public Move(String[] splitInput) {
        validateRemainingCommand(splitInput);
        this.splitInput = splitInput;
    }

    private void validateRemainingCommand(String[] splitInput) {
        if (splitInput.length != MOVE_COMMANDS_SIZE || !isCorrectPositionInput(splitInput)) {
            throw new IllegalArgumentException(COMMAND_ERROR);
        }
    }

    private boolean isCorrectPositionInput(String[] splitInput) {
        return splitInput[1].length() == 2 || splitInput[2].length() == 2;
    }

    @Override
    public Command move(ChessBoard chessBoard, Color turn) {
        Position source = Position.of(splitInput[1].charAt(0), splitInput[1].charAt(1));
        Position target = Position.of(splitInput[2].charAt(0), splitInput[2].charAt(1));
        Piece sourcePiece = chessBoard.findByPosition(source);
        validateTurn(sourcePiece, turn);
        chessBoard.movePiece(source, target);
        return new Waiting();
    }

    private void validateTurn(Piece sourcePiece, Color turn) {
        if (!sourcePiece.isSameColor(turn)) {
            throw new IllegalArgumentException(TURN_ERROR);
        }
    }

    @Override
    public Command changeWaiting() {
        return new Waiting();
    }

    @Override
    public Command changeRunningCommand(String input) {
        throw new UnsupportedOperationException(NOT_EXECUTE_ERROR);
    }
}
