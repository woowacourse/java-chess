package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.piece.info.Team;
import chess.domain.position.File;
import chess.domain.position.Rank;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChessBoardTest {

    ChessBoard chessBoard;

    @BeforeEach
    void setup() {
        chessBoard = ChessBoardFactory.create();
    }

    @Test
    void create메서드를_호출하면_체스판을_초기화하여_반환한다() {
        //given

        //when

        //then
        assertThat(chessBoard.getSquares()).hasSize(64);
    }

    @Test
    void 경로에_장애물이_없는_상황에서_이동_명령을_받았을_때_체스_기물을_이동시킨다() {
        //given
        Position startPosition = Position.of(File.C, Rank.TWO);
        Position endPosition = Position.of(File.C, Rank.FOUR);

        //when
        chessBoard.move(startPosition, endPosition);
        boolean isStartEmpty = chessBoard.getSquares().stream()
            .filter(square -> square.isSamePosition(startPosition))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."))
            .isEmpty();
        boolean isEndEmpty = chessBoard.getSquares().stream()
            .filter(square -> square.isSamePosition(endPosition))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("칸이 초기화되지 않았습니다."))
            .isEmpty();

        //then
        assertAll(
            () -> assertThat(isStartEmpty).isTrue(),
            () -> assertThat(isEndEmpty).isFalse()
        );
    }

    @Nested
    class 이동불가 {

        @Test
        void 경로에_장애물이_있는_상황일때_체스_기물을_이동시키지못한다() {
            //given
            Position startPosition = Position.of(File.A, Rank.ONE);
            Position endPosition = Position.of(File.A, Rank.FOUR);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 있습니다.");
        }

        @Test
        void 도착지에_내_기물이_있는_상황일때_체스_기물을_이동시키지못한다() {
            //given
            Position startPosition = Position.of(File.A, Rank.ONE);
            Position endPosition = Position.of(File.A, Rank.TWO);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착지에 아군 기물이 있습니다.");
        }

        @Test
        void 상대방의_기물을_이동시키려고_할_때_체스_기물을_이동시키지못한다() {
            //given
            Position startPosition = Position.of(File.C, Rank.SEVEN);
            Position endPosition = Position.of(File.C, Rank.SIX);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물은 이동시킬 수 없습니다.");
        }
    }

    @Nested
    class 공격가능 {

        @Test
        void 폰이_공격가능할때_폰을_이동시킨다() {
            //given
            Position startPosition = Position.of(File.C, Rank.TWO);
            Position middlePosition = Position.of(File.C, Rank.FOUR);
            Position enemyStartPosition = Position.of(File.D, Rank.SEVEN);
            Position enemyEndPosition = Position.of(File.D, Rank.FIVE);

            chessBoard.move(startPosition, middlePosition);
            chessBoard.move(enemyStartPosition, enemyEndPosition);

            //when
            final Executable executable = () -> chessBoard.move(middlePosition, enemyEndPosition);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void 퀸이_공격가능할때_퀸을_이동시킨다() {
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
        void 킹이죽었을시_true반환() {
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

    @ParameterizedTest
    @CsvSource(value = {"A:6.0", "B:3.5", "C:4.0", "D:10.0", "E:1.0", "F:4.0", "G:3.5",
        "H:6.0"}, delimiter = ':')
    void calculateScoreByFile_메서드를_호출하면_해당_열에_존재하는_특정_팀의_점수를_계산한다(File input, Double expected) {
        //given

        //when
        Score score = chessBoard.calculateScoreByFileAndTeam(input, Team.WHITE);

        //then
        assertThat(score).isEqualTo(new Score(expected));
    }
}
