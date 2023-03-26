package chess.domain.board;

import chess.domain.Color;
import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
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
    void 체스판이_정상적으로_생성된다() {
        //given
        final Board board = Board.from(List.of(
                new Pawn(A, SEVEN, BLACK),
                new Pawn(A, TWO, WHITE)
        ));

        //when
        final List<Piece> pieces = board.getPieces();

        //then
        assertThat(pieces)
                .extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(A, SEVEN), BLACK, Pawn.class),
                        tuple(new Position(A, TWO), WHITE, Pawn.class)
                );
    }

    @Test
    void 말을_원하는_위치로_이동시킨다() {
        //given
        final Board board = Board.from(List.of(
                new Queen(D, EIGHT, BLACK)
        ));

        //when
        board.move(BLACK, new Position(D, EIGHT), new Position(D, FIVE));

        //then
        final List<Piece> pieces = board.getPieces();
        final Piece queen = pieces.get(0);
        assertThat(queen.getPosition()).isEqualTo(new Position(D, FIVE));
    }

    @Test
    void 다른_색_말을_잡는다() {
        //given
        final Board board = Board.from(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, FIVE, WHITE)
        ));

        //when
        board.move(BLACK, new Position(D, EIGHT), new Position(D, FIVE));

        //then
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
        //given
        final Board board = Board.from(List.of());

        //when
        //then
        assertThatThrownBy(() -> board.move(BLACK, new Position(D, EIGHT), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 말이 존재하지 않습니다.");
    }

    @Test
    void 목표_위치로_이동할_수_없다면_예외가_발생한다() {
        //given
        final Board board = Board.from(List.of(
                new Queen(D, EIGHT, BLACK)
        ));

        //when
        //then
        assertThatThrownBy(() -> board.move(BLACK, new Position(D, EIGHT), new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @CsvSource({"BLACK, WHITE", "WHITE, BLACK"})
    void 해당_색상_체스말을_움직일_순서가_아니라면_예외가_발생한다(final Color pieceColor, final Color turnColor) {
        //given
        final Board board = Board.from(List.of(
                new Queen(D, EIGHT, pieceColor)
        ));

        //when
        //then
        assertThatThrownBy(() -> board.move(turnColor, new Position(D, EIGHT), new Position(E, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 색의 말을 이동시킬 순서가 아닙니다.");
    }

    @Test
    void 이동_경로에_말이_있다면_예외가_발생한다() {
        //given
        final Board board = Board.from(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, SEVEN, BLACK)
        ));

        //when
        //then
        assertThatThrownBy(() -> board.move(BLACK, new Position(D, EIGHT), new Position(D, FIVE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 말이 있습니다.");
    }

    @Test
    void 목표_위치에_같은_색_말이_있다면_예외가_발생한다() {
        //given
        final Board board = Board.from(List.of(
                new Queen(D, EIGHT, BLACK),
                new Pawn(D, SEVEN, BLACK)
        ));

        //when
        //then
        assertThatThrownBy(() -> board.move(BLACK, new Position(D, EIGHT), new Position(D, SEVEN)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색 말은 잡을 수 없습니다.");
    }

    @ParameterizedTest
    @MethodSource("provideKings")
    void 왕이_두_개인지_확인한다(final List<Piece> pieces, final boolean expected) {
        //given
        final Board board = Board.from(pieces);

        //when
        final boolean actual = board.hasTwoKings();

        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> provideKings() {
        return Stream.of(
                Arguments.of(List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)), true),
                Arguments.of(List.of(), false)
        );
    }


    @ParameterizedTest
    @MethodSource("providePiecesAndScore")
    void 두_진영의_점수를_계산한다(final List<Piece> pieces, final double blackScore, final double whiteScore) {
        //given
        final Board board = Board.from(pieces);

        //when
        Map<Color, Double> scoreByColor = board.calculateScoreByColor();

        //then
        assertSoftly(softly -> {
            softly.assertThat(scoreByColor.get(BLACK)).isEqualTo(blackScore);
            softly.assertThat(scoreByColor.get(WHITE)).isEqualTo(whiteScore);
        });
    }

    private static Stream<Arguments> providePiecesAndScore() {
        return Stream.of(
                Arguments.of(List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)), 0, 0),
                Arguments.of(List.of(new Queen(E, EIGHT, BLACK), new Rook(E, ONE, WHITE)), 9, 5),
                Arguments.of(List.of(new Knight(E, EIGHT, BLACK), new Bishop(E, ONE, WHITE)), 2.5, 3),
                Arguments.of(List.of(new Pawn(E, SEVEN, BLACK), new Pawn(E, SIX, BLACK),
                        new Pawn(E, TWO, WHITE)), 1, 1)
        );
    }
}
