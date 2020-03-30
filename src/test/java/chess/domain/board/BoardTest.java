package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Path;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

class BoardTest {
    private Board board;
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(A1, Team.BLACK);
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(A1, rook);
        map.put(A3, new Empty(A3, Team.NONE));
        map.put(A4, new Rook(A4, Team.WHITE));
        map.put(A5, new Rook(A5, Team.BLACK));
        board = Board.of(map);
    }

    @Test
    void create_By_Factory() {
        assertThat(BoardFactory.create()).isInstanceOf(Board.class);
    }

    @Test
    void update_Success() {
        board.update(A1, A3);
        assertThat(board.get(A3)).isEqualTo(rook);
    }

    @Test
    void hasPieceIn_Return_True() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(B2, new Rook(B2, Team.BLACK));

        Board board = Board.of(setter);

        assertThat(board.hasPieceIn(Path.of(B1, B3))).isTrue();
    }

    @Test
    void hasPieceIn_Return_False() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(B5, new Rook(B5, Team.BLACK));

        Board board = Board.of(setter);

        assertThat(board.hasPieceIn(Path.of(B1, B3))).isFalse();
    }

    @Test
    void pawnMove_Success_When_Black() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(C7, new Pawn(C7, Team.BLACK));

        Board board = Board.of(setter);

        assertThatCode(() -> board.update(C7, C5))
            .doesNotThrowAnyException();
    }

    @Test
    void pawnMove_Success_When_White() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Pawn(A2, Team.WHITE));

        Board board = Board.of(setter);

        assertThatCode(() -> board.update(A2, A3))
                .doesNotThrowAnyException();
    }

    @Test
    void pawnMove_Throw_Exception_When_TryFrontAttack() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Pawn(A2, Team.BLACK));
        setter.put(A3, new Pawn(A3, Team.WHITE));

        Board board = Board.of(setter);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.update(A2, A3))
                .withMessage("폰은 전방의 적을 공격할 수 없습니다.");
    }

    @Test
    void pawnMove_Throw_Exception_When_TryDiagonalMoveToEmptyPosition() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Pawn(A2, Team.BLACK));

        Board board = Board.of(setter);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.update(A2, B3))
                .withMessage("폰은 공격이 아니면 대각선 이동이 불가합니다.");
    }

    @Test
    void isKingDead_Return_True_When_BoardsHasNotTwoKing() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new King(A2, Team.BLACK));

        Board board = Board.of(setter);

        assertThat(board.isKingDead()).isTrue();
    }

    @Test
    void isKingDead_Return_False_When_BoardsHasTwoKing() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A3, new King(A3, Team.BLACK));
        setter.put(A2, new King(A2, Team.WHITE));

        Board board = Board.of(setter);

        assertThat(board.isKingDead()).isFalse();
    }
}