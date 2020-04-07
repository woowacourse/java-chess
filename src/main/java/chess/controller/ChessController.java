package chess.controller;

import chess.controller.dto.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.service.ChessService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

public class ChessController {

    private ChessService chessService = new ChessService();
    private Map<Command, BiConsumer<Long, List<String>>> runner;

    public ChessController() {
        this.runner = new HashMap<>();
        runner.put(Command.START, this::start);
        runner.put(Command.END, this::end);
        runner.put(Command.MOVE, this::move);
        runner.put(Command.UNKNOWN, this::unknown);
    }

    public boolean isEnd(Long id) {
        return chessService.isEnd(id);
    }

    public Long createChessGame() throws SQLException {
        return chessService.createGame();
    }

    public ResponseDto run() {
        ResponseDto responseDto = new ResponseDto();
        try {
            responseDto.setRoomId(chessService.getRoomId());
        } catch (SQLException e) {
            responseDto.setMessage(e.getMessage());
        }
        return responseDto;
    }

    public ResponseDto run(Long id, RequestDto requestDto) {
        String message = null;

        try {
            Command command = requestDto.getCommand();
            runner.get(command).accept(id, requestDto.getParameter());
        } catch (IllegalArgumentException | UnsupportedOperationException e) {
            message = e.getMessage();
        }
        ResponseDto responseDto = chessService.getResponseDto(id);
        responseDto.setMessage(message);
        return responseDto;
    }

    public ResponseDto getResponseDto(Long id) {
        return chessService.getResponseDto(id);
    }

    private void start(Long id, List<String> parameter) {
        try {
            chessService.start(id, parameter);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    private void end(Long id, List<String> parameter) {
        try {
            chessService.end(id, parameter);
        } catch (SQLException se) {
            System.out.println(se.getMessage());
        }
    }

    private void move(Long id, List<String> parameter) {
        chessService.move(id, parameter);
    }

    private ResponseDto unknown(Long id, List<String> parameter) {
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }
}
