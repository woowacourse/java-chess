package chess.controller;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.ChessGame;
import chess.domain.point.File;
import chess.domain.point.Point;
import chess.domain.point.Rank;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {

    private static final String COMMAND_END = "end";
    private static final int DEPARTURE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        startGame();
        runGame();
    }

    private void startGame() {
        outputView.printGameStart();
        while (true) {
            try {
                inputView.readStart();
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void runGame() {
        Board board = new Board(BoardFactory.createInitialChessBoard());
        ChessGame game = new ChessGame(board);
        while (true) {
            try {
                outputView.printBoard(board.getBoard());

                String readCommand = inputView.readCommand();
                if (COMMAND_END.equals(readCommand)) {
                    outputView.printGameEnd();
                    return;
                }
                pieceMove(readCommand, game);
            } catch (IllegalStateException e) {
                outputView.printErrorMessage(e.getMessage());
                return;
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void pieceMove(String readCommand, ChessGame game) {
        Point departure;
        Point destination;
        try {
            String[] splitCommands = readCommand.split(" ");
            departure = parsePoint(splitCommands[DEPARTURE_INDEX]);
            destination = parsePoint(splitCommands[DESTINATION_INDEX]);
        } catch (IllegalArgumentException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("잘못된 위치를 입력하였습니다. 입력값 : " + readCommand);
        }
        game.currentTurnPlayerMove(departure, destination);
        game.turnOver();
    }

    private Point parsePoint(String splitCommand) {
        File file = File.of(splitCommand.charAt(FILE_INDEX));
        Rank rank = Rank.of(Integer.parseInt(String.valueOf(splitCommand.charAt(RANK_INDEX))));
        return Point.of(file, rank);
    }
}
