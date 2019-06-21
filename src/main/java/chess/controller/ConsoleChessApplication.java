package chess.controller;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.chess.initializer.Initializer;
import view.InputView;
import view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        Initializer initializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(initializer);
        OutputView.printInitialStatement();

        while (!InputView.getStartOrEnd().equals("end")) {
            OutputView.printCheckBoard(chessBoard);
        }
    }
}
