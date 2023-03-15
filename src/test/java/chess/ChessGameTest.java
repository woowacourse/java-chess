package chess;

import chess.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ChessGameTest {
    @ParameterizedTest(name = "잘못된 위치 입력시 예외가 발생한다")
    @MethodSource("invalidSquareProvider")
    void moveToInvalidSquare(Square source, Square target) {
        ChessGame chessGame = new ChessGame();

        Assertions.assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "이동 가능한 위치 입력시 기물이 이동한다")
    @MethodSource("validSquareProvider")
    void moveToValidSquare(Square source, Square target) {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        Piece expected = chessboard.getPieceAt(source);
        chessGame.move(source, target);


        Assertions.assertThat(chessboard.getPieceAt(target))
                .isEqualTo(expected);
    }

    static Stream<Arguments> invalidSquareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.A, Rank.ONE), new Square(File.A, Rank.THREE)),
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.A, Rank.FIVE)),
                Arguments.arguments(new Square(File.A, Rank.THREE), new Square(File.A, Rank.FOUR))
                // 테스트 케이스 추가
        );
    }

    static Stream<Arguments> validSquareProvider() {
        return Stream.of(
                Arguments.arguments(new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE))
                // 테스트 케이스 추가
        );
    }


}
