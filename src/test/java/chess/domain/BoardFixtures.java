package chess.domain;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import java.util.List;
import java.util.stream.IntStream;

public class BoardFixtures {

    public static List<List<Piece>> generateOnlyRookBoard() {
        List<List<Piece>> board = IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());

        board.get(7).set(0, new Rook(Color.WHITE));

        return board;
    }

    private static List<Piece> generatePieces() {
        return IntStream.rangeClosed(0, 7)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }
}
