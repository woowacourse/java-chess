package techcourse.fp.chess.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static techcourse.fp.chess.domain.PieceFixtures.BLACK_KING;
import static techcourse.fp.chess.domain.PieceFixtures.BLACK_PAWN;
import static techcourse.fp.chess.domain.PieceFixtures.BLACK_QUEEN;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_KING;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_PAWN;
import static techcourse.fp.chess.domain.PieceFixtures.WHITE_QUEEN;
import static techcourse.fp.chess.domain.PositionFixtures.A1;
import static techcourse.fp.chess.domain.PositionFixtures.A2;
import static techcourse.fp.chess.domain.PositionFixtures.A3;
import static techcourse.fp.chess.domain.PositionFixtures.A4;
import static techcourse.fp.chess.domain.PositionFixtures.A5;
import static techcourse.fp.chess.domain.PositionFixtures.A7;
import static techcourse.fp.chess.domain.PositionFixtures.A8;
import static techcourse.fp.chess.domain.PositionFixtures.B4;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import techcourse.fp.chess.domain.piece.Color;
import techcourse.fp.chess.domain.piece.Empty;
import techcourse.fp.chess.domain.piece.Piece;
import techcourse.fp.chess.domain.piece.Turn;
import techcourse.fp.chess.domain.piece.ordinary.Rook;

class BoardTest {


    @DisplayName("보드의 사이즈가 맞으면 정상적으로 생성한다.")
    @Test
    void create_success() {
        assertThatNoException()
                .isThrownBy(() -> new Board(createEmptyBoard(), Turn.createByStartTurn(Color.WHITE)));
    }

    @DisplayName("보드의 사이즈가 8 x 8이 아니라면 생성시 예외를 반환한다.")
    @Test
    void create_fail() {
        assertThatThrownBy(() -> new Board(Map.of(), Turn.createByStartTurn(Color.WHITE)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("체스판의 사이즈는 8 x 8 여야합니다.");
    }

    @DisplayName("내 턴의 이동을 마치면 다음 턴으로 진행된다.")
    @Test
    void next_turn_after_move() {
        final Map<Position, Piece> rawBoard = createEmptyBoard();
        rawBoard.put(A1, Rook.create(Color.WHITE));

        final Position givenPosition = A8;
        rawBoard.put(givenPosition, BLACK_QUEEN);

        final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));

        board.move(A1, A2);
        //when && then
        assertThatNoException()
                .isThrownBy(() -> board.move(givenPosition, A7));
    }

