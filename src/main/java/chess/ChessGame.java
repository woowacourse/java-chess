package chess;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.board.dto.MoveCommand;
import chess.domain.square.dto.SquareCreateCommand;
import chess.util.RetryUtil;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PieceView;

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
        String progressCommand = RetryUtil.retryUntilNoException(inputView::readProgressCommand);

        if (!isStartCommand(progressCommand)) {
            return;
        }

        Board board = new Board();
        printBoardOutput(board);

        playUntilEnd(board);
    }

    private boolean isStartCommand(String progressCommand) {
        return progressCommand.equals(InputView.START_COMMAND);
    }

    private void playUntilEnd(Board board) {
        boolean run = true;

        while (run) {
            run = RetryUtil.retryUntilNoException(() -> loopWhileEnd(board));
        }
    }

    private boolean loopWhileEnd(Board board) {
        List<String> command = inputView.readCommand();

        if (command.size() == 1) {
            return false;
        }

        movePiece(board, createMoveCommand(command));

        return true;
    }

    private MoveCommand createMoveCommand(List<String> command) {
        Square source = Square.from(new SquareCreateCommand(command.get(1)));
        Square destination = Square.from(new SquareCreateCommand(command.get(2)));

        return new MoveCommand(source, destination);
    }

    private void movePiece(Board board, MoveCommand moveCommand) {
        board.move(moveCommand.source(), moveCommand.destination());
        printBoardOutput(board);
    }

    private void printBoardOutput(Board board) {
        Map<Square, Piece> boardOutput = board.toBoardOutput().board();
        List<String> output = new ArrayList<>();

        for (Rank rank : Rank.reverse()) {
            output.add(makeRankOutput(rank, boardOutput));
        }

        outputView.writeBoard(output);
    }

    private String makeRankOutput(Rank rank, Map<Square, Piece> boardOutput) {
        StringBuilder stringBuilder = new StringBuilder();

        for (File file : File.values()) {
            Square square = Square.of(file, rank);
            Piece piece = boardOutput.get(square);

            String pieceView = PieceView.toView(piece);
            stringBuilder.append(pieceView);
        }

        return stringBuilder.toString();
    }
}
