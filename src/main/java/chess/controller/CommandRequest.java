package chess.controller;

import chess.model.domain.position.Position;
import chess.model.exception.GameIdTypeException;
import chess.model.exception.RequestNotContainIdException;
import chess.model.exception.RequestNotContainPositionException;
import java.util.List;

public class CommandRequest {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int PARAMETER_START_INDEX = 1;
    private static final int FROM_INDEX = 0;
    private static final int TO_INDEX = 1;
    private static final int ID_INDEX = 0;

    private final GameCommand gameCommand;
    private final List<String> parameters;

    private CommandRequest(final GameCommand gameCommand, final List<String> parameters) {
        this.gameCommand = gameCommand;
        this.parameters = parameters;
    }

    public static CommandRequest from(final List<String> request) {
        final GameCommand command = GameCommand.from(request.get(COMMAND_INDEX));
        final List<String> parameters = request.subList(PARAMETER_START_INDEX, request.size());
        return new CommandRequest(command, parameters);
    }

    private static Position renderPosition(final String position) {
        final int file = position.charAt(FILE_INDEX) - 'a' + 1;
        final int rank = position.charAt(RANK_INDEX) - '0';
        return new Position(file, rank);
    }

    private static Long renderId(final String id) {
        try {
            return Long.valueOf(id);
        } catch (final NumberFormatException e) {
            throw new GameIdTypeException();
        }
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public Position getFrom() {
        validateMove();
        return renderPosition(parameters.get(FROM_INDEX));
    }

    public Position getTo() {
        validateMove();
        return renderPosition(parameters.get(TO_INDEX));
    }

    private void validateMove() {
        if (gameCommand != GameCommand.MOVE
                && parameters.size() != GameCommand.MOVE_PARAMETER_SIZE) {
            throw new RequestNotContainPositionException();
        }
    }

    public Long getId() {
        validateLoad();
        return renderId(parameters.get(ID_INDEX));
    }

    private void validateLoad() {
        if (gameCommand != GameCommand.LOAD
                && parameters.size() != GameCommand.LOAD_PARAMETER_SIZE) {
            throw new RequestNotContainIdException();
        }
    }
}
