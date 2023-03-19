package chess.domain.game;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.List;

public class GameCommand {
    private enum GameCommandIndex {
        COMMAND(0),
        SOURCE(1),
        TARGET(2);

        private final int index;

        GameCommandIndex(final int index) {
            this.index = index;
        }
    }

    private enum MoveIndex {
        FILE(0),
        RANK(1);

        private final int index;

        MoveIndex(final int index) {
            this.index = index;
        }

    }

    private static final String SQUARE_BOUND_REGULAR_EXPRESSION = "^[a-h][1-8]$";
    private static final int MOVE_COMMAND_SIZE = 3;
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String END_COMMAND = "end";

    private final List<String> gameCommand;

    public GameCommand(final List<String> gameCommand) {
        validateGameCommand(gameCommand);
        this.gameCommand = gameCommand;
    }

    private void validateGameCommand(final List<String> gameCommand) {
        final String command = gameCommand.get(GameCommandIndex.COMMAND.index);
        final boolean isCommandMove = command.equals(MOVE_COMMAND) && gameCommand.size() == MOVE_COMMAND_SIZE;

        if (isCommandMove) {
            validateMoveCommand(
                    gameCommand.get(GameCommandIndex.SOURCE.index),
                    gameCommand.get(GameCommandIndex.TARGET.index)
            );
            return;
        }

        if (!(command.equals(START_COMMAND) || command.equals(END_COMMAND))) {
            throw new IllegalArgumentException("게임 명령어가 올바르지 않습니다.");
        }
    }

    private void validateMoveCommand(final String source, final String target) {
        if (!source.matches(SQUARE_BOUND_REGULAR_EXPRESSION) && target.matches(SQUARE_BOUND_REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException("정확한 source 위치와 target 위치를 입력해주세요");
        }
    }

    public boolean isStart() {
        return gameCommand.get(GameCommandIndex.COMMAND.index).equals(START_COMMAND);
    }

    public boolean isMove() {
        return gameCommand.get(GameCommandIndex.COMMAND.index).equals(MOVE_COMMAND);
    }

    public boolean isEnd() {
        return gameCommand.get(GameCommandIndex.COMMAND.index).equals(END_COMMAND);
    }

    public List<Square> convertToSquare() {
        final String source = gameCommand.get(GameCommandIndex.SOURCE.index);
        final String target = gameCommand.get(GameCommandIndex.TARGET.index);

        final File sourceFile = File.findFileByLetter(source.charAt(MoveIndex.FILE.index));
        final Rank sourceRank = Rank.findRankByLetter(source.charAt(MoveIndex.RANK.index));

        final File targetFile = File.findFileByLetter(target.charAt(MoveIndex.FILE.index));
        final Rank targetRank = Rank.findRankByLetter(target.charAt(MoveIndex.RANK.index));

        return List.of(new Square(sourceFile, sourceRank), new Square(targetFile, targetRank));
    }
}
