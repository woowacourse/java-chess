package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void setup() {
        chessBoard = ChessBoardFactory.create();
    }

    @Nested
    class 생성 {
        @Test
        void should_체스판을초기화해반환한다_when_create호출시() {
            //given

            //when

            //then
            assertThat(chessBoard).extracting("squares", InstanceOfAssertFactories.collection(Square.class))
                    .hasSize(64);
        }
    }

    @Nested
    class 이동 {
        @Test
        void should_체스_기물을_이동시킨다_when_장애물이_없는_상황에서_이동_명령을_받았을_때() {
            //given
            Position source = Position.of(File.C, Rank.TWO);
            Position destination = Position.of(File.C, Rank.FOUR);

            //when
            chessBoard.move(source, destination);
            boolean isEmpty = chessBoard.getSquares().stream()
                    .filter(square -> square.isSamePosition(source))
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."))
                    .isEmpty();

            //then
            assertAll(
                    () -> assertThat(isEmpty).isTrue()
            );
        }
    }

    @Nested
    class 이동불가 {
        @Test
        void should_체스_기물을_이동시키지못한다_when_장애물이_있는_상황일때() {
            //given
            Position source = Position.of(File.A, Rank.ONE);
            Position destination = Position.of(File.A, Rank.FOUR);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(source, destination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동 경로에 다른 기물이 있습니다.");
        }

        @Test
        void should_체스_기물을_이동시키지못한다_when_도착지에_내_기물이_있는_상황일때() {
            //given
            Position source = Position.of(File.A, Rank.ONE);
            Position destination = Position.of(File.A, Rank.TWO);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(source, destination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("도착지에 아군 기물이 있습니다.");
        }

        @Test
        void should_체스_기물을_이동시키지못한다_when_상대방의_기물을_이동시키려고_할_때() {
            //given
            Position source = Position.of(File.C, Rank.SEVEN);
            Position destination = Position.of(File.C, Rank.SIX);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(source, destination))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("상대방의 기물은 이동시킬 수 없습니다.");
        }
    }

    @Nested
    class 공격가능 {

        @Test
        void should_폰을_이동시킨다_when_폰이_공격가능할때() {
            //given
            Position source = Position.of(File.C, Rank.TWO);
            Position middlePosition = Position.of(File.C, Rank.FOUR);
            Position enemyStartPosition = Position.of(File.D, Rank.SEVEN);
            Position enemyEndPosition = Position.of(File.D, Rank.FIVE);

            chessBoard.move(source, middlePosition);
            chessBoard.move(enemyStartPosition, enemyEndPosition);

            //when
            final Executable executable = () -> chessBoard.move(middlePosition, enemyEndPosition);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void should_퀸을_이동시킨다_when_퀸이_공격가능할때() {
            //given
            Position pawnStart = Position.of(File.D, Rank.TWO);
            Position pawnMiddle = Position.of(File.D, Rank.FOUR);
            Position enemyPawnStart = Position.of(File.E, Rank.SEVEN);
            Position enemyPawnEnd = Position.of(File.E, Rank.FIVE);
            Position enemyKnightStart = Position.of(File.G, Rank.EIGHT);
            Position enemyKnightEnd = Position.of(File.F, Rank.SIX);
            Position queenStart = Position.of(File.D, Rank.ONE);
            Position queenEnd = Position.of(File.D, Rank.SEVEN);

            chessBoard.move(pawnStart, pawnMiddle);
            chessBoard.move(enemyPawnStart, enemyPawnEnd);
            chessBoard.move(pawnMiddle, enemyPawnEnd);
            chessBoard.move(enemyKnightStart, enemyKnightEnd);

            //when
            final Executable executable = () -> chessBoard.move(queenStart, queenEnd);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void should_true반환_when_킹이죽었을시() {
            //given
            Position pawnStart = Position.of(File.D, Rank.TWO);
            Position pawnMiddle = Position.of(File.D, Rank.FOUR);
            Position enemyPawnStart = Position.of(File.C, Rank.SEVEN);
            Position enemyPawnEnd = Position.of(File.C, Rank.FIVE);
            Position knightStart = Position.of(File.G, Rank.ONE);
            Position knightEnd = Position.of(File.H, Rank.THREE);
            Position enemyQueenStart = Position.of(File.D, Rank.EIGHT);
            Position enemyQueenMiddle = Position.of(File.A, Rank.FIVE);
            Position enemyQueenEnd = Position.of(File.E, Rank.ONE);

            chessBoard.move(pawnStart, pawnMiddle);
            chessBoard.move(enemyPawnStart, enemyPawnEnd);
            chessBoard.move(knightStart, knightEnd);
            chessBoard.move(enemyQueenStart, enemyQueenMiddle);
            chessBoard.move(knightEnd, knightStart);
            chessBoard.move(enemyQueenMiddle, enemyQueenEnd);

            //when
            final boolean actual = chessBoard.isKingDead();

            //then
            assertThat(actual).isTrue();
        }
    }

    @Nested
    class 점수계산 {
        @Test
        void should_38점을반환한다_when_초기상태일때() {
            //given
            final double expected = 38.0;

            //when
            final double whiteScore = chessBoard.calculateScore(Team.WHITE);
            final double blackScore = chessBoard.calculateScore(Team.BLACK);

            //then
            assertThat(whiteScore).isEqualTo(blackScore).isEqualTo(expected);
        }

    }
}