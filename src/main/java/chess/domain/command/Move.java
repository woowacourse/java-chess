package chess.domain.command;

import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;

public class Move implements Command {

    private static final String MOVE_COMMAND = "move";
    private static final String REGEX = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int MOVE_COMMAND_COUNT = 3;
    private static final String MATCH_PATTERN = "[a-h][1-8]";

    private final ChessGame chessGame;

    public Move(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execution(String text) {
        String[] splitedCommand = text.split(REGEX);
        Position source = Position.of(splitedCommand[SOURCE_INDEX]);
        Position target = Position.of(splitedCommand[TARGET_INDEX]);
        // chessGame.move(source, target);
    }

    @Override
    public boolean isMatchedCommand(String text) {
        validateCommand(text);
        String[] splitCommand = text.split(REGEX);
        return MOVE_COMMAND.equalsIgnoreCase(splitCommand[COMMAND_INDEX]);
    }

    private void validateCommand(String text) {
        String[] splitCommand = text.split(REGEX);
        validateCommandCount(splitCommand.length);
        validateCommandPattern(splitCommand[SOURCE_INDEX], splitCommand[TARGET_INDEX]);
    }

    private void validateCommandCount(int splitCommandCount) {
        if (splitCommandCount != MOVE_COMMAND_COUNT) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCommandPattern(String source, String target) {
        if (nonMatchedPattern(source) || nonMatchedPattern(target)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean nonMatchedPattern(String position) {
        return !MATCH_PATTERN.matches(position);
    }
}