    @DisplayName("나의 턴이 아니라면 이동시 예외를 반환한다.")
    @Test
    void fail_by_false_turn() {
        //given
        final Map<Position, Piece> rawBoard = createEmptyBoard();

        final Position givenPosition = A1;
        rawBoard.put(givenPosition, Rook.create(Color.WHITE));

        final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.BLACK));
        //when && then
        assertThatThrownBy(() -> board.move(givenPosition, A3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("상대방의 기물을 움직일 수 없습니다.");
    }

    @DisplayName("킹이 1마리라면 게임이 종료된 상태이다.")
    @Test
    void is_game_end() {
        //given
        final Map<Position, Piece> rawBoard = createEmptyBoard();
        rawBoard.put(A1, WHITE_KING);

        final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
        //when
        final boolean actual = board.isGameEnd();
        //then
        assertThat(actual).isTrue();
    }

    @DisplayName("킹이 2마리라면 게임이 끝나지 않았다.")
    @Test
    void is_game_not_end() {
        //given
        final Map<Position, Piece> rawBoard = createEmptyBoard();
        rawBoard.put(A1, WHITE_KING);
        rawBoard.put(A2, BLACK_KING);

        final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
        //when
        final boolean actual = board.isGameEnd();
        //then
        assertThat(actual).isFalse();
    }

    @DisplayName("승리팀을 반환한다.")
    @Test
    public void find_winner() {
        //given
        final Map<Position, Piece> rawBoard = createEmptyBoard();
        rawBoard.put(A1, WHITE_KING);
        rawBoard.put(A2, BLACK_KING);

        final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
        board.move(A1, A2);
        //when
        final Color actual = board.findWinner();
        //then
        assertThat(actual).isEqualTo(Color.WHITE);
    }

    @DisplayName("게임이 끝나지 않았을시 승리팀을 찾으면 예외를 반환한다.")
    @Test
    public void fail_find_winner_by_game_not_end() {
        //given
        final Map<Position, Piece> rawBoard = createEmptyBoard();
        rawBoard.put(A1, WHITE_KING);
        rawBoard.put(A2, BLACK_KING);

        final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
        //when && then
        assertThatThrownBy(() -> board.findWinner())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("아직 게임이 끝나지 않았습니다.");
    }

    @DisplayName("일반적인 기물들의 이동 테스트 ")
    @Nested
    public class NormalPieceMoveTest {

        @DisplayName("정상적으로 이동이 가능하면 true를 반환한다.")
        @Test
        void success() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenPosition = A1;
            rawBoard.put(givenPosition, WHITE_QUEEN);

            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
            //when && then
            assertThatNoException().isThrownBy(() -> board.move(givenPosition, A3));
        }

        @DisplayName("행마법상 이동 불가능하다면 예외가 발생한다.")
        @Test
        void fail_by_cannot_movable() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();
            final Position givenPosition = A1;
            rawBoard.put(givenPosition, WHITE_QUEEN);
            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
            //when && then
            assertThatThrownBy(() -> board.move(givenPosition, B4))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("행마법 상 이동할 수 없는 위치입니다.");
        }

        @DisplayName("도착 지점에 아군 기물이 있다면 예외가 발생한다.")
        @Test
        void fail_by_target_is_same_color() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();
            final Position givenSourcePosition = A1;
            rawBoard.put(givenSourcePosition, WHITE_QUEEN);

            final Position givenTargetPosition = A2;
            rawBoard.put(givenTargetPosition, WHITE_QUEEN);

            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
            //when && then
            assertThatThrownBy(() -> board.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("아군의 기물이 존재하는 곳으로는 이동할 수 없습니다.");
        }

        @DisplayName("이동 경로에 기물이 존재한다면 false를 반환한다.")
        @Test
        void fail_by_path_exist_piece() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenPosition = A1;
            rawBoard.put(givenPosition, WHITE_QUEEN);
            rawBoard.put(A2, WHITE_QUEEN);

            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
            //when && then
            assertThatThrownBy(() -> board.move(givenPosition, A3))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("이동하려는 경로에 기물이 존재합니다.");
        }
    }

    @DisplayName("Pawn의 이동을 테스트한다. ")
    @Nested
    public class PawnMoveTest {

        @DisplayName("폰이 초기 위치가 아니면 두 칸 이동 시 예외가 발생한다.")
        @Test
        void fail_pawn1() {
            //given
            final Map<Position, Piece> rawBoard = createEmptyBoard();

            final Position givenSourcePosition = A3;
            rawBoard.put(givenSourcePosition, WHITE_PAWN);

            final Position givenTargetPosition = A5;

            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
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

            final Position givenSourcePosition = A3;
            rawBoard.put(givenSourcePosition, WHITE_PAWN);

            final Position givenTargetPosition = A4;
            rawBoard.put(givenTargetPosition, BLACK_PAWN);

            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
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

            final Position givenSourcePosition = A3;
            rawBoard.put(givenSourcePosition, WHITE_PAWN);

            final Position givenTargetPosition = B4;

            final Board board = new Board(rawBoard, Turn.createByStartTurn(Color.WHITE));
            //when  && then
            assertThatThrownBy(() -> board.move(givenSourcePosition, givenTargetPosition))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("폰이 해당 지점으로 이동할 수 없습니다.");
        }
    }

    private Map<Position, Piece> createEmptyBoard() {
        final Map<Position, Piece> board = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                board.put(Position.of(file, rank), Empty.create());
            }
        }

        return board;
    }
}
