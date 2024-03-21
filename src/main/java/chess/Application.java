package chess;

import static chess.domain.game.EndCommand.END_COMMAND;

import chess.domain.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.Command;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

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
