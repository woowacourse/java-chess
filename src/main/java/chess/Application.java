package chess;

import chess.domain.ChessGame;
import chess.domain.command.MoveCommand;
import chess.domain.piece.PieceColor;
import chess.domain.piece.PieceFactory;
import chess.dto.StatusDto;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {

    public static void main(String[] args) {
        OutputView.printGameStartMessage();
        Command command = getFirstCommand();
        if (command == Command.START) {
            startChessGame();
        }
    }

    private static Command getFirstCommand() {
        try {
            return Command.of(InputView.getFirstCommand());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return getFirstCommand();
        }
    }

    private static void startChessGame() {
        ChessGame chessGame = new ChessGame(PieceFactory.createChessPieces(), PieceColor.WHITE);
        OutputView.printBoard(chessGame.getPieces());

        while (chessGame.isRunning()) {
            OutputView.printPlayingCommandMessage();
            String commandValue = InputView.getCommand();
            Command command = Command.of(commandValue);
            if (command == Command.MOVE) {
                playTurn(commandValue, chessGame);
            }
            if (command == Command.STATUS) {
                showStatus(chessGame);
            }
            if (command == Command.END) {
                return;
            }
        }
    }

    private static void playTurn(String command, ChessGame chessGame) {
        try {
            chessGame.proceedWith(MoveCommand.of(command));
            OutputView.printBoard(chessGame.getPieces());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private static void showStatus(ChessGame chessGame) {
        OutputView.printStatus(new StatusDto(chessGame.calculateScoreByColor()));
    }
}
