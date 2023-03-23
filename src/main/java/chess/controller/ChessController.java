package chess.controller;

import chess.controller.command.Command;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.game.ChessGame;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public final class ChessController {

    public static final int RANK_SIZE = 8;

    public void run() {
        OutputView.printWelcomeMessage();
        ChessGame chessGame = new ChessGame();

        while (!chessGame.isGameEnd()) {
            playChessGame(chessGame);
            printChessBoard(chessGame);
        }
    }

    private void playChessGame(final ChessGame game) {
        Command command = getCommand();
        try {
            game.execute(command);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            playChessGame(game);
        }
    }

    private Command getCommand() {
        return repeatInput(InputView::readCommand);
    }

    private <T> T repeatInput(Supplier<T> input) {
        try {
            return input.get();
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return repeatInput(input);
        }
    }

    private void printChessBoard(final ChessGame chessGame) {
        OutputView.printChessBoard(getChessBoardMark(chessGame.getChessBoard()));
    }


    private List<List<String>> getChessBoardMark(final ChessBoard chessBoard) {
        final List<List<String>> currentChessBoardMark = new ArrayList<>();
        final List<Square> squares = Optional.of(chessBoard.getSquares()).orElse(Collections.emptyList());

        for (int rank = 0; rank < squares.size(); rank += RANK_SIZE) {
            currentChessBoardMark.add(getRankMark(squares.subList(rank, rank + RANK_SIZE)));
        }

        Collections.reverse(currentChessBoardMark);
        return currentChessBoardMark;
    }

    private List<String> getRankMark(final List<Square> rank) {
        List<String> currentRank = new ArrayList<>();
        for (Square square : rank) {
            currentRank.add(SquareMark.getMarkBySquare(square));
        }
        return currentRank;
    }
}
