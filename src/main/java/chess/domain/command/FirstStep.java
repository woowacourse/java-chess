package chess.domain.command;

import chess.controller.ChessController;

import java.util.function.Consumer;

public enum FirstStep {
    START(ChessController::start),
    END(ChessController::end),
    NONE((ChessController::except));

    private final Consumer<ChessController> firstStepAction;

    FirstStep(Consumer<ChessController> firstStepAction) {
        this.firstStepAction = firstStepAction;
    }

    public void accept(ChessController chessController) {
        firstStepAction.accept(chessController);
    }
}