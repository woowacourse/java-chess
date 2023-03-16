package chess.controller;

import chess.domain.ChessGame;
import chess.domain.PiecesPosition;
import chess.domain.position.Position;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.ViewFile;
import chess.view.ViewRank;
import java.util.List;
import java.util.function.Supplier;

public class ChessController {

    private static final int COMMAND_INDEX = 0;

    private final OutputView outputView;
    private final InputView inputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        ChessCommand chessCommand = retryOnInvalidUserInput(() -> {
            List<String> strings = inputView.readCommand();
            return ChessCommand.getStart(strings.get(COMMAND_INDEX));
        });

        ChessGame chessGame = startChessGame();
        play(chessGame, chessCommand);
    }

    private ChessGame startChessGame() {
        PiecesPosition piecesPosition = new PiecesPosition();
        printBoard(piecesPosition);

        return new ChessGame(piecesPosition);
    }

    private void play(ChessGame chessGame, ChessCommand chessCommand) {

        while (!chessCommand.isEnd()) {
            List<String> commands = retryOnInvalidUserInput(() -> {
                List<String> strings = inputView.readCommand();
                ChessCommand.from(strings.get(COMMAND_INDEX));
                return strings;
            });

            chessCommand = ChessCommand.from(commands.get(COMMAND_INDEX));
            if (chessCommand.isEnd()) {
                break;
            }

            move(chessGame, commands.get(1), commands.get(2));
        }
    }

    private void move(ChessGame chessGame, String from, String to) {
        Position fromPosition = getPosition(from);
        Position toPosition = getPosition(to);
        System.out.println(fromPosition);
        System.out.println(toPosition);
//        chessGame.move(fromPosition, toPosition);
    }

    private Position getPosition(String positionInput) {
        String fileInput = String.valueOf(positionInput.charAt(0));
        String rankInput = String.valueOf(positionInput.charAt(1));

        return new Position(ViewFile.from(fileInput), ViewRank.from(rankInput));
    }

    public void printBoard(PiecesPosition piecesPosition) {
        outputView.printChessState(piecesPosition.getPiecesPosition());
    }

    private <T> T retryOnInvalidUserInput(Supplier<T> request) {
        try {
            return request.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return retryOnInvalidUserInput(request);
        }
    }
}
