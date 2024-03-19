package chess.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("체스판 생성")
public class BoardFactoryTest {

    @DisplayName("체스판을 생성한다.")
    @Test
    void createBoard() {
        // given
        Map<Position, Piece> expected = createExpectedBoard();
        BoardFactory boardFactory = new BoardFactory();

        // when
        Map<Position, Piece> actual = boardFactory.create();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    private Map<Position, Piece> createExpectedBoard() {
        Map<Position, Piece> expected = new HashMap<>();

        // TODO: 개선하기
        expected.put(new Position('a', 1), new Piece(PieceType.ROOK, ColorType.WHITE));
        expected.put(new Position('b', 1), new Piece(PieceType.KNIGHT, ColorType.WHITE));
        expected.put(new Position('c', 1), new Piece(PieceType.BISHOP, ColorType.WHITE));
        expected.put(new Position('d', 1), new Piece(PieceType.QUEEN, ColorType.WHITE));
        expected.put(new Position('e', 1), new Piece(PieceType.KING, ColorType.WHITE));
        expected.put(new Position('f', 1), new Piece(PieceType.BISHOP, ColorType.WHITE));
        expected.put(new Position('g', 1), new Piece(PieceType.KNIGHT, ColorType.WHITE));
        expected.put(new Position('h', 1), new Piece(PieceType.ROOK, ColorType.WHITE));

        expected.put(new Position('a', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('b', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('c', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('d', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('e', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('f', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('g', 2), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(new Position('h', 2), new Piece(PieceType.PAWN, ColorType.WHITE));

        expected.put(new Position('a', 8), new Piece(PieceType.ROOK, ColorType.BLACK));
        expected.put(new Position('b', 8), new Piece(PieceType.KNIGHT, ColorType.BLACK));
        expected.put(new Position('c', 8), new Piece(PieceType.BISHOP, ColorType.BLACK));
        expected.put(new Position('d', 8), new Piece(PieceType.QUEEN, ColorType.BLACK));
        expected.put(new Position('e', 8), new Piece(PieceType.KING, ColorType.BLACK));
        expected.put(new Position('f', 8), new Piece(PieceType.BISHOP, ColorType.BLACK));
        expected.put(new Position('g', 8), new Piece(PieceType.KNIGHT, ColorType.BLACK));
        expected.put(new Position('h', 8), new Piece(PieceType.ROOK, ColorType.BLACK));

        expected.put(new Position('a', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('b', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('c', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('d', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('e', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('f', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('g', 7), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(new Position('h', 7), new Piece(PieceType.PAWN, ColorType.BLACK));

        for (int rank = 3; rank <= 6; rank++) {
            for (char file = 'a'; file <= 'h'; file++) {
                expected.put(new Position(file, rank), null);
            }
        }

        return expected;
    }
}
