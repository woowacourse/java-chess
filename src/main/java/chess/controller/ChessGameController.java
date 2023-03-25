package chess.controller;

import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final String END_COMMAND = "end";
    private static final int COMMAND_INDEX = 0;
    private static final String MOVE_COMMAND = "move";
    private static final int SELECTED_PIECE = 1;
    private static final int DESTINATION = 2;
    private static final String START_COMMAND = "start";

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());

        outputView.printStartMessage();

        List<String> inputCommand = inputView.readGameCommand();

        playChess(chessGame, inputCommand);
    }

    private void playChess(ChessGame chessGame, List<String> inputCommand) {
        while (isNotEnd(inputCommand) && !chessGame.isGameOver()) {
            try {
                chessGame = createNewChessGame(chessGame, inputCommand);
                tryChessMove(chessGame, inputCommand);
                outputView.printBoard(chessGame.getBoard());
                if (chessGame.isGameOver()) {
                    break;
                }
                inputCommand = inputView.readGameCommand();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
                inputCommand = inputView.readGameCommand();
            }
        }
    }

    private boolean isNotEnd(final List<String> inputCommand) {
        return !inputCommand.get(COMMAND_INDEX).equals(END_COMMAND);
    }

    private void tryChessMove(final ChessGame chessGame, final List<String> inputCommand) {
        if (inputCommand.get(COMMAND_INDEX).startsWith(MOVE_COMMAND)) {
            Position source = Position.from(inputCommand.get(SELECTED_PIECE));
            Position destination = Position.from(inputCommand.get(DESTINATION));
            chessGame.move(source, destination);
        }
    }

    private ChessGame createNewChessGame(ChessGame chessGame, final List<String> inputCommand) {
        if (inputCommand.get(COMMAND_INDEX).equals(START_COMMAND)) {
            chessGame = new ChessGame(BoardFactory.createBoard());
        }
        return chessGame;
    }
}
