package chess.domain;

import chess.TestPiecesFactory;
import chess.domain.board.maker.EmptyPiecesFactory;
import chess.domain.board.maker.StartingPiecesFactory;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static chess.domain.Color.*;
import static chess.domain.File.A;
import static chess.domain.File.E;
import static chess.domain.Rank.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class ChessGameTest {

    @Test
    void 턴이_바뀌었는지_확인한다() {
        final ChessGame chessGame = ChessGame.from(new StartingPiecesFactory());

        final ChessGame changedChessGame = chessGame.move(new Position(A, TWO), new Position(A, FOUR));

        assertThat(changedChessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    void 입력_받은_현재_위치_말_색상이_이동할_차례가_아니면_예외를_던진다() {
        final ChessGame chessGame = ChessGame.from(new StartingPiecesFactory());

        assertThatThrownBy(() -> chessGame.move(new Position(A, SEVEN), new Position(A, SIX)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 색의 말을 이동시킬 순서가 아닙니다.");
    }

    @Test
    void 빈_체스판을_갖는_체스게임을_생성하였을_때_체스게임이_진행중인지_확인한다() {
        final ChessGame chessGame = ChessGame.from(new EmptyPiecesFactory());

        final boolean actual = chessGame.isPlaying();

        assertThat(actual).isFalse();
    }

    @Test
    void 세팅된_체스판을_갖는_체스게임을_생성하였을_때_체스게임이_진행중인지_확인한다() {
        final ChessGame chessGame = ChessGame.from(new StartingPiecesFactory());

        final boolean actual = chessGame.isPlaying();

        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("providePieces")
    void 게임이_끝났는지_확인한다(final List<Piece> pieces, final boolean expected) {
        final ChessGame chessGame = ChessGame.from(new TestPiecesFactory(pieces));

        final boolean actual = chessGame.isGameOver();

        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> providePieces() {
        return Stream.of(
                Arguments.of(List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)), false),
                Arguments.of(List.of(new King(E, EIGHT, BLACK)), true)
        );
    }

    @ParameterizedTest
    @MethodSource("providePiecesAndScore")
    void 두_진영의_점수를_계산한다(final List<Piece> pieces, final double blackScore, final double whiteScore) {
        final ChessGame chessGame = ChessGame.from(new TestPiecesFactory(pieces));

        Map<Color, Double> scoreByColor = chessGame.calculateScoreByColor();

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

    @ParameterizedTest
    @MethodSource("providePiecesAndScoreWinnerColor")
    void 점수가_높은_색을_반환한다(final List<Piece> pieces, final Color expectedColor) {
        final ChessGame chessGame = ChessGame.from(new TestPiecesFactory(pieces));

        Color actualColor = chessGame.findScoreWinner();

        assertThat(actualColor).isEqualTo(expectedColor);
    }

    private static Stream<Arguments> providePiecesAndScoreWinnerColor() {
        return Stream.of(
                Arguments.of(List.of(new Queen(E, EIGHT, BLACK), new Rook(E, ONE, WHITE)), BLACK),
                Arguments.of(List.of(new Knight(E, EIGHT, BLACK), new Bishop(E, ONE, WHITE)), WHITE)
        );
    }

    @ParameterizedTest
    @MethodSource("providePiecesAndDrawColor")
    void 점수가_같으면_아무것도_아닌_색을_반환한다(final List<Piece> pieces, final Color expectedColor) {
        final ChessGame chessGame = ChessGame.from(new TestPiecesFactory(pieces));

        Color actualColor = chessGame.findScoreWinner();

        assertThat(actualColor).isEqualTo(expectedColor);
    }

    private static Stream<Arguments> providePiecesAndDrawColor() {
        return Stream.of(
                Arguments.of(List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)), NOTHING),
                Arguments.of(List.of(new Pawn(E, SEVEN, BLACK), new Pawn(E, SIX, BLACK),
                        new Pawn(E, TWO, WHITE)), NOTHING)
        );
    }
}
