import chess.ChessBoard;
import chess.ChessGame;
import chess.piece.Side;
import chess.position.MovablePosition;
import chess.position.Position;
import java.util.List;
import view.InputView;
import view.OutputView;

public class chessGameApplication {

    private static final int MOVE_COMMAND_SIZE = 3;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;

    public static void main(String[] args) {
        ChessBoard chessBoard = ChessBoard.generateChessBoard();
        ChessGame chessGame = new ChessGame(chessBoard);
        startPhase(chessBoard);
        while (true) {
            if (!commandPhase(chessGame, chessBoard, Side.WHITE)) {
                break;
            }
            if (!commandPhase(chessGame, chessBoard, Side.BLACK)) {
                break;
            }
        }
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

    private static boolean commandPhase(ChessGame chessGame, ChessBoard chessBoard, Side side) {
        try {
            List<String> command = InputView.readPlayGameCommand();
            if (command.size() == MOVE_COMMAND_SIZE) {
                move(chessGame, chessBoard, command, side);
                OutputView.printChessBoard(chessBoard.getChessBoard());
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
            commandPhase(chessGame, chessBoard, side);
            return true;
        }
    }

    private static void move(ChessGame chessGame, ChessBoard chessBoard, List<String> moveCommand, Side side) {
        Position sourcePosition = Position.of(moveCommand.get(SOURCE_POSITION_INDEX));
        if (!chessBoard.getChessPieceByPosition(sourcePosition).getSide().equals(side)) {
            throw new IllegalArgumentException("[ERROR] " + side.getSide() + "편의 기물을 움직여 주세요");
        }
        Position targetPosition = Position.of(moveCommand.get(TARGET_POSITION_INDEX));
        MovablePosition movablePosition = new MovablePosition();
        List<Position> movableRoute = movablePosition.findByShape(chessBoard, sourcePosition);
        if (chessGame.validateMovablePosition(targetPosition, movableRoute)) {
            chessGame.moveChessPiece(sourcePosition, targetPosition);
        }
    }
}
