package chess.controller;

import chess.domain.Board;
import chess.view.CommandDto;

public abstract class Controller {

    protected final MainController mainController;
    protected ControllerState controllerState;

    public Controller(final MainController mainController, final ControllerState initialState) {
        this.mainController = mainController;
        this.controllerState = initialState;
    }

    public abstract Board run(CommandDto commandDto, Board board);

    public void enableState() {
        controllerState = ControllerState.RUNNABLE;
    }
}
