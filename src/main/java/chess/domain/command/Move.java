package chess.domain.command;

import chess.domain.game.Side;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public final class Move implements Command {

    private static final String SPACE = " ";
    private static final int COMMAND_ARRAY_MAX_LENGTH = 3;

    private String moveId;
    private String gameId;
    private Side side;

    private final Position source;
    private final Position target;

    public Move(String command) {
        this(validatedLength(command));
    }

    private static String[] validatedLength(String command) {
        String[] splittedCommand = command.split(SPACE);
        if (splittedCommand.length != COMMAND_ARRAY_MAX_LENGTH) {
            throw new InvalidCommandException();
        }
        return splittedCommand;
    }

    private Move(String... sourceTarget) {
        this(sourceTarget[1], sourceTarget[2]);
    }

    public Move(String source, String target) {
        this(Position.from(source), Position.from(target));
    }

    public Move(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isMove() {
        return true;
    }

    @Override
    public Position source() {
        return source;
    }

    @Override
    public Position target() {
        return target;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getMoveId() {
        return moveId;
    }

    public void setMoveId(String moveId) {
        this.moveId = moveId;
    }

    public String getSource() {
        return source.toString();
    }

    public String getTarget() {
        return target.toString();
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }
}
