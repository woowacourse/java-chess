package chess;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import java.util.List;

public class GameCommand {
    private static final String SQUARE_BOUND_REGULAR_EXPRESSION = "^[a-h][1-8]$";
    private final List<String> gameCommand;

    public GameCommand(final List<String> gameCommand) {
        validateGameCommand(gameCommand);
        this.gameCommand = gameCommand;
    }

    private void validateGameCommand(final List<String> gameCommand) {
        final String command = gameCommand.get(0);

        if (command.equals("move") && gameCommand.size() == 3) {
            final String source = gameCommand.get(1);
            final String target = gameCommand.get(2);
            validateMoveCommand(source, target);
            return;
        }
        if (!(command.equals("start") || command.equals("end"))) {
            throw new IllegalArgumentException("게임 명령어가 올바르지 않습니다.");
        }
    }

    private void validateMoveCommand(final String source, final String target) {
        if (!source.matches(SQUARE_BOUND_REGULAR_EXPRESSION) && target.matches(SQUARE_BOUND_REGULAR_EXPRESSION)) {
            throw new IllegalArgumentException("정확한 source 위치와 target 위치를 입력해주세요");
        }
    }

    public boolean isStart() {
        return gameCommand.get(0).equals("start");
    }

    public boolean isMove() {
        return gameCommand.get(0).equals("move");
    }

    public List<Square> convertToCoordinate() {
        final String source = gameCommand.get(1);
        final String target = gameCommand.get(2);

        final File sourceFile = File.findFile(source.charAt(0) - 'a');
        final Rank sourceRank = Rank.findRank(source.charAt(1) - '1');

        final File targetFile = File.findFile(target.charAt(0) - 'a');
        final Rank targetRank = Rank.findRank(target.charAt(1) - '1');

        return List.of(new Square(sourceFile, sourceRank), new Square(targetFile, targetRank));
    }
}
