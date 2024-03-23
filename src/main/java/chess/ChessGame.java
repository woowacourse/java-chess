package chess;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import chess.domain.position.MoveInformation;
import chess.util.RetryUtil;
import chess.view.GameStatus;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PieceView;
import chess.view.UserCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void play() {
        UserCommand command = RetryUtil.retryUntilNoException(inputView::readProgressCommand);

        if (!isStart(command)) {
            return;
        }

        Board board = new Board();
        printBoardOutput(board);

        playUntilEnd(board);
    }

    public boolean isStart(UserCommand command) {
        return command.gameStatus().equals(GameStatus.START);
    }

    private void playUntilEnd(Board board) {
        boolean run = true;

        while (run) {
            run = RetryUtil.retryUntilNoException(() -> loopWhileEnd(board));
        }
    }

    private boolean loopWhileEnd(Board board) {
        UserCommand command = RetryUtil.retryUntilNoException(inputView::readMoveCommand);

        if (isEnd(command.gameStatus())) {
            return false;
        }

        MoveInformation moveInformation =
                new MoveInformation(Square.findByName(command.source()), Square.findByName(command.destination()));
        movePiece(board, moveInformation);

        return true;
    }

    private boolean isEnd(GameStatus gameStatus) {
        return gameStatus.equals(GameStatus.END);
    }

    private void movePiece(Board board, MoveInformation moveInformation) {
        board.move(moveInformation.source(), moveInformation.destination());
        printBoardOutput(board);
    }

    private void printBoardOutput(Board board) {
        List<String> output = new ArrayList<>();

        for (Rank rank : Rank.reverse()) {
            output.add(makeRankOutput(rank, board));
        }

        outputView.writeBoard(output);
    }

    private String makeRankOutput(Rank rank, Board board) {
        StringBuilder stringBuilder = new StringBuilder();

        for (File file : File.values()) {
            Square square = Square.of(file, rank);
            Piece piece = board.findPieceBySquare(square);

            String pieceView = PieceView.toView(piece);
            stringBuilder.append(pieceView);
        }

        return stringBuilder.toString();
    }
}
