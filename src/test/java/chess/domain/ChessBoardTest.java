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

    @Test
    void should_체스판을초기화해반환한다_when_create호출시() {
        //given

        //when

        //then
        assertThat(chessBoard).extracting("squares", InstanceOfAssertFactories.collection(Square.class))
                .hasSize(64);
    }

    @Test
    void should_체스_기물을_이동시킨다_when_장애물이_없는_상황에서_이동_명령을_받았을_때() {
        //given
        Position startPosition = Position.of(Rank.C, File.TWO);
        Position endPosition = Position.of(Rank.C, File.FOUR);

        //when
        chessBoard.move(startPosition, endPosition);
        //boolean actual = chessBoard.isSamePiece(endPosition, Pawn.class);
        boolean isEmpty = chessBoard.isEmptyAt(startPosition);

        //then
        assertAll(
                //() -> assertThat(actual).isTrue(),
                () -> assertThat(isEmpty).isTrue()
        );
    }

    @Nested
    class 이동불가{
        @Test
        void should_체스_기물을_이동시키지못한다_when_장애물이_있는_상황일때 () {
        //given
        Position startPosition = Position.of(Rank.A, File.ONE);
        Position endPosition = Position.of(Rank.A, File.FOUR);

        //when

        //then
        assertThatThrownBy(() -> chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 경로에 다른 기물이 있습니다.");
    }

        @Test
        void should_체스_기물을_이동시키지못한다_when_도착지에_내_기물이_있는_상황일때 () {
        //given
        Position startPosition = Position.of(Rank.A, File.ONE);
        Position endPosition = Position.of(Rank.A, File.TWO);

        //when

        //then
        assertThatThrownBy(() -> chessBoard.move(startPosition, endPosition))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("도착지에 아군 기물이 있습니다.");
    }

        @Test
        void should_체스_기물을_이동시키지못한다_when_상대방의_기물을_이동시키려고_할_때() {
            //given
            Position startPosition = Position.of(Rank.C, File.SEVEN);
            Position endPosition = Position.of(Rank.C, File.SIX);

            //when

            //then
            assertThatThrownBy(() -> chessBoard.move(startPosition, endPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("상대방의 기물은 이동시킬 수 없습니다.");
        }
    }

    @Nested
    class 공격가능{

        @Test
        void should_폰을_이동시킨다_when_폰이_공격가능할때() {
            //given
            Position startPosition = Position.of(Rank.C, File.TWO);
            Position middlePosition = Position.of(Rank.C, File.FOUR);
            Position enemyStartPosition = Position.of(Rank.D, File.SEVEN);
            Position enemyEndPosition = Position.of(Rank.D, File.FIVE);

            chessBoard.move(startPosition, middlePosition);
            chessBoard.move(enemyStartPosition, enemyEndPosition);

            //when
            final Executable executable = () -> chessBoard.move(middlePosition, enemyEndPosition);

            //then
            assertDoesNotThrow(executable);
        }

        @Test
        void should_퀸을_이동시킨다_when_퀸이_공격가능할때() {
            //given
            Position pawnStart = Position.of(Rank.D, File.TWO);
            Position pawnMiddle = Position.of(Rank.D, File.FOUR);
            Position enemyPawnStart = Position.of(Rank.E, File.SEVEN);
            Position enemyPawnEnd = Position.of(Rank.E, File.FIVE);
            Position enemyKnightStart = Position.of(Rank.G, File.EIGHT);
            Position enemyKnightEnd = Position.of(Rank.F, File.SIX);
            Position queenStart = Position.of(Rank.D, File.ONE);
            Position queenEnd = Position.of(Rank.D, File.SEVEN);

            chessBoard.move(pawnStart, pawnMiddle);
            chessBoard.move(enemyPawnStart, enemyPawnEnd);
            chessBoard.move(pawnMiddle, enemyPawnEnd);
            chessBoard.move(enemyKnightStart, enemyKnightEnd);


            //when
            final Executable executable = () -> chessBoard.move(queenStart, queenEnd);

            //then
            assertDoesNotThrow(executable);
        }

    }
}