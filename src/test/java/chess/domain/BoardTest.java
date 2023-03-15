package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    static Board board;

    @BeforeAll
    static void setup() {
        board = BoardFactory.create();
    }

    @ParameterizedTest
    @MethodSource("pieceDummy")
    @DisplayName("초기 세팅 후 위치를 확인한다.")
    void create(final File file, final Rank rank, final Role expectedRole, final Side side) {
        // expected
        Piece piece = board.findPiece(file, rank);

        assertThat(piece).isInstanceOf(expectedRole.create(side).getClass());

    }

    static Stream<Arguments> pieceDummy() {
        return Stream.of(
                // 폰을 제외한 백의 기물
                Arguments.arguments(File.A, Rank.ONE, Role.ROOK, new Side(Color.WHITE)),
                Arguments.arguments(File.B, Rank.ONE, Role.KNIGHT, new Side(Color.WHITE)),
                Arguments.arguments(File.C, Rank.ONE, Role.BISHOP, new Side(Color.WHITE)),
                Arguments.arguments(File.D, Rank.ONE, Role.QUEEN, new Side(Color.WHITE)),
                Arguments.arguments(File.E, Rank.ONE, Role.KING, new Side(Color.WHITE)),
                Arguments.arguments(File.F, Rank.ONE, Role.BISHOP, new Side(Color.WHITE)),
                Arguments.arguments(File.G, Rank.ONE, Role.KNIGHT, new Side(Color.WHITE)),
                Arguments.arguments(File.H, Rank.ONE, Role.ROOK, new Side(Color.WHITE)),
                // 백의 폰
                Arguments.arguments(File.A, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.B, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.C, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.D, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.E, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.F, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.G, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                Arguments.arguments(File.H, Rank.TWO, Role.PAWN, new Side(Color.WHITE)),
                // 흑의 폰
                Arguments.arguments(File.B, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.A, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.C, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.D, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.E, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.F, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.G, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                Arguments.arguments(File.H, Rank.SEVEN, Role.PAWN, new Side(Color.BLACK)),
                // 폰을 제외한 흑의 기물
                Arguments.arguments(File.A, Rank.EIGHT, Role.ROOK, new Side(Color.BLACK)),
                Arguments.arguments(File.B, Rank.EIGHT, Role.KNIGHT, new Side(Color.BLACK)),
                Arguments.arguments(File.C, Rank.EIGHT, Role.BISHOP, new Side(Color.BLACK)),
                Arguments.arguments(File.D, Rank.EIGHT, Role.QUEEN, new Side(Color.BLACK)),
                Arguments.arguments(File.E, Rank.EIGHT, Role.KING, new Side(Color.BLACK)),
                Arguments.arguments(File.F, Rank.EIGHT, Role.BISHOP, new Side(Color.BLACK)),
                Arguments.arguments(File.G, Rank.EIGHT, Role.KNIGHT, new Side(Color.BLACK)),
                Arguments.arguments(File.H, Rank.EIGHT, Role.ROOK, new Side(Color.BLACK))
        );
    }
}
