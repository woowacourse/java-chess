package chess.controller;

import chess.database.dao.ChessDao;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
import chess.service.ChessGameService;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.List;

public class ChessGameController {

    private static final String END_COMMAND = "end";
    private static final String STATUS_COMMAND = "status";
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final int COMMAND_INDEX = 0;
    private static final int SELECTED_PIECE = 1;
    private static final int DESTINATION = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGameController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessDao chessDao = new ChessDao();
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard());
        ChessGameService chessGameService = new ChessGameService(chessGame, chessDao);
        outputView.printStartMessage();
        playChessGame(chessGameService);
    }

    private void playChessGame(final ChessGameService chessGameService) {
        List<String> inputCommand = inputView.readGameCommand();
        while (!chessGameService.gameOver() && isNotEnd(inputCommand)) {
            playChessRound(chessGameService, inputCommand);
            if (chessGameService.gameOver()) {
                break;
            }
            inputCommand = inputView.readGameCommand();
        }
    }

    private boolean isNotEnd(final List<String> inputCommand) {
        return !inputCommand.get(COMMAND_INDEX).equals(END_COMMAND);
    }

    private void playChessRound(final ChessGameService chessGameService, final List<String> command) {
        try {
            executeForCommand(chessGameService, command);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void executeForCommand(final ChessGameService chessGameService, final List<String> command) {
        if (START_COMMAND.equals(command.get(COMMAND_INDEX))) {
            startGame(chessGameService);
        }
        if (MOVE_COMMAND.equals(command.get(COMMAND_INDEX))) {
            tryChessMove(chessGameService, command);
        }
        if (STATUS_COMMAND.equals(command.get(COMMAND_INDEX))) {
            printScore(chessGameService);
        }
    }

    private void startGame(final ChessGameService chessGameService) {
        ChessGame chessGame = chessGameService.loadGame();
        outputView.printBoard(chessGame.getBoard());
    }

    private void tryChessMove(final ChessGameService chessGameService, final List<String> command) {
        Position source = Position.from(command.get(SELECTED_PIECE));
        Position destination = Position.from(command.get(DESTINATION));
        ChessGame chessGame = chessGameService.movePiece(source, destination);
        outputView.printBoard(chessGame.getBoard());
    }

    private void printScore(final ChessGameService chessGameService) {
        outputView.printWin(chessGameService.getScore());
    }
}
