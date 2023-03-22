package chess.controller;

import chess.controller.mapper.response.ChessBoardStateMapper;
import chess.controller.mapper.ChessCommandMapper;
import chess.domain.position.PiecesPosition;
import chess.domain.game.ChessGame;
import chess.domain.game.command.ChessGameCommand;
import chess.view.input.InputView;
import chess.view.OutputView;
import java.util.List;

public final class ChessController {

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartPrefix();

        ChessGame chessGame = startChessGame();
        printChessGameBoard(chessGame);
        play(chessGame);
    }

    private ChessGame startChessGame() {
        try {
            List<String> commandInputs = inputView.readCommands();
            PiecesPosition piecesPosition = new PiecesPosition();
            ChessGame chessGame = new ChessGame(piecesPosition);

            ChessGameCommand command = ChessCommandMapper.convertToChessGameCommand(commandInputs);
            command.execute(chessGame);
            return chessGame;
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return startChessGame();
        }
    }

    private void play(ChessGame chessGame) {
        while (chessGame.isRunnableGame()) {
            playTurn(chessGame);
            printChessGameBoard(chessGame);
        }
    }

    private void playTurn(ChessGame chessGame) {
        try {
            List<String> commandInputs = inputView.readCommands();
            ChessGameCommand command = ChessCommandMapper.convertToChessGameCommand(commandInputs);
            command.execute(chessGame);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            playTurn(chessGame);
        }
    }

    private void printChessGameBoard(ChessGame chessGame) {
        PiecesPosition piecesPosition = chessGame.getPiecesPosition();
        List<List<String>> consoleViewBoard = ChessBoardStateMapper.convertToConsoleViewBoard(piecesPosition.getPiecesPosition());
        outputView.printChessState(consoleViewBoard);
    }
}
