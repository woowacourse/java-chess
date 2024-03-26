package controller.command;

import static view.InputView.MOVE_POSITION_REGEX_FORMAT;

import domain.game.ChessGame;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import java.util.List;
import java.util.regex.Pattern;
import view.command.CommandType;

public class MoveCommandExecutor implements CommandExecutor {
    private static final Pattern POSITION_INPUT_PATTERN = Pattern.compile(MOVE_POSITION_REGEX_FORMAT);
    private final Position source;
    private final Position target;

    public MoveCommandExecutor(final CommandType commandType) {
        if (commandType.notEqualsSupplementSize(2)) {
            throw new IllegalArgumentException("[ERROR] 게임 이동 명령어를 올바르게 입력해주세요.");
        }
        List<String> supplements = commandType.getSupplements();
        this.source = convertToPosition(supplements.get(0));
        this.target = convertToPosition(supplements.get(1));
    }

    private Position convertToPosition(final String coordinate) {
        validateIllegalCoordinate(coordinate);
        File file = new File(coordinate.charAt(0));
        Rank rank = new Rank(Character.getNumericValue(coordinate.charAt(1)));
        return new Position(file, rank);
    }

    private void validateIllegalCoordinate(String coordinate) {
        if (isInvalidCoordinate(coordinate)) {
            throw new IllegalArgumentException("[ERROR] 게임 이동 명령어를 올바르게 입력해주세요.");
        }
    }

    private boolean isInvalidCoordinate(String coordinate) {
        return !POSITION_INPUT_PATTERN.matcher(coordinate).matches();
    }

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.move(source, target);
    }
}
