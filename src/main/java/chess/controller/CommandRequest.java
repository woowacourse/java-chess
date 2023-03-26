package chess.controller;

import chess.domain.position.Position;
import java.util.List;
import java.util.Optional;

public class CommandRequest {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final String REQUEST_IS_NOT_VALIDATE_SIZE_MESSAGE = "올바른 명령을 입력해주십시오.";
    private static final String REQUEST_NOT_CONTAIN_POSITION_MESSAGE = "이 커맨드는 Position을 포함하지 않습니다.";
    private static final String REQUEST_NOT_CONTAIN_ID_MESSAGE = "이 커맨드는 ID를 포함하지 않습니다.";
    private static final String GAME_ID_IS_LONG_TYPE_MESSAGE = "게임 ID는 숫자형태 입니다.";
    private static final int COMMAND_INDEX = 0;
    private static final int GAME_ID_INDEX = 1;
    private static final int FROM_POSITION_INDEX = 1;
    private static final int TO_POSITION_INDEX = 2;

    private final GameCommand gameCommand;
    private final Optional<Position> from;
    private final Optional<Position> to;
    private final Optional<Long> id;

    private CommandRequest(final GameCommand gameCommand) {
        this.gameCommand = gameCommand;
        from = Optional.empty();
        to = Optional.empty();
        id = Optional.empty();
    }

    private CommandRequest(final GameCommand gameCommand, final String from, final String to) {
        this.gameCommand = gameCommand;
        this.from = Optional.of(renderPosition(from));
        this.to = Optional.of(renderPosition(to));
        this.id = Optional.empty();
    }

    private CommandRequest(final GameCommand gameCommand, final String id) {
        this.gameCommand = gameCommand;
        this.from = Optional.empty();
        this.to = Optional.empty();
        this.id = Optional.of(renderId(id));
    }

    public static CommandRequest from(final List<String> request) {
        final GameCommand command = GameCommand.from(request.get(COMMAND_INDEX));
        if (request.size() == GameCommand.MOVE_COMMAND_SIZE && command == GameCommand.MOVE) {
            return new CommandRequest(command, request.get(FROM_POSITION_INDEX), request.get(TO_POSITION_INDEX));
        }
        if (request.size() == GameCommand.LOAD_COMMAND_SIZE && command == GameCommand.LOAD) {
            return new CommandRequest(command, request.get(GAME_ID_INDEX));
        }
        if (request.size() == GameCommand.DEFAULT_COMMAND_SIZE) {
            return new CommandRequest(command);
        }
        throw new IllegalArgumentException(REQUEST_IS_NOT_VALIDATE_SIZE_MESSAGE);
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
            throw new IllegalArgumentException(GAME_ID_IS_LONG_TYPE_MESSAGE);
        }
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public Position getFrom() {
        return from.orElseThrow(() -> new UnsupportedOperationException(REQUEST_NOT_CONTAIN_POSITION_MESSAGE));
    }

    public Position getTo() {
        return to.orElseThrow(() -> new UnsupportedOperationException(REQUEST_NOT_CONTAIN_POSITION_MESSAGE));
    }

    public Long getId() {
        return id.orElseThrow(() -> new UnsupportedOperationException(REQUEST_NOT_CONTAIN_ID_MESSAGE));
    }
}
