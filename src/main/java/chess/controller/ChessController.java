package chess.controller;

import chess.controller.mapper.request.ChessGameCommandMapper;
import chess.controller.mapper.response.ChessBoardStateFormatter;
import chess.domain.game.command.ChessGameCommand;
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

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        outputView.printStartPrefix();

        ChessGame chessGame = new ReadyGame();
        do {
            chessGame = play(chessGame);
            printChessGameBoard(chessGame);
        } while (chessGame.isRunnableGame());
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
        ChessGameCommand command = ChessGameCommandMapper.convertToChessGameCommand(commandInputs);
        return command.execute(chessGame);
    }

    private void printChessGameBoard(ChessGame chessGame) {
        Map<Position, Piece> piecesPosition = chessGame.getPiecesPosition();
        List<List<String>> consoleViewBoard =
                ChessBoardStateFormatter.convertToConsoleViewBoard(piecesPosition);

        outputView.printChessState(consoleViewBoard);
    }
}
