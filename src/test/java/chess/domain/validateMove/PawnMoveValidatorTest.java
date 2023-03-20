package chess.domain.validateMove;

import chess.domain.ChessGame;
import chess.domain.board.Chessboard;
import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class PawnMoveValidatorTest {
    @ParameterizedTest(name = "{0} 이동 불가능 하다")
    @MethodSource("inValidProvider")
    void pawnFalseTest(String name, Square source, Square target) {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        chessboard.swapPiece(new Square(File.A, Rank.SEVEN), new Square(File.B, Rank.THREE));
        chessboard.swapPiece(new Square(File.B, Rank.SEVEN), new Square(File.C, Rank.FOUR));
        ValidateMove validateMove = new SourceMoveValidator();
        assertThat(validateMove.validate(new ValidateData(source, target, chessboard))).isFalse();
    }

    @ParameterizedTest(name = "{0} 이동 가능 하다")
    @MethodSource("validProvider")
    void pawnTrueTest(String name, Square source, Square target) {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        chessboard.swapPiece(new Square(File.A, Rank.SEVEN), new Square(File.B, Rank.THREE));
        ValidateMove validateMove = new SourceMoveValidator();
        assertThat(validateMove.validate(new ValidateData(source, target, chessboard))).isTrue();
    }

    static Stream<Arguments> inValidProvider() {
        return Stream.of(
                Arguments.arguments("폰은 기물이 앞에 있을때 ", new Square(File.B, Rank.TWO), new Square(File.B, Rank.FOUR)),
                Arguments.arguments("폰은 직선 목표에 기물이 있을때", new Square(File.C, Rank.TWO), new Square(File.C, Rank.FOUR)),
                Arguments.arguments("폰은 대각선에 적기물이 없을때", new Square(File.C, Rank.TWO), new Square(File.D, Rank.THREE))
        );
    }

    static Stream<Arguments> validProvider() {
        return Stream.of(
                Arguments.arguments("폰은 기물이 앞에 없을때 두칸 앞으로", new Square(File.A, Rank.TWO), new Square(File.A, Rank.FOUR)),
                Arguments.arguments("폰은 기물이 앞에 없을때 한칸 앞으로", new Square(File.A, Rank.TWO), new Square(File.A, Rank.THREE)),
                Arguments.arguments("폰은 대각선에 적기물이 있을때 대각선으로", new Square(File.A, Rank.TWO), new Square(File.B, Rank.THREE))
        );
    }
}
