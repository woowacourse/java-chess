package chess.controller;

import chess.database.dao.ChessDao;
import chess.database.dao.Notation;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.factory.BoardFactory;
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
        outputView.printStartMessage();
        playChessGame(chessDao, chessGame);
        chessDao.deleteNotation();
    }

    private void playChessGame(final ChessDao chessDao, final ChessGame chessGame) {
        List<String> inputCommand = inputView.readGameCommand();
        while (!chessGame.isGameOver() && isNotEnd(inputCommand)) {
            playChessRound(chessGame, inputCommand, chessDao);
            if (chessGame.isGameOver()) {
                break;
            }
            inputCommand = inputView.readGameCommand();
        }
    }

    private boolean isNotEnd(final List<String> inputCommand) {
        return !inputCommand.get(COMMAND_INDEX).equals(END_COMMAND);
    }

    private void playChessRound(ChessGame chessGame, List<String> command, ChessDao chessDao) {
        try {
            executeForCommand(chessGame, command, chessDao);
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void executeForCommand(ChessGame chessGame, List<String> command, ChessDao chessDao) {
        if (command.get(COMMAND_INDEX).equals(START_COMMAND)) {
            startGame(chessGame, chessDao);
        }
        if (command.get(COMMAND_INDEX).equals(MOVE_COMMAND)) {
            tryChessMove(chessGame, command, chessDao);
        }
        if (command.get(COMMAND_INDEX).equals(STATUS_COMMAND)) {
            printScore(chessGame);
        }
    }

    private void startGame(final ChessGame chessGame, final ChessDao chessDao) {
        List<Notation> notations = chessDao.readNotation();
        for (Notation notation : notations) {
            chessGame.move(Position.from(notation.getSource()), Position.from(notation.getTarget()));
        }
        outputView.printBoard(chessGame.getBoard());
    }

    private void tryChessMove(final ChessGame chessGame, final List<String> inputCommand, final ChessDao chessDao) {
        Position source = Position.from(inputCommand.get(SELECTED_PIECE));
        Position destination = Position.from(inputCommand.get(DESTINATION));
        chessGame.move(source, destination);
        chessDao.addNotation(source, destination);
        outputView.printBoard(chessGame.getBoard());
    }

    private void printScore(final ChessGame chessGame) {
        outputView.printWin(chessGame.calculateScore());
    }
}
