package chess.controller;

import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class ChessConsoleController {

    private ChessService chessService = new ChessService();
    private InputView inputView;
    private OutputView outputView;
    private Long runningGameId;

    public ChessConsoleController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printInitialMessage();
        outputView.printRoom(chessService.getRoomId());

        while (true) {
            RequestDto requestDto = inputView.inputRequest();
            runCommand(requestDto);
            ResponseDto responseDto = getResponseDto(runningGameId);
            outputView.printResponse(responseDto);
        }
    }

    private void runCommand(RequestDto requestDto) {
        switch (requestDto.getCommand()) {
            case CREATE:
                create();
                break;
            case ENTER:
                enter(requestDto.getParameter());
                break;
            case START:
                start(requestDto.getParameter());
                break;
            case END:
                end(requestDto.getParameter());
                break;
            case MOVE:
                chessService.move(runningGameId, requestDto.getParameter());
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void enter(List<String> parameter) {
        runningGameId = Long.valueOf(parameter.get(0));
        chessService.load(runningGameId);
    }

    private void create() {
        runningGameId = chessService.createGame();
    }

    private void start(List<String> parameter) {
        if ("new".equals(parameter.get(0))) {
            chessService.restart(runningGameId);
        }
        if ("load".equals(parameter.get(0))) {
            chessService.load(runningGameId);
        }
    }

    private void end(List<String> parameter) {
        if ("save".equals(parameter.get(0))) {
            chessService.save(runningGameId);
        }
        if ("".equals(parameter.get(0))) {
            chessService.remove(runningGameId);
        }
    }

    private ResponseDto getResponseDto(Long id) {
        return chessService.getResponseDto(id);
    }
}
