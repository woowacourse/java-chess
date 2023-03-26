package chess.controller;

import chess.controller.mapper.request.ChessGameCommandMapper;
import chess.domain.ChessGameService;
import chess.view.output.GameResultFormatter;
import chess.view.output.ChessBoardStateFormatter;
import chess.domain.game.command.ChessGameCommand;
import chess.domain.game.result.GameResult;
import chess.domain.game.state.ChessGame;
import chess.domain.game.state.ReadyGame;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;
import java.util.Map;

public final class ChessController {

    private final OutputView outputView;
    private final InputView inputView;
    private final ChessGameService gameService;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.gameService = new ChessGameService();
    }

    public void run() {
        outputView.printStartPrefix();
        ChessGame chessGame = new ReadyGame();
        if (gameService.isGameExist()) {
            chessGame = requestNewGame();
            printChessGameResult(chessGame);
        }

        do {
            chessGame = play(chessGame);
            printChessGameResult(chessGame);
        } while (chessGame.isRunnableGame());

        printResult(chessGame);
    }

    private ChessGame requestNewGame() {
        String command = inputView.readLoadCommand();
        if (command.equals("y")) {
            return gameService.getExistGame();
        }

        return new ReadyGame().startGame();
    }

    private ChessGame play(ChessGame chessGame) {
        try {
            List<String> commandInputs = inputView.readCommands();
            return playByCommand(chessGame, commandInputs);
        } catch (IllegalArgumentException | IllegalStateException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return play(chessGame);
        }
    }

    private ChessGame playByCommand(ChessGame chessGame, List<String> commandInputs) {
        ChessGameCommand command =
                ChessGameCommandMapper.convertToChessGameCommand(commandInputs);
        return command.execute(chessGame);
    }

    private void printChessGameResult(ChessGame chessGame) {
        Map<Position, Piece> piecesPosition = chessGame.getPiecesPosition();
        List<List<String>> consoleViewBoard =
                ChessBoardStateFormatter.convertToConsoleViewBoard(piecesPosition);

        outputView.printChessState(consoleViewBoard);
    }

    private void printResult(ChessGame chessGame) {
        GameResult gameResult = chessGame.calculateResult();
        List<String> results =
                GameResultFormatter.convertToGameResult(gameResult);

        outputView.printResult(results);
    }
}
