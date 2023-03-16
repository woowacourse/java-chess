import chess.ChessBoard;
import chess.ChessGame;
import chess.Position;
import view.InputView;
import view.OutputView;

import java.util.List;

public class chessGameApplication {

    public static void main(String[] args) {
        startPhase();

    }

    private static void startPhase() {
        try {
            InputView.printGameStartMessage();
            ChessBoard chessBoard = ChessBoard.generateChessBoard();
            ChessGame chessGame = new ChessGame(chessBoard);
            OutputView.printChessBoard(chessBoard.getChessBoard());
            executePhase(chessGame);
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            startPhase();
        }
    }

    private static void executePhase(ChessGame chessGame) {
        try {
            List<String> command = InputView.readPlayGameCommand();
            if (command.size() == 1) {
                System.out.println("end");
            } else if (command.size() == 3) {
                move(chessGame, command);
            }
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            executePhase(chessGame);
        }
    }

    private static void move(ChessGame chessGame, List<String> moveCommand) {
        Position sourcePosition = Position.of(moveCommand.get(1));
        Position targetPosition = Position.of(moveCommand.get(2));
//        ChessPiece chessPiece = chessGame.findChessPiece(sourcePosition);
//        List<Position> movablePosition = chessGame.findMovablePosition(chessPiece);
//        chessGame.validateMovablePosition(targetPosition, movablePosition);
        chessGame.moveChessPiece(sourcePosition, targetPosition);
    }
}
