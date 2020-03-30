package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Path;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static chess.domain.position.Fixtures.*;
import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {
    @Test
    void create_By_Factory() {
        assertThat(BoardFactory.create()).isInstanceOf(Board.class);
    }

    @Test
    void update_Success() {
        Map<Position, Piece> map = new LinkedHashMap<>();
        Rook rook = new Rook(A1, Team.BLACK);
        map.put(A1, rook);
        map.put(A3, new Empty(A3));
        Board board = Board.of(map);

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