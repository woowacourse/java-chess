package chess.controller.state;

import chess.controller.PositionParser;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;

public class Move extends Running {

    private final String firstArgument;
    private final String secondArgument;

    public Move(ChessGame chessGame, String firstArgument, String secondArgument) {
        super(chessGame);
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
    }

    @Override
    public State execute(InputView inputView, OutputView outputView) {
        chessGame.playTurn(PositionParser.parse(firstArgument), PositionParser.parse(secondArgument));
        outputView.printBoard(new BoardDTO(chessGame.getBoard()));

        return inputCommand(inputView, outputView);
    }
}
