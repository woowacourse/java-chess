package domain.piece.objects;

import domain.Board;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.objects.Pawn;
import domain.piece.objects.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {
    @DisplayName("Queen은 전후좌우, 대각선으로 칸수 제한없이 움직일 수 있다.(빈칸일 경우)")
    @ParameterizedTest
    @ValueSource(strings = {"d5", "e6", "f3", "f5", "c4", "d3", "g4"})
    void queen_move_if_empty(String endPosition) {
        Queen queen = Queen.of("Q", true);
        Board board = new Board(new HashMap<Position, Piece>(){{
            put(Position.of("e4"), queen);
        }});

        assertThat(queen.canMove2(board.getBoard(), Position.of("e4"), Position.of(endPosition))).isTrue();
    }

    @DisplayName("Queen은 전후좌우, 대각선으로 칸수 제한없이 움직일 수 있다.(적 기물이 있는 경우)")
    @Test
    void queen_move_if_enemy_exist() {
        Queen queen = Queen.of("Q", true);
        Board board = new Board(new HashMap<Position, Piece>(){{
            put(Position.of("e4"), queen);
            put(Position.of("e2"), Queen.of("q", false));
        }});
        assertThat(queen.canMove2(board.getBoard(), Position.of("e4"), Position.of("e2"))).isTrue();
    }

    @DisplayName("Queen은 전후좌우, 대각선으로 움직일 수 없다.(같은 색의 기물이 있는 경우)")
    @Test
    void queen_cant_move_if_same_piece_exist() {
        Queen queen = Queen.of("Q", true);
        Board board = new Board(new HashMap<Position, Piece>(){{
            put(Position.of("e5"), queen);
            put(Position.of("g7"), Pawn.of("P", true));
        }});
        assertThat(queen.canMove2(board.getBoard(), Position.of("e5"), Position.of("g7"))).isFalse();
    }

    @DisplayName("Queen은 전후좌우, 대각선 이외의 위치로 움직일 수 없다.")
    @ParameterizedTest
    @ValueSource(strings = {"d6", "f2", "d2", "f6", "c5", "c3", "g5", "g3"})
    void queen_move_fail_test(String endPosition) {
        Queen queen = Queen.of("Q", true);
        Board board = new Board(new HashMap<Position, Piece>(){{
            put(Position.of("e4"), queen);
        }});
        assertThat(queen.canMove2(board.getBoard(), Position.of("e4"), Position.of(endPosition))).isFalse();
    }

    @DisplayName("Queen이 이동하려는 경로에 다른 말이 있으면, 실패를 반환한다.")
    @Test
    void cant_move_queen_if_piece_exist() {
        Queen queen = Queen.of("Q", true);
        Board board = new Board(new HashMap<Position, Piece>(){{
            put(Position.of("e4"), queen);
            put(Position.of("e5"), Pawn.of("P", true));
            put(Position.of("f3"), Pawn.of("p", false));
        }});
        assertThat(queen.canMove2(board.getBoard(), Position.of("e4"), Position.of("e6"))).isFalse();
        assertThat(queen.canMove2(board.getBoard(), Position.of("e4"), Position.of("g2"))).isFalse();
    }
}