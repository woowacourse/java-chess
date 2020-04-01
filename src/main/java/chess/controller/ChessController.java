package chess.controller;

import chess.controller.dto.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.service.ChessService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ChessController {

    private ChessService chessService = new ChessService();
    private Map<Command, Function<List<String>, ResponseDto>> runner;

    public ChessController() {
        this.runner = new HashMap<>();
        runner.put(Command.START, this::start);
        runner.put(Command.END, this::end);
        runner.put(Command.MOVE, this::move);
        runner.put(Command.STATUS, this::status);
        runner.put(Command.UNKNOWN, this::unknown);
    }

    public boolean isEnd() {
        return chessService.isEnd();
    }

    public synchronized ResponseDto run(RequestDto requestDto) {
        Command command = requestDto.getCommand();
        return runner.get(command).apply(requestDto.getParameter());
    }

    private ResponseDto start(List<String> parameter) {
        return chessService.start(parameter);
    }

    private ResponseDto end(List<String> parameter) {
        return chessService.end(parameter);
    }

    private ResponseDto move(List<String> parameter) {
        return chessService.move(parameter);
    }

    private ResponseDto status(List<String> parameter) {
        return chessService.status(parameter);
    }

    private ResponseDto unknown(List<String> parameter) {
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }
}
