package chess.controller;

import chess.domain.Board;
import chess.view.OutputView;

public class EndController implements Controller {
    private final OutputView outputView;

    public EndController(OutputView outputView) {
        this.outputView = outputView;
    }

    @Override
    public Board execute(RequestInfo requestInfo, Board board) {
        try {
            validate(requestInfo);
            return board;
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return board;
        }
    }

    public void validate(RequestInfo requestInfo) {
        if (requestInfo.getGameCommand() != GameCommand.END) {
            throw new IllegalArgumentException();
        }
    }
}
