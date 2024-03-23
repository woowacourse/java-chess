package chess.domain.board;

import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Square;
import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 생성")
public class BoardFactoryTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void createBoard() {
        // given
        Map<Square, Piece> expected = createExpectedBoard();
        BoardFactory boardFactory = new BoardFactory();

        // when
        Map<Square, Piece> actual = boardFactory.create();

        // then
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private Map<Square, Piece> createExpectedBoard() {
        Map<Square, Piece> expected = new HashMap<>();
        List<File> files = File.sorted();
        List<PieceType> pieces = List.of(PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN,
                PieceType.KING, PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK);

        IntStream.range(0, 8)
                .forEach(i -> expected.put(Square.of(files.get(i), Rank.ONE), new Piece(pieces.get(i), ColorType.WHITE)));

        IntStream.range(0, 8)
                .forEach(i -> expected.put(Square.of(files.get(i), Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE)));

        IntStream.range(0, 8)
                .forEach(i -> expected.put(Square.of(files.get(i), Rank.EIGHT), new Piece(pieces.get(i), ColorType.BLACK)));

        IntStream.range(0, 8)
                .forEach(i -> expected.put(Square.of(files.get(i), Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK)));

        for (Rank rank : Rank.sorted().subList(2, 6)) {
            for (File file : File.sorted()) {
                expected.put(Square.of(file, rank), new Piece(PieceType.EMPTY, ColorType.EMPTY));
            }
        }

        return expected;
    }
}
