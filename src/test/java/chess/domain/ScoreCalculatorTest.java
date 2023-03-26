package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {

    @Test
    @DisplayName("흰색 팀의 시작 점수는 38점이다.")
    void white_start_score_is_38() {
        BoardFactory boardFactory = new BoardFactory();
        Score score = ScoreCalculator.calculateWhiteScore(boardFactory.make());

        assertThat(score).isEqualTo(new Score(38));
    }

    @Test
    @DisplayName("흑색 팀의 시작 점수는 38점이다.")
    void black_start_score_is_38() {
        BoardFactory boardFactory = new BoardFactory();
        Score score = ScoreCalculator.calculateBlackScore(boardFactory.make());

        assertThat(score).isEqualTo(new Score(38));
    }

    @ParameterizedTest
    @MethodSource("provideWhiteSquare")
    @DisplayName("백팀 점수 계산 테스트")
    void calculate_white_score(Map<Square, Piece> board, double point) {
        assertThat(ScoreCalculator.calculateWhiteScore(board))
                .isEqualTo(new Score(point));
    }

    private static Stream<Arguments> provideWhiteSquare() {
        return Stream.of(
                // 기본
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.TWO), new WhitePawn(),
                                Square.of(File.A, Rank.ONE), new Rook(Color.WHITE),
                                Square.of(File.G, Rank.ONE), new Rook(Color.WHITE),
                                Square.of(File.D, Rank.ONE), new Queen(Color.WHITE)
                        ), 20
                ),
                // 폰 위치 겹치는 경우
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.TWO), new WhitePawn(),
                                Square.of(File.A, Rank.ONE), new Knight(Color.WHITE),
                                Square.of(File.G, Rank.ONE), new Bishop(Color.WHITE),
                                Square.of(File.D, Rank.ONE), new Queen(Color.WHITE),
                                Square.of(File.F, Rank.ONE), new Rook(Color.WHITE),
                                Square.of(File.A, Rank.THREE), new WhitePawn()
                        ), 20.5
                ),
                // 폰만 있는 경우
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.TWO), new WhitePawn(),
                                Square.of(File.B, Rank.TWO),new WhitePawn(),
                                Square.of(File.C, Rank.TWO), new WhitePawn(),
                                Square.of(File.C, Rank.THREE), new WhitePawn()
                        ), 3
                ),
                // 폰 복잡한 버전
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.TWO), new WhitePawn(),
                                Square.of(File.B, Rank.TWO),new WhitePawn(),
                                Square.of(File.C, Rank.TWO), new WhitePawn(),
                                Square.of(File.C, Rank.THREE), new WhitePawn(),
                                Square.of(File.C, Rank.FOUR), new WhitePawn(),
                                Square.of(File.C, Rank.FIVE), new WhitePawn(),
                                Square.of(File.D, Rank.TWO), new WhitePawn(),
                                Square.of(File.E, Rank.TWO), new WhitePawn()
                        ), 6
                ),
                // 검정 말도 있을 때 흰 말만 계산
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.TWO), new WhitePawn(),
                                Square.of(File.B, Rank.TWO),new WhitePawn(),
                                Square.of(File.C, Rank.TWO), new WhitePawn(),
                                Square.of(File.A, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.G, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK)
                        ), 3
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideBlackSquare")
    @DisplayName("흑팀 점수 계산 테스트")
    void calculate_black_score(Map<Square, Piece> board, double point) {
        assertThat(ScoreCalculator.calculateBlackScore(board))
                .isEqualTo(new Score(point));
    }

    private static Stream<Arguments> provideBlackSquare() {
        return Stream.of(
                // 기본
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.SEVEN), new WhitePawn(),
                                Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.G, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK)
                        ), 19
                ),
                // 폰 겹치는 경우
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.A, Rank.EIGHT), new Knight(Color.BLACK),
                                Square.of(File.G, Rank.EIGHT), new Bishop(Color.BLACK),
                                Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK),
                                Square.of(File.F, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.A, Rank.SIX), new BlackPawn()
                        ), 20.5
                ),
                // 폰만 있는 경우
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.B, Rank.SEVEN),new BlackPawn(),
                                Square.of(File.C, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.C, Rank.SIX), new BlackPawn()
                        ), 3
                ),
                // 폰 복잡한 버전
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.B, Rank.SEVEN),new BlackPawn(),
                                Square.of(File.C, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.C, Rank.SIX), new BlackPawn(),
                                Square.of(File.C, Rank.FIVE), new BlackPawn(),
                                Square.of(File.D, Rank.FIVE), new BlackPawn(),
                                Square.of(File.D, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.E, Rank.SEVEN), new BlackPawn()
                        ), 5.5
                ),
                // 흰 말이 있어도 검은 말만 계산
                Arguments.of(
                        Map.of(Square.of(File.A, Rank.TWO), new WhitePawn(),
                                Square.of(File.B, Rank.TWO),new WhitePawn(),
                                Square.of(File.C, Rank.TWO), new WhitePawn(),
                                Square.of(File.A, Rank.SEVEN), new BlackPawn(),
                                Square.of(File.A, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.G, Rank.EIGHT), new Rook(Color.BLACK),
                                Square.of(File.D, Rank.EIGHT), new Queen(Color.BLACK)
                        ), 20
                )
        );
    }
}