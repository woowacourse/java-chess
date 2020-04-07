package chess.service;

import chess.controller.dto.Command;
import chess.controller.dto.ResponseDto;
import chess.domain.ChessGame;
import chess.domain.MoveParameter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessService {
    private final Map<Command, Function<List<String>, ResponseDto>> commands = new HashMap<>();
    private ChessGame chessGame;

    public ChessService() {
        chessGame = ChessGame.start();
        commands.put(Command.START, this::start);
        commands.put(Command.MOVE, this::move);
        commands.put(Command.END, this::end);
        commands.put(Command.STATUS, this::status);
        commands.put(Command.UNKNOWN, this::unknown);
    }

    public ResponseDto run(Command command, List<String> parameters) {
        return commands.get(command).apply(parameters);
    }

    private ResponseDto start(final List<String> parameters) {
        return new ResponseDto(chessGame.getBoard());
    }

    private ResponseDto move(final List<String> parameters) {
        chessGame.move(MoveParameter.of(parameters));
        return new ResponseDto(chessGame.getBoard());
    }

    private ResponseDto end(final List<String> parameters) {
        chessGame.end();
        return new ResponseDto(chessGame.getBoard());
    }

    private ResponseDto status(final List<String> parameter) {
        chessGame.status();
        return new ResponseDto(chessGame.getStatus());
    }

    private ResponseDto unknown(final List<String> parameter) {
        throw new UnsupportedOperationException("알 수 없는 명령어 입니다.");
    }

    public boolean isEnd() {
        return chessGame.isEnd();
    }
}
