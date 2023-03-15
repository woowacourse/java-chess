package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.util.Retryable;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChessController {

    public void run() {
        startGame();
    }

    private void startGame() {
        OutputView.printWelcomeMessage();
        if (requestCommand()) {
            OutputView.printChessBoard(getChessBoardMark(new ChessBoard()));
        }
    }

    private boolean requestCommand() {
        return Retryable.retryWhenException(InputView::readCommand);
    }

    private List<List<String>> getChessBoardMark(final ChessBoard chessBoard) {
        List<List<String>> currentChessBoardMark = new ArrayList<>();

        List<Square> squares = chessBoard.getSquares();

        for (int rank = 0; rank < squares.size(); rank += 8) {
            currentChessBoardMark.add(getRankMark(squares.subList(rank, rank+8)));

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
