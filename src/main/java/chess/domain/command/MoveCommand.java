package chess.domain.command;

import chess.domain.Game;
import chess.domain.position.Position;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveCommand extends BasicCommand {

    private static final int SOURCE_POSITION = 1;
    private static final int TARGET_POSITION = 2;

    public MoveCommand(final Game game) {
        super(game);
    }

    private static Position positionOf(String input, int pieceIndex) {
        Pattern pattern = Pattern.compile(Commands.MOVE.getMessage());
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Position.of(matcher.group(pieceIndex));
        }
        throw new IllegalArgumentException("유효하지 않은 체스말을 입력했습니다.");
    }

    @Override
    public void execute(String input) {
        if (!game.isRunning()) {
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
        game.move(positionOf(input, SOURCE_POSITION), positionOf(input, TARGET_POSITION));
    }

    @Override
    public boolean isStatus() {
        return false;
    }
}
