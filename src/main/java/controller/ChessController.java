package controller;

import java.util.Map;
import java.util.function.Consumer;

import dto.CommandRequestDto;
import service.ChessService;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final ChessService chessService;

    private final Map<Command, Consumer<CommandRequestDto>> commandsAndExecutions = Map.of(
        Command.START, ignored -> start(),
        Command.END, ignored -> end(),
        Command.MOVE, this::move,
        Command.STATUS, ignored -> fetchScore());

    public ChessController() {
        this.chessService = new ChessService();
    }

    public void run() {
        OutputView.printChessInfo();

        play();
    }

    private void play() {
        try {
            executeCommand();
        } catch (IllegalArgumentException | IllegalStateException e) {
            OutputView.printErrorMessage(e);
            play();
        }
    }

    private void executeCommand() {
        do {
            CommandRequestDto commandRequestDto = InputView.requestCommand();
            commandsAndExecutions.get(commandRequestDto.getCommand()).accept(commandRequestDto);
        } while (chessService.isOngoing());
    }

    private void start() {
        chessService.start();
        OutputView.printChessBoard(chessService.toBoardDto());
    }

    private void end() {
        chessService.end();
    }

    private void move(CommandRequestDto commandRequestDto) {
        chessService.move(commandRequestDto);
        OutputView.printChessBoard(chessService.toBoardDto());
    }

    private void fetchScore() {
         OutputView.printScore(chessService.toScoreDto());
    }
}

