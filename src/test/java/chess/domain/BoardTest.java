package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Board board;

    static Stream<Arguments> pieceDummy() {
        return Stream.of(
                // 폰을 제외한 백의 기물
                Arguments.arguments(File.A, Rank.ONE, Role.ROOK, Side.from(Color.WHITE)),
                Arguments.arguments(File.B, Rank.ONE, Role.KNIGHT, Side.from(Color.WHITE)),
                Arguments.arguments(File.C, Rank.ONE, Role.BISHOP, Side.from(Color.WHITE)),
                Arguments.arguments(File.D, Rank.ONE, Role.QUEEN, Side.from(Color.WHITE)),
                Arguments.arguments(File.E, Rank.ONE, Role.KING, Side.from(Color.WHITE)),
                Arguments.arguments(File.F, Rank.ONE, Role.BISHOP, Side.from(Color.WHITE)),
                Arguments.arguments(File.G, Rank.ONE, Role.KNIGHT, Side.from(Color.WHITE)),
                Arguments.arguments(File.H, Rank.ONE, Role.ROOK, Side.from(Color.WHITE)),
                // 백의 폰
                Arguments.arguments(File.A, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.B, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.C, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.D, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.E, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.F, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.G, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                Arguments.arguments(File.H, Rank.TWO, Role.INITIAL_PAWN, Side.from(Color.WHITE)),
                // 흑의 폰
                Arguments.arguments(File.A, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.B, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.C, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.D, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.E, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.F, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.G, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                Arguments.arguments(File.H, Rank.SEVEN, Role.INITIAL_PAWN, Side.from(Color.BLACK)),
                // 폰을 제외한 흑의 기물
                Arguments.arguments(File.A, Rank.EIGHT, Role.ROOK, Side.from(Color.BLACK)),
                Arguments.arguments(File.B, Rank.EIGHT, Role.KNIGHT, Side.from(Color.BLACK)),
                Arguments.arguments(File.C, Rank.EIGHT, Role.BISHOP, Side.from(Color.BLACK)),
                Arguments.arguments(File.D, Rank.EIGHT, Role.QUEEN, Side.from(Color.BLACK)),
                Arguments.arguments(File.E, Rank.EIGHT, Role.KING, Side.from(Color.BLACK)),
                Arguments.arguments(File.F, Rank.EIGHT, Role.BISHOP, Side.from(Color.BLACK)),
                Arguments.arguments(File.G, Rank.EIGHT, Role.KNIGHT, Side.from(Color.BLACK)),
                Arguments.arguments(File.H, Rank.EIGHT, Role.ROOK, Side.from(Color.BLACK))
        );
    }

    static Stream<Arguments> squareDummy() {
        return Stream.of(
                Arguments.of(File.A, Rank.TWO, File.A, Rank.FOUR, Role.PAWN),
                Arguments.of(File.B, Rank.ONE, File.C, Rank.THREE, Role.KNIGHT)
        );
    }

    @BeforeEach
    void setup() {
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

    @Test
    @DisplayName("내 말이 아닌 경우 예외를 발생한다.")
    void moveExceptionWhenIsNotTurn() {
        // given
        Square sourceSquare = Square.of(File.A, Rank.SEVEN);
        Square targetSquare = Square.of(File.A, Rank.FIVE);

        // expected
        assertThatThrownBy(() -> board.makeMove(sourceSquare, targetSquare))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("같은 위치로 이동할 경우 예외가 발생한다.")
    void moveExceptionWhenSameSquare() {
        // given
        Square sourceSquare = Square.of(File.A, Rank.TWO);
        Square targetSquare = Square.of(File.A, Rank.TWO);

        // expected
        assertThatThrownBy(() -> board.makeMove(sourceSquare, targetSquare))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동하려는 말이 없을 경우 예외가 발생한다.")
    void moveExceptionWhenEmptySquare() {
        // given
        Square sourceSquare = Square.of(File.A, Rank.FIVE);
        Square targetSquare = Square.of(File.A, Rank.SEVEN);

        // expected
        assertThatThrownBy(() -> board.makeMove(sourceSquare, targetSquare))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("막혀있는 경우 예외가 발생한다.")
    void moveExceptionWhenIsNotEmptyPath() {
        // given
        Square sourceSquare = Square.of(File.A, Rank.ONE);
        Square targetSquare = Square.of(File.A, Rank.FIVE);

        // expected
        assertThatThrownBy(() -> board.makeMove(sourceSquare, targetSquare))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("이동하려는 위치에 같은 진영 말이 있을 경우 예외가 발생한다.")
    void moveExceptionWhenIsSameSide() {
        // given
        Square sourceSquare = Square.of(File.C, Rank.ONE);
        Square targetSquare = Square.of(File.B, Rank.TWO);

        // expected
        assertThatThrownBy(() -> board.makeMove(sourceSquare, targetSquare))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("squareDummy")
    @DisplayName("체스판의 말을 움직인다.")
    void move(final File sourceFile, final Rank sourceRank,
              final File targetFile, final Rank targetRank,
              final Role expectedRole) {
        Square sourceSquare = Square.of(sourceFile, sourceRank);
        Square targetSquare = Square.of(targetFile, targetRank);

        board.makeMove(sourceSquare, targetSquare);

        Piece piece = board.findPiece(targetFile, targetRank);

        assertThat(piece.getRole()).isEqualTo(expectedRole);
    }
}
