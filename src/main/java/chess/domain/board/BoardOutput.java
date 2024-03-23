package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardOutput {
    private final List<List<Piece>> board;

    private BoardOutput(List<List<Piece>> board) {
        this.board = board;
    }

    public static BoardOutput of(Board board) {
        List<List<Piece>> output = new ArrayList<>();

        for (Rank rank : Rank.reversed()) {
            output.add(makeRow(board, rank));
        }
        return new BoardOutput(output);
    }

    private static List<Piece> makeRow(Board board, Rank rank) {
        return File.sorted().stream()
                .map(file -> Square.of(file, rank))
                .map(board::findPieceBySquare)
                .toList();
    }

    public List<List<Piece>> pieces() {
        return board;
    }
}
