package chess.domain.game;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.exception.ErrorCode;
import chess.exception.IllegalCommandException;
import chess.exception.IllegalStartCommandException;
import java.util.List;

public class GameCommand {
    private static final String SQUARE_BOUND_REGULAR_EXPRESSION = "^[a-h][1-8]$";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";
    private static final String STATUS_COMMAND = "status";
    private static final String SAVE_COMMAND = "save";

    private final List<String> gameCommand;

    public GameCommand(List<String> gameCommand) {
        validateGameCommand(gameCommand);
        this.gameCommand = gameCommand;
    }

    private void validateGameCommand(List<String> gameCommand) {
        String command = gameCommand.get(GameCommandIndex.COMMAND.index);
        boolean isCommandMove = MOVE_COMMAND.equals(command) && gameCommand.size() == MOVE_COMMAND_SIZE;

        if (isCommandMove) {
            validateMoveCommand(
                    gameCommand.get(GameCommandIndex.SOURCE.index),
                    gameCommand.get(GameCommandIndex.TARGET.index)
            );
            return;
        }

        if (!(START_COMMAND.equals(command) || END_COMMAND.equals(command)
                || STATUS_COMMAND.equals(command) || SAVE_COMMAND.equals(command))) {
            throw new IllegalCommandException(ErrorCode.ILLEGAL_COMMAND);
        }
    }

    private void validateMoveCommand(String source, String target) {
        if (!source.matches(SQUARE_BOUND_REGULAR_EXPRESSION) && target.matches(
                SQUARE_BOUND_REGULAR_EXPRESSION)) {
            throw new IllegalCommandException(ErrorCode.ILLEGAL_COMMAND);
        }
    }

    public void isStart() {
        if (!START_COMMAND.equals(gameCommand.get(GameCommandIndex.COMMAND.index))) {
            throw new IllegalStartCommandException(ErrorCode.ILLEGAL_START_COMMAND);
        }
    }

    public boolean isMove() {
        return MOVE_COMMAND.equals(gameCommand.get(GameCommandIndex.COMMAND.index));
    }

    public boolean isStatus() {
        return STATUS_COMMAND.equals(gameCommand.get(GameCommandIndex.COMMAND.index));
    }

    public boolean isSave() {
        return SAVE_COMMAND.equals(gameCommand.get(GameCommandIndex.COMMAND.index));
    }

    public List<Square> convertToSquare() {
        String source = gameCommand.get(GameCommandIndex.SOURCE.index);
        String target = gameCommand.get(GameCommandIndex.TARGET.index);

        File sourceFile = File.findFileByLetter(source.charAt(MoveIndex.FILE.index));
        Rank sourceRank = Rank.findRankByLetter(source.charAt(MoveIndex.RANK.index));

        File targetFile = File.findFileByLetter(target.charAt(MoveIndex.FILE.index));
        Rank targetRank = Rank.findRankByLetter(target.charAt(MoveIndex.RANK.index));

        return List.of(new Square(sourceFile, sourceRank), new Square(targetFile, targetRank));
    }

    private enum GameCommandIndex {
        COMMAND(0),
        SOURCE(1),
        TARGET(2);

        private final int index;

        GameCommandIndex(int index) {
            this.index = index;
        }
    }

    private enum MoveIndex {
        FILE(0),
        RANK(1);

        private final int index;

        MoveIndex(int index) {
            this.index = index;
        }
    }
}
