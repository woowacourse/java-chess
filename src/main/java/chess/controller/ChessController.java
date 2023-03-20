package chess.controller;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Coordinate;
import chess.domain.chessboard.Square;
import chess.util.Retryable;
import chess.view.Command;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ChessController {

    public static final int RANK_SIZE = 8;
    private ChessBoard chessBoard;
    private GameState state = GameState.READY;

    public void run() {
        startGame();
        if (state == GameState.RUNNING) {
            playGame();
        }
    }

    private void startGame() {
        OutputView.printWelcomeMessage();
        if (getCommand() == Command.START) {
            state = GameState.RUNNING;
            chessBoard = new ChessBoard();
            OutputView.printChessBoard(getChessBoardMark(chessBoard));
            return;
        }
        state = GameState.END;
    }

    private Command getCommand() {
        return Retryable.retryWhenException(this::requestCommand);
    }

    private Command requestCommand() {
        final Command command = InputView.readCommand();
        if (state == GameState.READY && command == Command.MOVE) {
            throw new IllegalArgumentException("게임을 시작전에는 말을 움직일 수 없습니다.");
        }

        if (state == GameState.RUNNING && command == Command.START) {
            throw new IllegalArgumentException("게임을 진행 중에는 새로운 게임을 시작할 수 없습니다.");
        }

        return command;
    }

    private void playGame() {
        while (getCommand() != Command.END) {
            movePiece();
        }
        state = GameState.END;
    }

    private void movePiece() {
        try {
            final Coordinate from = Coordinate.of(InputView.getCoordinate());
            final Coordinate to = Coordinate.of(InputView.getCoordinate());
            chessBoard.move(from, to);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
        }
        OutputView.printChessBoard(getChessBoardMark(chessBoard));
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
