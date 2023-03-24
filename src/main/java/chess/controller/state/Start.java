package chess.controller.state;

import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Start extends Running {

    public Start() {
        super(new ChessGame());
    }

    public Start(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        outputView.printBoard(new BoardDTO(chessGame.getBoard()));
        return inputCommand(inputView, outputView);
    }
}
