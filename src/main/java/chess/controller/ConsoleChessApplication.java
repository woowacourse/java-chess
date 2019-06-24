package chess.controller;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.chess.initializer.Initializer;
import chess.domain.geometric.Position;
import view.InputView;
import view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        Initializer initializer = new ChessBoardInitializer();
        ChessBoard chessBoard = new ChessBoard(initializer);
        OutputView.printInitialStatement();

        while (true) {
            try {
                OutputView.printCheckBoard(chessBoard);

                Position source = InputView.getSourcePosition();
                Position target = InputView.getTargetPosition();
                chessBoard.move(source, target);
                OutputView.printCheckBoard(chessBoard);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
