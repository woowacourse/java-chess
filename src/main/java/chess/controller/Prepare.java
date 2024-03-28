package chess.controller;

import chess.model.board.ChessBoard;
import chess.model.board.ChessBoardGenerator;
import chess.model.board.ChessBoardInitializer;
import chess.model.board.Turn;
import chess.model.piece.Side;
import chess.view.input.GameCommand;
import chess.view.input.InputView;
import chess.view.output.OutputView;

public class Prepare implements GameState {

    @Override
    public GameState run(InputView inputView, OutputView outputView) {
        GameCommand gameCommand = getFirstGameCommand(inputView);
        if (gameCommand.isEnd()) {
            return new End();
        }
        ChessBoardGenerator chessBoardInitializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(chessBoardInitializer.create());
        outputView.printChessBoard(chessBoard);
        return new Run(chessBoard, Turn.from(Side.WHITE));
    }

    private GameCommand getFirstGameCommand(InputView inputView) {
        String gameCommandInput = inputView.readGameCommand();
        return GameCommand.createInPreparation(gameCommandInput);
    }

    @Override
    public boolean canContinue() {
        return true;
    }
}
