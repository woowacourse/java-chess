package chess.domain.command;

import chess.domain.ChessCalculator;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.position.Position;
import utils.CommandParser;

import java.util.Objects;

public class Command {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    private String[] command;

    private Command(String command) {
        Objects.requireNonNull(command, "null이 입력되었습니다.");
        this.command = CommandParser.parseCommand(command);
    }

    public static Command from(String inputCommand) {
        return new Command(inputCommand);
    }

    public ChessBoard commandMoveRun(ChessBoard chessBoard) {
        chessBoard.move(sourcePosition(), targetPosition());
        return chessBoard;
    }

    public double commandStatusRun(ChessBoard chessBoard) {
        return ChessCalculator.calculateScoreOf(chessBoard);
    }

    private Position sourcePosition() {
        return Position.of(this.command[SOURCE_POSITION_INDEX]);
    }

    private Position targetPosition() {
        return Position.of(this.command[TARGET_POSITION_INDEX]);
    }

    public String getCommand() {
        return this.command[COMMAND_INDEX];
    }

}
