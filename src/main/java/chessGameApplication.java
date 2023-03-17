import chess.ChessBoard;
import chess.ChessGame;
import chess.MovablePosition;
import chess.Position;
import view.InputView;
import view.OutputView;

import java.util.List;

public class chessGameApplication {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public static void main(String[] args) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        startPhase(chessBoard);
        while (commandPhase(chessGame, chessBoard));
    }

    private static void startPhase(ChessBoard chessBoard) {
        try {
            InputView.printGameStartMessage();
            OutputView.printChessBoard(chessBoard.getChessBoard());
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            startPhase(chessBoard);
        }
    }

    private static boolean commandPhase(ChessGame chessGame, ChessBoard chessBoard) {
        try {
            List<String> command = InputView.readPlayGameCommand();
            if (command.size() == MOVE_COMMAND_SIZE) {
                move(chessGame, chessBoard, command);
                OutputView.printChessBoard(chessBoard.getChessBoard());
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            commandPhase(chessGame, chessBoard);
            return true;
        }
    }

    private static void move(ChessGame chessGame, ChessBoard chessBoard, List<String> moveCommand) {
        Position sourcePosition = Position.of(moveCommand.get(SOURCE_POSITION_INDEX));
        Position targetPosition = Position.of(moveCommand.get(TARGET_POSITION_INDEX));
        MovablePosition movablePosition = new MovablePosition();
        List<Position> movableRoute = movablePosition.findByShape(chessBoard, sourcePosition);
        if (chessGame.validateMovablePosition(targetPosition, movableRoute)) {
            chessGame.moveChessPiece(sourcePosition, targetPosition);
        }
    }
}
