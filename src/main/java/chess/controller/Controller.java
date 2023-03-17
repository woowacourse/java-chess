package chess.controller;

import chess.service.ChessService;
import chess.view.CommandDto;

public abstract class Controller {

    protected final MainController mainController;
    protected ControllerState controllerState;

    public Controller(final MainController mainController, final ControllerState initialState) {
        this.mainController = mainController;
        this.controllerState = initialState;
    }

    public abstract ChessService run(final CommandDto commandDto, final ChessService service);

    public void enableState() {
        controllerState = ControllerState.RUNNABLE;
    }
}
