package chess.controller;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ChessController {
    private Map<Command, Function<RequestDto, ResponseDto>> commands;
    private ChessGame chessGame;

    public ChessController() {
        this.commands = new HashMap<>();
        commands.put(Command.START, this::start);
        commands.put(Command.MOVE, this::move);
        commands.put(Command.STATUS, this::status);
        chessGame = new ChessGame();
    }

    public ResponseDto start(RequestDto requestDto) {
        return chessGame.start(requestDto);
    }

    public ResponseDto move(RequestDto requestDto) {
        return chessGame.move(requestDto);
    }

    public ResponseDto status(RequestDto requestDto) {
        return chessGame.status(requestDto);
    }

    public ResponseDto end(RequestDto requestDto) {
        return chessGame.end(requestDto);
    }

    public ResponseDto getResponseDto(RequestDto requestDto) {
        return commands.get(requestDto.getCommand()).apply(requestDto);
    }
}