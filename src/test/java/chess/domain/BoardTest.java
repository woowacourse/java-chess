package chess.domain;

import chess.domain.piece.Blank;
import chess.domain.piece.Color;
import chess.domain.piece.FirstPawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    @DisplayName("보드의 사이즈가 8 x 8이 아니라면 생성시 예외를 반환한다.")
    @Test
    void create_fail() {
        assertThatThrownBy(() -> Board.from(Collections.emptyMap()))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판의 사이즈는 8 x 8 여야합니다.");
    }

    private Map<Position, Piece> createEmptyBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Blank.create());
            }
        }
        return board;
    }

    @DisplayName("일반적인 기물들의 이동 테스트 ")
    @Nested
    public class NormalPieceMoveTest {

        @DisplayName("정상적으로 이동이 가능하면 true를 반환한다.")
        @Test
        void success() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenPosition = PositionFixtures.A1;
            rawBoard.put(givenPosition, Rook.create(Color.WHITE));

            final Board board = Board.from(rawBoard);
            //when && then
            assertThatNoException().isThrownBy(() -> board.move(givenPosition, PositionFixtures.A3));
        }

        @DisplayName("행마법상 이동 불가능하다면 예외가 발생한다.")
        @Test
        void fail_by_cannot_movable() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();
            final Position givenPosition = PositionFixtures.A1;
            rawBoard.put(givenPosition, Rook.create(Color.WHITE));
            final Board board = Board.from(rawBoard);
            //when && then
            assertThatThrownBy(() -> board.move(givenPosition, PositionFixtures.B2))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법상 이동 불가능한 지역입니다.");
        }

        @DisplayName("도착 지점에 아군 기물이 있다면 예외가 발생한다.")
        @Test
        void fail_by_target_is_same_color() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();
            final Position givenSourcePosition = PositionFixtures.A1;
            rawBoard.put(givenSourcePosition, Rook.create(Color.WHITE));

            final Position givenTargetPosition = PositionFixtures.A2;
            rawBoard.put(givenTargetPosition, Rook.create(Color.WHITE));

            final Board board = Board.from(rawBoard);
            //when && then
            assertThatThrownBy(() -> board.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        @DisplayName("이동 경로에 기물이 존재한다면 false를 반환한다.")
        @Test
        void fail_by_path_exist_piece() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenPosition = PositionFixtures.A1;
            rawBoard.put(givenPosition, Rook.create(Color.WHITE));

            final Position givenPathPosition = PositionFixtures.A2;
            rawBoard.put(givenPathPosition, Rook.create(Color.WHITE));

            final Board board = Board.from(rawBoard);
            //when && then
            assertThatThrownBy(() -> board.move(givenPosition, PositionFixtures.A3))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 기물이 존재합니다.");
        }
    }

    @DisplayName("Pawn의 이동을 테스트한다. ")
    @Nested
    public class FirstPawnMoveTest {

        @DisplayName("폰이 초기 위치가 아니면 두 칸 이동 시 예외가 발생한다.")
        @Test
        void fail_pawn1() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = PositionFixtures.A3;
            rawBoard.put(givenSourcePosition, FirstPawn.createByColor(Color.WHITE));

            final Position givenTargetPosition = PositionFixtures.A5;

            final Board board = Board.from(rawBoard);
            //when  && then
            assertThatThrownBy(() -> board.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("폰은 앞에 기물이 있으면 전진 시 예외가 발생한다.")
        @Test
        void fail_pawn2() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = PositionFixtures.A3;
            rawBoard.put(givenSourcePosition, FirstPawn.createByColor(Color.WHITE));

            final Position givenTargetPosition = PositionFixtures.A4;
            rawBoard.put(givenTargetPosition, FirstPawn.createByColor(Color.BLACK));

            final Board board = Board.from(rawBoard);
            //when  && then
            assertThatThrownBy(() -> board.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }

        @DisplayName("폰은 대각선 방향에 적 기물이 없을 시 예외가 발생한다.")
        @Test
        void fail_pawn3() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = PositionFixtures.A3;
            rawBoard.put(givenSourcePosition, FirstPawn.createByColor(Color.WHITE));

            final Position givenTargetPosition = PositionFixtures.B4;

            final Board board = Board.from(rawBoard);
            //when  && then
            assertThatThrownBy(() -> board.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }
    }
}
