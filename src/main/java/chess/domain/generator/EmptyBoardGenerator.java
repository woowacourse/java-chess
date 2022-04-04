package chess.domain.generator;

import static java.util.stream.Collectors.toList;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.IntStream;

public class EmptyBoardGenerator implements BoardGenerator {

    private static final int START_INCLUSIVE = 0;
    private static final int END_INCLUSIVE = 8;

    @Override
    public List<List<Piece>> generate() {
        List<List<Piece>> board = IntStream.range(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(ignored -> generatePieces())
                .collect(toList());

        return board;
    }

    private List<Piece> generatePieces() {
        return IntStream.range(START_INCLUSIVE, END_INCLUSIVE)
                .mapToObj(ignored -> new EmptyPiece())
                .collect(toList());
    }
}
