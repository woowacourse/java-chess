package chess.controller;

import chess.controller.dto.Command;
import chess.controller.dto.RequestDto;
import chess.controller.dto.ResponseDto;
import chess.controller.dto.SubCommand;
import chess.service.ChessService;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ChessConsoleController {

    private ChessService chessService = new ChessService();
    private InputView inputView;
    private OutputView outputView;
    private Map<Command, Consumer<List<String>>> gameRunner;
    private boolean isEnd;
    private Long runningGameId;

    public ChessConsoleController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.isEnd = false;
        initializeGameRunner();
    }

    public void run() {
        outputView.printInitialMessage();
        outputView.printRoom(chessService.getRoomId());

        while (!isEnd) {
            try {
                RequestDto requestDto = inputView.inputRequest();
                runCommand(requestDto);
                ResponseDto responseDto = getResponseDto(runningGameId);
                outputView.printResponse(responseDto);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void runCommand(RequestDto requestDto) {
        Command command = requestDto.getCommand();
        List<String> parameter = requestDto.getParameter();
        gameRunner.get(command).accept(parameter);
    }

    private void create(List<String> parameter) {
        runningGameId = chessService.createGame();
        outputView.printGameId(runningGameId);
    }

    private void enter(List<String> parameter) {
        runningGameId = Long.valueOf(parameter.get(0));
        chessService.load(runningGameId);
    }

    private void start(List<String> parameter) {
        switch (SubCommand.of(parameter.get(0))) {
            case NEW:
                chessService.restart(runningGameId);
                break;
            case LOAD:
                chessService.load(runningGameId);
                break;
            default:
                throw new IllegalArgumentException("잘못된 시작 명령어입니다.");
        }
    }

    private void end(List<String> parameter) {
        switch (SubCommand.of(parameter.get(0))) {
            case SAVE:
                chessService.save(runningGameId);
                isEnd = true;
                break;
            case EMPTY:
                chessService.remove(runningGameId);
                isEnd = true;
                break;
            default:
                throw new IllegalArgumentException("잘못된 종료 명령어입니다.");
        }
    }

    private void move(List<String> parameter) {
        chessService.move(runningGameId, parameter);
    }

    private void unknown(List<String> parameter) {
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }

    private ResponseDto getResponseDto(Long id) {
        return chessService.getResponseDto(id);
    }

    private void initializeGameRunner() {
        gameRunner = new HashMap<>();
        gameRunner.put(Command.CREATE, this::create);
        gameRunner.put(Command.ENTER, this::enter);
        gameRunner.put(Command.START, this::start);
        gameRunner.put(Command.END, this::end);
        gameRunner.put(Command.MOVE, this::move);
        gameRunner.put(Command.UNKNOWN, this::unknown);
    }
}
