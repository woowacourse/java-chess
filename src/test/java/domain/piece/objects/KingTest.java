package domain.piece.objects;

import domain.Board;
import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("King은 상하좌우 모든 대각선 방향으로 1칸 이동할 수 있다.(목적지가 빈칸일 경우)")
    @ParameterizedTest
    @ValueSource(strings = {"f4", "e3", "d4", "e5", "f5", "f3", "d5", "d3"})
    void king_move(String endPosition) {
        King king = King.of("K", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), King.of("K", true));
        }});
        assertThat(king.canMove(board.getPieceMap(), Position.of("e4"), Position.of(endPosition))).isTrue();
    }

    @DisplayName("King은 상하좌우 모든 대각선 방향으로 1칸 이동할 수 있다.(목적지에 적의 기물이 있을 경우)")
    @Test
    void cant_move_king_if_different_color_piece_exist() {
        King king = King.of("K", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), King.of("K", true));
            put(Position.of("f4"), Pawn.of("p", false));
            put(Position.of("d3"), Pawn.of("p", false));
        }});
        assertThat(king.canMove(board.getPieceMap(), Position.of("e4"), Position.of("f4"))).isTrue();
        assertThat(king.canMove(board.getPieceMap(), Position.of("e4"), Position.of("d3"))).isTrue();
    }

    @DisplayName("같은 편 말이 존재하는 위치로 이동할 수 없다.")
    @Test
    void cant_move_king_if_same_color_piece_exist() {
        King king = King.of("K", true);
        Board board = new Board(new HashMap<Position, Piece>() {{
            put(Position.of("e4"), King.of("K", true));
            put(Position.of("f4"), Pawn.of("P", true));
            put(Position.of("d3"), Pawn.of("P", true));
        }});
        assertThat(king.canMove(board.getPieceMap(), Position.of("e4"), Position.of("f4"))).isFalse();
        assertThat(king.canMove(board.getPieceMap(), Position.of("e4"), Position.of("d3"))).isFalse();
    }
}