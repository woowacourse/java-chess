package chess.controller;

import java.util.function.Supplier;

import chess.controller.mapper.ExceptionMapper;
import chess.domain.exception.ChessGameException;
import chess.view.OutputView;

public class ChessGameExceptionHandler {

    private static final int LOOP_MAX = 10000;

    private final OutputView outputView;

    public ChessGameExceptionHandler(OutputView outputView) {
        this.outputView = outputView;
    }

    public <T> T handleExceptionByRepeating(Supplier<T> supplier) {
        return handleExceptionByRepeating(supplier, 0);
    }

    public void handleExceptionByRepeating(Runnable runnable) {
        handleExceptionByRepeating(runnable, 0);
    }

    private <T> T handleExceptionByRepeating(Supplier<T> supplier, int loopCount) {
        if (loopCount > LOOP_MAX) {
            throw new IllegalStateException("재입력 가능 횟수를 초과했습니다");
        }
        try {
            return supplier.get();
        } catch (ChessGameException exception) {
            outputView.printException(ExceptionMapper.map(exception));
            return handleExceptionByRepeating(supplier, loopCount + 1);
        }
    }

    private void handleExceptionByRepeating(Runnable runnable, int loopCount) {
        if (loopCount > LOOP_MAX) {
            throw new IllegalStateException("재입력 가능 횟수를 초과했습니다");
        }
        try {
            runnable.run();
        } catch (ChessGameException exception) {
            outputView.printException(ExceptionMapper.map(exception));
            handleExceptionByRepeating(runnable, loopCount + 1);
        }
    }
}
