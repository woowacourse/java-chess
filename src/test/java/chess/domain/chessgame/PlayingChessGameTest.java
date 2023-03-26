package chess.domain.chessgame;

import chess.TestPiecesFactory;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.*;
import chess.domain.piecesfactory.StartingPiecesFactory;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
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
import static org.assertj.core.api.AssertionsForClassTypes.tuple;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class PlayingChessGameTest {

    @Test
    void 시작하면_예외가_발생한다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        //then
        assertThatThrownBy(chessGame::start)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("게임이 이미 시작되었습니다.");
    }

    @Test
    void 색깔이_바뀌는지_확인한다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new StartingPiecesFactory()), WHITE);

        //when
        final ChessGame changedChessGame = chessGame.move(new Position(A, TWO), new Position(A, FOUR));

        //then
        assertThat(changedChessGame).extracting("currentTurnColor")
                .isEqualTo(BLACK);
    }

    @Test
    void 게임이_진행중이다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        final boolean actual = chessGame.isPlaying();

        //then
        assertThat(actual).isTrue();
    }

    @Test
    void 게임이_끝이_아니다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        final boolean actual = chessGame.isGameOver();

        //then
        assertThat(actual).isFalse();
    }

    @Test
    void 게임이_끝난다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new StartingPiecesFactory()), Color.BLACK);

        //when
        final ChessGame endChessGame = chessGame.end();

        //then
        final boolean actual = endChessGame.isGameOver();
        assertThat(actual).isTrue();
    }

    @ParameterizedTest
    @MethodSource("providePiecesAndScore")
    void 색깔별_점수를_계산한다(final List<Piece> pieces, final double blackScore, final double whiteScore) {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new TestPiecesFactory(pieces)), Color.BLACK);

        //when
        Map<Color, Double> scoreByColor = chessGame.calculateScoreByColor();

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

    @ParameterizedTest
    @MethodSource("providePiecesAndWinnerColor")
    void 우승_색깔을_찾는다(final List<Piece> pieces, final Color expectedColor) {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new TestPiecesFactory(pieces)), Color.BLACK);

        //when
        Color actualColor = chessGame.findScoreWinner();

        //then
        assertThat(actualColor).isEqualTo(expectedColor);
    }

    private static Stream<Arguments> providePiecesAndWinnerColor() {
        return Stream.of(
                Arguments.of(List.of(new Queen(E, EIGHT, BLACK), new Rook(E, ONE, WHITE)), BLACK),
                Arguments.of(List.of(new Knight(E, EIGHT, BLACK), new Bishop(E, ONE, WHITE)), WHITE)
        );
    }

    @ParameterizedTest
    @MethodSource("provideDrawPieces")
    void 무승부이면_아무것도_아닌_색을_반환한다(final List<Piece> pieces) {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new TestPiecesFactory(pieces)), Color.BLACK);

        //when
        Color actualColor = chessGame.findScoreWinner();

        //then
        assertThat(actualColor).isEqualTo(NOTHING);
    }

    private static Stream<Arguments> provideDrawPieces() {
        return Stream.of(
                Arguments.of(List.of(new King(E, EIGHT, BLACK), new King(E, ONE, WHITE))),
                Arguments.of(List.of(new Pawn(E, SEVEN, BLACK), new Pawn(E, SIX, BLACK),
                        new Pawn(E, TWO, WHITE)))
        );
    }

    @Test
    void 체스말을_꺼낸다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new TestPiecesFactory(List.of(
                new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)))),
                Color.BLACK);

        //when
        final List<Piece> pieces = chessGame.getPieces();

        //then
        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .contains(
                        tuple(new Position(E, EIGHT), BLACK, King.class),
                        tuple(new Position(E, ONE), WHITE, King.class)
                );
    }

    @Test
    void 현재_순서_색깔을_꺼낸다() {
        //given
        final ChessGame chessGame = new PlayingChessGame(Board.from(new TestPiecesFactory(List.of(
                new King(E, EIGHT, BLACK), new King(E, ONE, WHITE)))),
                Color.BLACK);

        //when
        final Color currentTurnColor = chessGame.getCurrentTurnColor();

        //then
        assertThat(currentTurnColor).isEqualTo(BLACK);
    }
}