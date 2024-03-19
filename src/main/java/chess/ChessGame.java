package chess;

import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import chess.view.PieceView;

import java.util.ArrayList;
import java.util.Collections;
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
        String progressCommand = inputView.readProgressCommand();
        if (!inputView.isStartCommand(progressCommand)) {
            return;
        }

        Board board = new Board();
        printBoardOutput(board);
    }

    private void printBoardOutput(Board board) {
        Map<Position, Piece> boardOutput = board.toBoardOutput().board();
        List<String> output = new ArrayList<>();

        List<Rank> ranks = reverseRank();
        for (Rank rank : ranks) {
            StringBuilder stringBuilder = new StringBuilder();

            makeRankOutput(rank, stringBuilder, boardOutput);

            output.add(stringBuilder.toString());
        }

        outputView.writeBoard(output);
    }

    private static void makeRankOutput(Rank rank, StringBuilder stringBuilder, Map<Position, Piece> boardOutput) {
        for (File file : File.values()) {
            stringBuilder.append(PieceView.toView(boardOutput.get(Position.of(file, rank))));
        }
    }

    private List<Rank> reverseRank() {
        List<Rank> ranks = new ArrayList<>(List.of(Rank.values()));
        Collections.reverse(ranks);

        return ranks;
    }
}
