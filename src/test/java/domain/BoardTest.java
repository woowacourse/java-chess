package domain;

import domain.piece.objects.Empty;
import domain.piece.objects.King;
import domain.piece.objects.Piece;
import domain.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BoardTest {
    private Map<Position, Piece> pieces;

    @DisplayName("2개의 king을 peices에 담는다.")
    @BeforeEach
    void setUp() {
        pieces = new HashMap<Position, Piece>() {
            {
                put(Position.of("a7"), King.of("K", true));
                put(Position.of("a1"), King.of("k", false));
            }
        };
    }

    @DisplayName("정상적인 체스 기물이 들어오는 경우 체스 보드판이 생성된다.")
    @Test
    void board_generate() {
        Board board = new Board(pieces);
        assertThat(board.getPiece(Position.of("a7"))).isEqualTo(King.of("K", true));
        assertThat(board.getPiece(Position.of("a1"))).isEqualTo(King.of("k", false));
    }

    @DisplayName("a7의 체스 기물(king)을 b6로 옮긴다.")
    @Test
    void piece_move_on_board() {
        Board board = new Board(pieces);
        board.move(Position.of("a7"), Position.of("b6"));
        assertThat(board.getPiece(Position.of("b6"))).isEqualTo(King.of("K", true));
    }

    @DisplayName("이동하기 위해 선택한 기물의 색깔이 현재 유저의 색깔과 일치하는 지 검사한다.")
    @Test
    void correct_user_turn_check() {
        Board board = new Board(pieces);
        assertDoesNotThrow(() -> board.checkMovable(Position.of("a7"), true));
    }

    @DisplayName("빈 칸의 기물을 가져오는 경우 empty가 반환된다.")
    @Test
    void get_piece_in_board_if_piece_not_exist() {
        Board board = new Board(pieces);
        assertThat(board.getPiece(Position.of("a6"))).isInstanceOf(Empty.class);
    }
}