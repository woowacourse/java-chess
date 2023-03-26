package chess.controller;

import chess.view.InputView;
import chess.view.OutputView;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ChessGameController {
    private final ChessGameState chessGameState;
    
    public ChessGameController(ChessGameState chessGameState) {
        this.chessGameState = chessGameState;
    }
    
    public void execute() {
        OutputView.noticeGameStart();
        
        while (chessGameState.isRunning()) {
            retryWithError(chessGameState::playWithCurrentTurn);
        }
    }
    
    private void retryWithError(Consumer<List<String>> process) {
        try {
            process.accept(splitedCommand());
        } catch (IllegalArgumentException illegalArgumentException) {
            OutputView.printErrorMessage(illegalArgumentException.getMessage());
            retryWithError(process);
        }
    }
    
    private List<String> splitedCommand() {
        String inputCommand = InputView.repeatAtExceptionCase(InputView::inputCommand);
        return Arrays.stream(inputCommand.split(" "))
                .collect(Collectors.toUnmodifiableList());
    }
}
