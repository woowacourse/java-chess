package chess.controller;

import chess.domain.chess.ChessGame;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.chess.initializer.Initializer;
import chess.domain.geometric.Position;
import view.InputView;
import view.OutputView;

public class ConsoleChessApplication {

    public static void main(String[] args) {
        Initializer initializer = new ChessBoardInitializer();
        ChessGame chessGame = new ChessGame(initializer);
        OutputView.printInitialStatement();

        while (true) {
            try {
                OutputView.printCheckBoard(chessGame);
                System.out.println("현재 턴은 " + chessGame.getTeam().name() + "입니다.");
                Position source = InputView.getSourcePosition();
                Position target = InputView.getTargetPosition();
                chessGame.move(source, target);
                chessGame.changeTeam();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
