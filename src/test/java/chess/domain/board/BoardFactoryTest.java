package chess.domain.board;

import chess.domain.File;
import chess.domain.Rank;
import chess.domain.Position;
import chess.domain.piece.ColorType;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    private Map<Position, Piece> createExpectedBoard() {
        Map<Position, Piece> expected = new HashMap<>();

        // TODO: 개선하기
        expected.put(Position.of(File.a, Rank.ONE), new Piece(PieceType.ROOK, ColorType.WHITE));
        expected.put(Position.of(File.b, Rank.ONE), new Piece(PieceType.KNIGHT, ColorType.WHITE));
        expected.put(Position.of(File.c, Rank.ONE), new Piece(PieceType.BISHOP, ColorType.WHITE));
        expected.put(Position.of(File.d, Rank.ONE), new Piece(PieceType.QUEEN, ColorType.WHITE));
        expected.put(Position.of(File.e, Rank.ONE), new Piece(PieceType.KING, ColorType.WHITE));
        expected.put(Position.of(File.f, Rank.ONE), new Piece(PieceType.BISHOP, ColorType.WHITE));
        expected.put(Position.of(File.g, Rank.ONE), new Piece(PieceType.KNIGHT, ColorType.WHITE));
        expected.put(Position.of(File.h, Rank.ONE), new Piece(PieceType.ROOK, ColorType.WHITE));

        expected.put(Position.of(File.a, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.b, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.c, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.d, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.e, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.f, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.g, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));
        expected.put(Position.of(File.h, Rank.TWO), new Piece(PieceType.PAWN, ColorType.WHITE));

        expected.put(Position.of(File.a, Rank.EIGHT), new Piece(PieceType.ROOK, ColorType.BLACK));
        expected.put(Position.of(File.b, Rank.EIGHT), new Piece(PieceType.KNIGHT, ColorType.BLACK));
        expected.put(Position.of(File.c, Rank.EIGHT), new Piece(PieceType.BISHOP, ColorType.BLACK));
        expected.put(Position.of(File.d, Rank.EIGHT), new Piece(PieceType.QUEEN, ColorType.BLACK));
        expected.put(Position.of(File.e, Rank.EIGHT), new Piece(PieceType.KING, ColorType.BLACK));
        expected.put(Position.of(File.f, Rank.EIGHT), new Piece(PieceType.BISHOP, ColorType.BLACK));
        expected.put(Position.of(File.g, Rank.EIGHT), new Piece(PieceType.KNIGHT, ColorType.BLACK));
        expected.put(Position.of(File.h, Rank.EIGHT), new Piece(PieceType.ROOK, ColorType.BLACK));

        expected.put(Position.of(File.a, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.b, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.c, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.d, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.e, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.f, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.g, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));
        expected.put(Position.of(File.h, Rank.SEVEN), new Piece(PieceType.PAWN, ColorType.BLACK));

        for (Rank rank : Arrays.copyOfRange(Rank.values(), 2, 6)) {
            System.out.println(rank);
            for (File file : File.values()) {
                expected.put(Position.of(file, rank), new Piece(PieceType.EMPTY, ColorType.EMPTY));
            }
        }

        return expected;
    }
}
