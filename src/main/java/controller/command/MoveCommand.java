package controller.command;

import domain.board.Position;
import domain.game.Game;
import java.util.List;
import view.OutputView;
import view.dto.RankInfos;

public class MoveCommand implements Command {
    private static final String MOVE = "move";
    public static final int SOURCE_INDEX = 1;
    public static final int TARGET_INDEX = 2;
    private static MoveCommand instance;

    private final Game game;

    private MoveCommand(final Game game) {
        this.game = game;
    }

    public static MoveCommand of(final Game game) {
        if (instance == null) {
            instance = new MoveCommand(game);
        }
        return instance;
    }

    @Override
    public void execute(final List<String> commandTokens) {
        try {
            String sourceValue = commandTokens.get(SOURCE_INDEX);
            String targetValue = commandTokens.get(TARGET_INDEX);
            moveByPosition(sourceValue, targetValue);
        } catch (IndexOutOfBoundsException e) {
            OutputView.printErrorMessage("이동할 기물의 위치와 목표 위치를 모두 입력해주세요.");
        }
    }

    @Override
    public boolean isSameAs(final String value) {
        return value.startsWith(MOVE);
    }

    private void moveByPosition(final String sourceValue, final String targetValue) {
        final Position source = Position.createPosition(sourceValue);
        final Position target = Position.createPosition(targetValue);

        game.moveByPosition(source, target);
        OutputView.printChessBoard(RankInfos.of(game.board()));
    }
}
