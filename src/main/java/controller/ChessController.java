package controller;

import domain.GameCommand;
import domain.chessGame.ChessBoard;
import domain.chessGame.ChessBoardGenerator;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public final class ChessController {

    public void run() {
        OutputView.printStartMessage();
        GameCommand gameCommand = InputView.readGameCommand();
        while(!gameCommand.equals(GameCommand.END)) {
            ChessBoardGenerator generator = new ChessBoardGenerator();
            ChessBoard chessBoard = new ChessBoard(generator.generate());
            OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
            gameCommand = InputView.readGameCommand();
        }
    }
}
