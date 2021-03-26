package chess.domain.command;

import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;

public class Move implements Command {
    private static final String MOVE_COMMAND = "move";
    private static final String MATCH_PATTERN = "[a-h][1-8]";
    private static final String REGEX = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int MOVE_COMMAND_COUNT = 3;
    private final ChessGame chessGame;

    public Move(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public void execution(String text) {
        String[] splitedCommand = text.split(REGEX);
        Position source = Position.of(splitedCommand[SOURCE_INDEX]);
        Position target = Position.of(splitedCommand[TARGET_INDEX]);
        chessGame.move(source, target);
    }

    @Override
    public boolean isMatchedCommand(String text) {
        String[] splitCommand = text.split(REGEX);
        return isSameCommandCount(splitCommand.length) &&
                isSameCommandPattern(splitCommand[SOURCE_INDEX], splitCommand[TARGET_INDEX]) &&
                MOVE_COMMAND.equalsIgnoreCase(splitCommand[COMMAND_INDEX]);
    }

    private boolean isSameCommandCount(int splitCommandCount) {
        return splitCommandCount == MOVE_COMMAND_COUNT;
    }

    private boolean isSameCommandPattern(String source, String target) {
        return isMatchedPattern(source) && isMatchedPattern(target);
    }

    private boolean isMatchedPattern(String position) {
        return position.matches(MATCH_PATTERN);
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
