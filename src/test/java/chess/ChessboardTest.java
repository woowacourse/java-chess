package chess;

import chess.domain.BoardInitializer;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


class ChessboardTest {
    @DisplayName("체스판은 64개의 Square로 이루어진다.")
    @Test
    void createChessboardSuccessTest() {
        Assertions.assertThat(new Chessboard())
                .extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.map(Square.class, Piece.class))
                .hasSize(64);
    }

    @DisplayName("경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.A, Rank.EIGHT)))
                .isFalse();
    }

    @DisplayName("경로 사이가 비어있는 경우, false를 반환한다.")
    @Test
    void isEmptyInRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.A, Rank.SEVEN)))
                .isTrue();
    }

    @DisplayName("대각선 경로 사이에 기물이 있을 경우, false를 반환한다.")
    @Test
    void isExistPieceInDiagonalRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.G, Rank.EIGHT)))
                .isFalse();
    }

    @DisplayName("대각선 경로 사이가 비어있는 경우, true를 반환한다.")
    @Test
    void isEmptyInDiagonalRouteTest() {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);

        Assertions.assertThat(chessboard.isEmptyInRoute(new Square(File.A, Rank.TWO), new Square(File.F, Rank.SEVEN)))
                .isTrue();
    }

    @ParameterizedTest(name = "{0}이 한개 없을 때 {3}점을 반환한다.")
    @MethodSource("validSquareProvider")
    void countScoreTest(String name, Square source, Square target, double score) {
        Chessboard chessboard = new Chessboard();
        BoardInitializer.initializeBoard(chessboard);
        chessboard.swapPiece(source, target);

        Assertions.assertThat(chessboard.countScore(Camp.WHITE)).isEqualTo(score);
    }

    static Stream<Arguments> validSquareProvider() {
        return Stream.of(
                Arguments.arguments("폰", new Square(File.A, Rank.SEVEN), new Square(File.A, Rank.TWO), 37),
                Arguments.arguments("퀸", new Square(File.A, Rank.SEVEN), new Square(File.D, Rank.ONE), 29),
                Arguments.arguments("룩", new Square(File.A, Rank.SEVEN), new Square(File.A, Rank.ONE), 33),
                Arguments.arguments("나이트", new Square(File.A, Rank.SEVEN), new Square(File.B, Rank.ONE), 35.5),
                Arguments.arguments("비숍", new Square(File.A, Rank.SEVEN), new Square(File.C, Rank.ONE), 35)
        );
    }
}
