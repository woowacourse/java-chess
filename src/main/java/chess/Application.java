package chess;

import chess.controller.ChessBoardDto;
import chess.domain.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        InputView inputView = new InputView(new Scanner(System.in));
        outputView.printGameGuide();
        String command = inputView.readStart();
        ChessGame chessGame = ChessGame.startNewGame(command);
        outputView.printChessBoard(new ChessBoardDto(chessGame.getChessBoard()));
    }
}
