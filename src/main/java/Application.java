import static domain.chess.game.EndCommand.END_COMMAND;

import domain.Position;
import domain.chess.game.ChessGame;
import domain.chess.game.Command;
import domain.chess.piece.Piece;
import java.util.List;
import view.InputView;
import view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView.printGuide();
        Command startOrEnd = InputView.readStartOrEnd();
        if (isEndCommand(startOrEnd)) {
            return;
        }
        ChessGame chessGame = new ChessGame();
        List<Piece> piecesOnBoard = chessGame.getPiecesOnBoard();
        OutputView.printChessBoard(piecesOnBoard);

        Command endOrMove = InputView.readEndOrMove();
        while (!isEndCommand(endOrMove)) {
            playGame(endOrMove, chessGame);
            endOrMove = InputView.readEndOrMove();
        }
    }

    private static boolean isEndCommand(Command startOrEnd) {
        return startOrEnd.equals(END_COMMAND);
    }

    private static void playGame(Command moveCommand, ChessGame chessGame) {
        List<Position> options = moveCommand.getOptions();
        Position from = options.get(0);
        Position to = options.get(1);
        boolean moveSuccess = chessGame.move(from, to);
        List<Piece> piecesOnBoard = chessGame.getPiecesOnBoard();
        OutputView.printChessBoard(piecesOnBoard);
        if (!moveSuccess) {
            OutputView.printReInputGuide();
        }
    }
}
