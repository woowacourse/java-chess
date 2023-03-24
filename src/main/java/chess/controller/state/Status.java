package chess.controller.state;

import chess.controller.PositionParser;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Status extends Running {

    public Status(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        outputView.printStatus(chessGame.calculateWhiteScore(), chessGame.calculateBlackScore());
        outputView.printWinner(chessGame.winner().name());
        return inputCommand(inputView, outputView);
    }
}
