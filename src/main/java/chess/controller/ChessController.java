package chess.controller;

import chess.controller.mapper.request.ChessGameCommandMapper;
import chess.controller.mapper.response.ChessBoardStateFormatter;
import chess.domain.game.ChessGame;
import chess.domain.game.command.ChessGameCommand;
import chess.domain.position.ChessBoard;
import chess.view.InputView;
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
        ChessGame chessGame = new ChessGame();
        while (chessGame.isRunnableGame()) {
            play(chessGame);
            printChessGameBoard(chessGame);
        }
    }

    private void play(ChessGame chessGame) {
        try {
            List<String> commandInputs = inputView.readCommands();
            playByCommand(chessGame, commandInputs);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            play(chessGame);
        }
    }

    private void playByCommand(ChessGame chessGame, List<String> commandInputs) {
        ChessGameCommand command = ChessGameCommandMapper.convertToChessGameCommand(commandInputs);
        command.execute(chessGame);
    }

    private void printChessGameBoard(ChessGame chessGame) {
        ChessBoard chessBoard = chessGame.getPiecesPosition();
        List<List<String>> consoleViewBoard =
                ChessBoardStateFormatter.convertToConsoleViewBoard(chessBoard.getPiecesPosition());

        outputView.printChessState(consoleViewBoard);
    }
}
