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

public class EtcMoveTest {
    @ParameterizedTest(name = "{0} 이동 불가능 하다")
    @MethodSource("inValidProvider")
    void pawnFalseTest(String name, Square source, Square target) {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        ValidateMove validateMove = new SourceMoveValidator();
        assertThat(validateMove.validate(new ValidateData(source, target, chessboard))).isFalse();
    }

    @ParameterizedTest(name = "{0} 이동 가능 하다")
    @MethodSource("validProvider")
    void pawnTrueTest(String name, Square source, Square target) {
        ChessGame chessGame = new ChessGame();
        Chessboard chessboard = chessGame.getChessboard();
        Square movedSource = new Square(File.D, Rank.SIX);
        chessboard.swapPiece(source, movedSource);
        ValidateMove validateMove = new SourceMoveValidator();
        assertThat(validateMove.validate(new ValidateData(movedSource, target, chessboard))).isTrue();
    }

    static Stream<Arguments> inValidProvider() {
        return Stream.of(
                Arguments.arguments("킹은 이동 타겟에 아군 기물이 있으면", new Square(File.D, Rank.ONE), new Square(File.D, Rank.TWO)),
                Arguments.arguments("퀸은 이동 타겟에 아군 기물이 있으면", new Square(File.E, Rank.ONE), new Square(File.E, Rank.TWO)),
                Arguments.arguments("퀸은 경로에 기물이 있으면", new Square(File.E, Rank.ONE), new Square(File.E, Rank.EIGHT)),
                Arguments.arguments("비숍은 이동 타겟에 아군 기물이 있으면", new Square(File.C, Rank.ONE), new Square(File.D, Rank.TWO)),
                Arguments.arguments("비숍은 경로룩 기물이 있으면", new Square(File.C, Rank.ONE), new Square(File.H, Rank.SIX)),
                Arguments.arguments("룩은 이동 타겟에 아군 기물이 있으면", new Square(File.A, Rank.ONE), new Square(File.A, Rank.TWO)),
                Arguments.arguments("룩은 경로에 기물이 있으면", new Square(File.A, Rank.ONE), new Square(File.H, Rank.ONE))
        );
    }

    static Stream<Arguments> validProvider() {
        return Stream.of(
                Arguments.arguments("킹은 이동 타겟이 비어있다면", new Square(File.E, Rank.ONE), new Square(File.D, Rank.FIVE)),
                Arguments.arguments("킹은 이동 타겟에 적 기물이 있다면", new Square(File.E, Rank.ONE), new Square(File.D, Rank.SEVEN)),
                Arguments.arguments("퀸은 직선 이동 타겟이 비어있다면", new Square(File.D, Rank.ONE), new Square(File.H, Rank.SIX)),
                Arguments.arguments("퀸은 대각선 이동 타겟이 비어있다면", new Square(File.D, Rank.ONE), new Square(File.G, Rank.THREE)),
                Arguments.arguments("퀸은 직선 이동 타겟에 적 기물이 있다면", new Square(File.E, Rank.ONE), new Square(File.E, Rank.SEVEN)),
                Arguments.arguments("퀸은 대각선 이동 타겟에 적 기물이 있다면", new Square(File.E, Rank.ONE), new Square(File.E, Rank.SEVEN)),
                Arguments.arguments("비숍은 이동 타겟이 비어있다면", new Square(File.C, Rank.ONE), new Square(File.G, Rank.THREE)),
                Arguments.arguments("비숍은 이동 타겟에 적 기물이 있다면", new Square(File.C, Rank.ONE), new Square(File.E, Rank.SEVEN)),
                Arguments.arguments("룩은 이동 타겟이 비어있다면", new Square(File.A, Rank.ONE), new Square(File.H, Rank.SIX)),
                Arguments.arguments("룩은 이동 타겟에 적 기물이 있다면", new Square(File.A, Rank.ONE), new Square(File.D, Rank.SEVEN))
        );
    }
}
