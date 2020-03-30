package chess.domain.board;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.piece.King;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Rook;
import chess.domain.piece.Team;
import chess.domain.position.Path;
import chess.domain.position.Position;

class BoardTest {
    private Board board;
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = new Rook(A1, Team.BLACK);
        Map<Position, Piece> map = new LinkedHashMap<>();
        map.put(Position.of("a1"), rook);
        map.put(Position.of("a4"), new Rook(A4, Team.WHITE));
        map.put(Position.of("a5"), new Rook(A5, Team.BLACK));
        board = Board.of(map);
    }

    @Test
    void create_By_Factory() {
        assertThat(BoardFactory.create()).isInstanceOf(Board.class);
    }

    @Test
    void get_When_Success() {
        assertThat(board.get(A1)).isEqualTo(rook);
    }

    @Test
    void get_When_Fail() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                board.get(A2)
        ).withMessage("기물이 존재하지 않습니다.");
    }

    @Test
    void move_Success_When_NotingThere() {
        board.move(A1, A3, Team.BLACK);
        assertThat(board.get(A3)).isEqualTo(rook);
        assertThatIllegalArgumentException().isThrownBy(() ->
                board.get(A1)
        ).withMessage("기물이 존재하지 않습니다.");
    }

    @Test
    void move_Success_When_EnemyThere() {
        board.move(A1, A4, Team.BLACK);
        assertThat(board.get(A4)).isEqualTo(rook);
        assertThatIllegalArgumentException().isThrownBy(() ->
                board.get(A1)
        ).withMessage("기물이 존재하지 않습니다.");
    }

    @Test
    void move_Fail() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                board.move(A1, A5, Team.BLACK)
        ).withMessage("아군 기물이 위치하고 있습니다.");
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

        assertThatCode(() -> board.move(C7, C5, Team.BLACK))
            .doesNotThrowAnyException();
    }

    @Test
    void pawnMove_Success_When_White() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Pawn(A2, Team.WHITE));

        Board board = Board.of(setter);

        assertThatCode(() -> board.move(A2, A3, Team.WHITE))
                .doesNotThrowAnyException();
    }

    @Test
    void pawnMove_Throw_Exception_When_TryFrontAttack() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Pawn(A2, Team.BLACK));
        setter.put(A3, new Pawn(A3, Team.WHITE));

        Board board = Board.of(setter);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(A2, A3, Team.BLACK))
                .withMessage("폰은 전방의 적을 공격할 수 없습니다.");
    }

    @Test
    void pawnMove_Throw_Exception_When_TryDiagonalMoveToEmptyPosition() {
        Map<Position, Piece> setter = new LinkedHashMap<>();
        setter.put(A2, new Pawn(A2, Team.BLACK));

        Board board = Board.of(setter);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> board.move(A2, B3, Team.BLACK))
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