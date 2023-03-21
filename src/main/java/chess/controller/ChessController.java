package chess.controller;

import chess.domain.ChessGame;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public final class ChessController {

    public static final int RANK_SIZE = 8;

    public void run() {
        OutputView.printWelcomeMessage();
        ChessGame chessGame = new ChessGame();

        do {
            checkException(this::playGame, chessGame);
        } while (chessGame.isNotEnd());
    }

    private <T> void checkException(Consumer<T> consumer, T parameter) {
        try {
            consumer.accept(parameter);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private void playGame(ChessGame chessGame) {
        Command command = InputView.readCommand();
        processCommand(chessGame, command);
    }

    private void processCommand(ChessGame chessGame, Command command) {
        if (command == Command.START) {
            startGame(chessGame);
        }
        if (command == Command.MOVE) {
            movePiece(chessGame);
        }
        if (command == Command.END) {
            chessGame.end();
        }
    }

    private void startGame(ChessGame chessGame) {
        chessGame.start();
        OutputView.printChessBoard(getChessBoardMark(chessGame.getChessBoard()));
    }

    private void movePiece(ChessGame chessGame) {
        final Coordinate from = Coordinate.of(InputView.getCoordinate());
        final Coordinate to = Coordinate.of(InputView.getCoordinate());

        chessGame.move(from, to);
        OutputView.printChessBoard(getChessBoardMark(chessGame.getChessBoard()));
    }

    private List<List<String>> getChessBoardMark(final ChessBoard chessBoard) {
        final List<List<String>> currentChessBoardMark = new ArrayList<>();
        final List<Square> squares = chessBoard.getSquares();

        for (int rank = 0; rank < squares.size(); rank += RANK_SIZE) {
            currentChessBoardMark.add(getRankMark(squares.subList(rank, rank + RANK_SIZE)));
        }

        Collections.reverse(currentChessBoardMark);
        return currentChessBoardMark;
    }

    private static List<String> getRankMark(final List<Square> rank) {
        List<String> currentRank = new ArrayList<>();
        for (Square square : rank) {
            currentRank.add(SquareMark.getMarkBySquare(square));
        }
        return currentRank;
    }
}
