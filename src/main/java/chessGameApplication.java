import chess.ChessBoard;
import chess.ChessController;
import chess.ChessGame;

public class chessGameApplication {

    public static void main(String[] args) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        ChessController chessController = new ChessController(chessBoard, chessGame);
        chessController.startPhase();
        chessController.commandPhase();
    }
}
