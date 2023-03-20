package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {

    public static final Pawn BLACK_PIECE = Pawn.from(Color.BLACK);
    public static final Pawn WHITE_PIECE = Pawn.from(Color.WHITE);
    public static final Position POSITION_1_4 = Position.of(1, 4);
    public static final Position POSITION_1_5 = Position.of(1, 5);
    public static final Position POSITION_1_6 = Position.of(1, 6);
    public static final Position POSITION_1_1 = Position.of(1, 1);
    public static final Position POSITION_1_3 = Position.of(1, 3);
    public static final Position POSITION_1_2 = Position.of(1, 2);

    @Test
    @DisplayName("검정 폰이 한 칸 이동하는 경우 성공 테스트")
    void PawnSingleMoveSuccessTest() {
        //given
        Map<Position, Piece> pieces = Map.of(POSITION_1_4, BLACK_PIECE);
        Board board = Board.from(pieces);
        //when
        board.move(POSITION_1_4, Position.of(1,5), Color.BLACK);
        //then
        Assertions.assertThat(board.getBoard().get(Position.of(1,5))).isInstanceOf(Pawn.class);
        Assertions.assertThat(board.getBoard().get(POSITION_1_4)).isInstanceOf(Empty.class);
    }

    @Test
    @DisplayName("검정 폰이 대각선으로 이동하는 경우 테스트")
    void PawnSingleMoveSuccessTest2() {
        //given
        Map<Position, Piece> pieces = Map.of(Position.of(1,1), BLACK_PIECE
                , Position.of(2,0), WHITE_PIECE);
        Board board = Board.from(pieces);
        //when
        board.move(Position.of(1,1), Position.of(2,0), Color.BLACK);
        //then
        Assertions.assertThat(board.getBoard().get(Position.of(2,0))).isInstanceOf(Pawn.class);
        Assertions.assertThat(board.getBoard().get(Position.of(1,1))).isInstanceOf(Empty.class);
    }

    @Test
    @DisplayName("검정 폰이 한 칸 이동하는 경우 실패 테스트 (이동 할 수 없는 위치 입니다.)")
    void PawnInvalidPositionFailTest1() {
        //given
        Map<Position, Piece> pieces = Map.of(POSITION_1_4, BLACK_PIECE);
        Board board = Board.from(pieces);
        //then
        assertThatThrownBy(() -> board.move(POSITION_1_4, Position.of(1, 0), Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 할 수 없는 위치 입니다.");
    }

    @Test
    @DisplayName("검정 폰이 한 칸 이동하는 경우 실패 테스트 (같은 색깔의 기물을 선택할 수 없습니다.)")
    void PawnInvalidPositionFailTest2() {
        //given
        Map<Position, Piece> pieces = Map.of(POSITION_1_4, BLACK_PIECE
                , POSITION_1_5, BLACK_PIECE);
        Board board = Board.from(pieces);
        //then
        assertThatThrownBy(() -> board.move(POSITION_1_4, POSITION_1_5, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("같은 색깔의 기물을 선택할 수 없습니다.");
    }

    @Test
    @DisplayName("검정 폰이 한 칸 이동하는 경우 실패 테스트 (이동 할 수 없는 위치 입니다.)")
    void PawnInvalidPositionFailTest3() {
        //given
        Map<Position, Piece> pieces = Map.of(POSITION_1_4, BLACK_PIECE
                , POSITION_1_5, WHITE_PIECE);
        Board board = Board.from(pieces);
        //then
        assertThatThrownBy(() -> board.move(POSITION_1_4, POSITION_1_5, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 할 수 없는 위치 입니다.");
    }

    @Test
    @DisplayName("검정 폰이 앞으로 두 칸 이동하는 경우 실패 테스트 (이동 할 수 없는 위치 입니다.)")
    void PawnInvalidPositionFailTest4() {
        //given
        Map<Position, Piece> pieces = Map.of(POSITION_1_4, BLACK_PIECE);
        Board board = Board.from(pieces);

        //then
        assertThatThrownBy(() -> board.move(POSITION_1_4, POSITION_1_6, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 할 수 없는 위치 입니다.");
    }

    @Test
    @DisplayName("검정 폰이 앞으로 두 칸 이동하는 경우 실패 테스트 (이동 위치가 다른 기물에 의해 막혀 있습니다.)")
    void PawnInvalidPositionFailTest5() {
        //given
        Map<Position, Piece> pieces = Map.of(POSITION_1_1, BLACK_PIECE, POSITION_1_2, WHITE_PIECE);
        Board board = Board.from(pieces);

        //then
        assertThatThrownBy(() -> board.move(POSITION_1_1, POSITION_1_3, Color.BLACK))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이동 위치가 다른 기물에 의해 막혀 있습니다.");
    }
}
