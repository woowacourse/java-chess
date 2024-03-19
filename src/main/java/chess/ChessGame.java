package chess;

import chess.domain.position.File;
import chess.domain.position.Square;
import chess.domain.position.Rank;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PieceView;

import java.util.*;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void play() {
        String progressCommand = inputView.readProgressCommand();
        if (!inputView.isStartCommand(progressCommand)) {
            return;
        }

        Board board = new Board();
        printBoardOutput(board);
    }

    private void printBoardOutput(Board board) {
        Map<Square, Piece> boardOutput = board.toBoardOutput().board();
        List<String> output = new ArrayList<>();

        for (Rank rank : Rank.values()) {
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
