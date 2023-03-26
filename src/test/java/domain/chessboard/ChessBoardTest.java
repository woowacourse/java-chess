package domain.chessboard;

import domain.chessgame.TestChessBoardFactory;
import domain.piece.Color;
import domain.position.Position;
import domain.position.PositionFactory;
import domain.squarestatus.SquareStatus;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @Test
    @DisplayName("체스판을 생성한다.")
    void givenChessBoard_thenSize64() {
        final ChessBoard chessBoard = ChessBoardFactory.generate();
        assertThat(chessBoard)
                .extracting("chessBoard", InstanceOfAssertFactories.map(Position.class, SquareStatus.class))
                .hasSize(32);
    }

    @Test
    @DisplayName("색깔을 입력하면 Pawn을 제외한 해당 색깔의 모든 기물을 리스트로 반환한다,")
    void givenColor_thenReturnPieceList() {
        //given
        final ChessBoard chessBoard = ChessBoardFactory.generate();

        //when
        final List<SquareStatus> whitePieces = chessBoard.findPieces(Color.WHITE);

        //then
        assertThat(whitePieces).hasSize(8);
    }

    @Test
    @DisplayName("색깔을 입력하면 해당 색깔의 Pawn 개수를 칼럼별로 리스트화한다.")
    void givenColor_thenPawnCountList() {
        //given
        final ChessBoard chessBoard = ChessBoardFactory.generate();

        //when
        final List<Long> columnPawnCount = chessBoard.findColumnPawnCounts(Color.WHITE);


        //then
        assertThat(columnPawnCount)
                .containsOnly(1L)
                .hasSize(8);
    }

    @Nested
    class IsKingAliveTest{

        @Test
        @DisplayName("살아있는 King이 2개면 True를 리턴한다.")
        void givenBothKingAlive_thenReturnFalse() {
            //given
            final ChessBoard chessBoard = TestChessBoardFactory.generate();

            //when
            final boolean isKingAlive = chessBoard.isKingAlive();

            //then
            assertThat(isKingAlive).isTrue();
        }

        @Test
        @DisplayName("살아있는 King이 2개가 아니면 false를 리턴한다.")
        void givenOnlyKingAlive_thenReturnFalse() {
            //given
            final ChessBoard chessBoard = TestChessBoardFactory.generate();

            //when
            final Position source = PositionFactory.createPosition("G4");
            final Position target = PositionFactory.createPosition("G5");

            chessBoard.move(source, target);
            final boolean isKingAlive = chessBoard.isKingAlive();

            //then
            assertThat(isKingAlive).isFalse();
        }

    }

}
