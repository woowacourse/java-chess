package chess;

import chess.controller.ChessGame;
import chess.domain.Board;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printChessGameStartMessage();
        OutputView.printCommandGuideMessage();

        Board board = new Board();
        board.placeInitialPieces();
        ChessGame chessGame = new ChessGame(board);

        chessGame.startGame();
    }
}
