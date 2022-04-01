package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.domain.Color;
import chess.domain.Distance;
import chess.domain.board.MoveResult;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.board.TestBoardFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PawnTest {
    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"0:1", "0:2"}, delimiter = ':')
    @DisplayName("폰 첫수 이동 테스트 - movable")
    void pawnMovable(int rankDistance, int fileDistance) {
        // given & when
        Pawn pawn = new Pawn(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(pawn.movable(distance, opponent)).isTrue();
    }

    @Test
    @DisplayName("폰 첫수 이동 테스트 - movable")
    void pawnMovableBothFirstMove() {
        // given & when
        Pawn pawn = new Pawn(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(0, 2);

        // then
        assertAll(
                ()-> assertThat(pawn.movable(distance, opponent)).isTrue(),
                ()-> assertThat(pawn.movable(distance, opponent)).isFalse()
        );
    }

    @Test
    @DisplayName("폰 적군 기물 대상 대각선 이동 테스트 - movable")
    void pawnMovableDiagonalWithOpponent() {
        // given & when
        Pawn pawn = new Pawn(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance firstMoveDistance = new Distance(0,1);
        Distance distance = new Distance(1, 1);

        // then
        assertAll(
                ()-> assertThat(pawn.movable(firstMoveDistance, opponent)).isTrue(),
                ()-> assertThat(pawn.movable(distance, opponent)).isTrue()
        );
    }

    @ParameterizedTest(name = "rank 거리 : {0}, file 거리 : {1}")
    @CsvSource(value = {"1:2", "1:0", "2:2"}, delimiter = ':')
    @DisplayName("폰 이동 실패 테스트 - movable")
    void pawnMovableFail(int rankDistance, int fileDistance) {
        // given & when
        Pawn pawn = new Pawn(Color.WHITE);
        Bishop opponent = new Bishop(Color.BLACK);
        Distance distance = new Distance(rankDistance, fileDistance);

        // then
        assertThat(pawn.movable(distance, opponent)).isFalse();
    }

    @ParameterizedTest(name = "출발지 : {0}, 도착지 : {1}")
    @CsvSource(value = {"B2, B3"})
    @DisplayName("폰 앞으로 전진 테스트")
    void pawnMoveForward(String from, String to) {
        // given
        Board board = BoardFactory.createBoard();

        // when
        MoveResult result = board.move(from, to, Color.WHITE);

        // then
        assertThat(result).isEqualTo(MoveResult.SUCCESS);
    }

    @Test
    @DisplayName("아군 기물 존재로 인한 폰 이동 실패 테스트")
    void pawnMoveShouldFailCausedBySameColoredPiece() {
        // given
        Map<Position, Piece> testBoard = new LinkedHashMap<>();
        testBoard.put(Position.of("F6"), new Rook(Color.WHITE));
        testBoard.put(Position.of("F5"), new Pawn(Color.WHITE));
        TestBoardFactory testBoardFactory = new TestBoardFactory(testBoard);
        Board board = testBoardFactory.createBoard();

        // when
        MoveResult result = board.move("F5", "F6", Color.WHITE);

        // then
        assertThat(result).isEqualTo(MoveResult.FAIL);
    }
}
