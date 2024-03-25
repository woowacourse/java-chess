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
    private static final Pattern pattern = Pattern.compile(MOVE_POSITION_REGEX_FORMAT);
    public static final String INVALID_COMMAND_ERROR = "[ERROR] 잘못된 명령어 입력입니다.";
    private final Position source;
    private final Position target;

    public MoveCommandExecutor(final CommandType commandType) {
        if (commandType.notEqualsSupplementSize(2)) {
            throw new IllegalArgumentException(INVALID_COMMAND_ERROR);
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
            throw new IllegalArgumentException(INVALID_COMMAND_ERROR);
        }
    }

    private boolean isInvalidCoordinate(String coordinate) {
        return !pattern.matcher(coordinate).matches();
    }

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.move(source, target);
    }
}
