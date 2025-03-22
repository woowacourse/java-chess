package chess;

import chess.gameboard.GameBoard;
import chess.request.StartPosAndEndPos;
import chess.view.GameView;

public class ChessApp {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        do {
            StartPosAndEndPos moveData = GameView.askMove();
            gameBoard.move(moveData);
            GameView.printBoard(gameBoard);
        } while (GameView.doYouWantToPlayMore());
    }
}
