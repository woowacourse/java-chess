package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;

import java.util.ArrayList;
import java.util.List;

public class BoardOutput {
    private final List<List<Piece>> board;

    private BoardOutput(List<List<Piece>> board) {
        this.board = board;
    }

    public static BoardOutput of(Board board) {
        List<List<Piece>> output = new ArrayList<>();

        for (Rank rank : Rank.reverse()) {
            List<Piece> piecesByRank = new ArrayList<>();
            for (File file : File.values()) {
                Square square = Square.of(file, rank);
                Piece piece = board.findPieceBySquare(square);

                piecesByRank.add(piece);
            }
            output.add(piecesByRank);
        }
        return new BoardOutput(output);
    }

    public List<List<Piece>> values() {
        return board;
    }
}
