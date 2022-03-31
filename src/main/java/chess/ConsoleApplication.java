package chess;

import chess.controller.ChessController;
import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoardFactory;
import chess.resolver.CommandResolverMapper;
import chess.view.CommandDto;
import chess.view.InputView;
import chess.view.OutputView;

public class ConsoleApplication {

    private static ChessController chessController;

    public static void main(final String[] args) {
        OutputView.printStartMessage();

        final ChessGame chessGame = new ChessGame(ChessBoardFactory.createChessBoard());
        chessController = new ChessController(chessGame);
        while (chessController.canPlay()) {
            playTurn();
        }
    }

    private static void playTurn() {
        try {
            final CommandDto commandDto = InputView.requestCommand();
            CommandResolverMapper.resolve(commandDto, chessController);
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
        }
    }
}
