package chess.domain.board;

import chess.TestPiecesFactory;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.maker.PiecesFactory;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static chess.domain.Color.BLACK;
import static chess.domain.Color.WHITE;
import static chess.domain.File.*;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class BoardTest {

    @Test
    void 초기_체스판이_정상적으로_생성된다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Pawn(A, SEVEN, BLACK),
                new Rook(A, EIGHT, BLACK),

                new Pawn(A, TWO, WHITE),
                new Rook(A, ONE, WHITE)
        ));
        final Board board = Board.createBoardWith(piecesFactory);
        final List<Piece> pieces = board.getPieces();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(A, SEVEN), BLACK, Pawn.class),
                        tuple(new Position(A, EIGHT), BLACK, Rook.class),

                        tuple(new Position(A, TWO), WHITE, Pawn.class),
                        tuple(new Position(A, ONE), WHITE, Rook.class)
                );
    }

    @Test
    void 말을_원하는_위치로_이동시킨다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        board.move(new Position(D, EIGHT), new Position(D, FIVE));

        final List<Piece> pieces = board.getPieces();
        final Piece queen = pieces.get(0);

        assertThat(queen.getPosition()).isEqualTo(new Position(D, FIVE));
    }

    @Test
    void 다른_색_말을_잡는다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, FIVE, WHITE)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        board.move(new Position(D, EIGHT), new Position(D, FIVE));
        final List<Piece> pieces = board.getPieces();
        final Piece queen = pieces.get(0);

        assertSoftly(softly -> {
            softly.assertThat(pieces.size()).isEqualTo(1);
            softly.assertThat(queen).isInstanceOf(Queen.class);
            softly.assertThat(queen.getPosition()).isEqualTo(new Position(D, FIVE));
        });
    }

    @Test
    void 현재_위치에_말이_없다면_예외가_발생한다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of());
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    void 목표_위치로_이동할_수_없다면_예외가_발생한다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    void 이동_경로에_말이_있다면_예외가_발생한다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, SEVEN, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 있습니다.");
    }

    @Test
    void 목표_위치에_같은_색_말이_있다면_예외가_발생한다() {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, SEVEN, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        assertThatThrownBy(() -> board.move(new Position(D, EIGHT), new Position(D, SEVEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"BLACK, true", "WHITE, false"})
    void 같은_색인지_확인한다(final Color color, final boolean expected) {
        final PiecesFactory piecesFactory = new TestPiecesFactory(List.of(
                new Queen(D, EIGHT, BLACK)
        ));
        final Board board = Board.createBoardWith(piecesFactory);

        final boolean actual = board.isSameColor(new Position(D, EIGHT), color);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("providePieces")
    void 빈_보드인지_확인한다(final List<Piece> pieces, final boolean expected) {
        final Board board = Board.createBoardWith(new TestPiecesFactory(pieces));

        final boolean actual = board.hasPieces();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePieces() {
        return Stream.of(
                Arguments.of(List.of(), false),
                Arguments.of(List.of(new Queen(D, EIGHT, BLACK)), true)
        );
    }
}
