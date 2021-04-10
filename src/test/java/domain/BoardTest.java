package domain;

import domain.piece.objects.*;
import domain.piece.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BoardTest {
    private Map<Position, Piece> pieces = new HashMap<>();

    @DisplayName("bishop이 이동할 때 Board에서도 정상적인 이동임을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"c8:B:true:e6", "e5:B:true:c7", "b4:B:true:d6", "f4:B:true:d2"}, delimiter = ':')
    void bishop_move_on_board(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), Bishop.of(name, color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Bishop.of(name, color));
    }

    @DisplayName("Bishop이 경로 사이에 기물이 있는 경우 Board에서 실패한다.")
    @ParameterizedTest
    @CsvSource(value = {"c7:B:true:d6:P:true:e5", "f4:B:true:d6:P:true:c7", "f6:B:true:e5:p:false:b2"}, delimiter = ':')
    void bishop_cant_move_on_board(String start, String name, boolean color,
                                   String start2, String name2, boolean color2, String destination) {
        pieces.put(Position.of(start), Bishop.of(name, color));
        pieces.put(Position.of(start2), Pawn.of(name2, color2));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(destination), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(destination))).isInstanceOf(Empty.class);
    }

    @DisplayName("bishop은 목적지에 같은 색깔의 기물이 있는 경우 이동할 수 없다.")
    @Test
    void cant_move_bishop_if_same_color_piece_exist() {
        pieces.put(Position.of("e5"), Bishop.of("B", true));
        pieces.put(Position.of("g3"), Pawn.of("P", true));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of("e5"), Position.of("g3"), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("B은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of("g3"))).isEqualTo(Pawn.of("P", true));
    }

    @DisplayName("bishop은 목적지에 적 기물이 있을 경우 목적지로 이동한다.")
    @Test
    void check_diagonal_if_enemy_piece() {
        Bishop bishop = Bishop.of("B", true);
        pieces.put(Position.of("c5"), bishop);
        pieces.put(Position.of("d4"), Pawn.of("p", false));
        Board board = new Board(pieces);
        board.move(Position.of("c5"), Position.of("d4"), true);
        assertThat(board.getPiece(Position.of("d4"))).isEqualTo(bishop);
    }

    @DisplayName("Queen이 이동할 때 Board에서도 정상적인 이동임을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"d6:Q:true:b8", "e5:Q:true:g7", "e5:Q:true:e6", "d4:Q:true:g1",
            "c5:Q:true:a3", "d6:Q:true:a6"}, delimiter = ':')
    void queen_move_on_board(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), Queen.of(name, color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Queen.of(name, color));
    }

    @DisplayName("Queen은 목적지에 적 기물이 있을 경우 목적지로 이동한다.")
    @Test
    void queen_move_if_enemy_exist() {
        Queen queen = Queen.of("Q", true);
        pieces.put(Position.of("d4"), queen);
        pieces.put(Position.of("f6"), Pawn.of("p", false));
        Board board = new Board(pieces);
        board.move(Position.of("d4"), Position.of("f6"), true);
        assertThat(board.getPiece(Position.of("f6"))).isEqualTo(queen);
    }

    @DisplayName("Queen은 목적지에 같은 색깔의 기물이 있는 경우 이동할 수 없다.")
    @Test
    void queen_cant_move_if_same_piece_exist() {
        Queen queen = Queen.of("Q", true);
        pieces.put(Position.of("e5"), queen);
        pieces.put(Position.of("g7"), Pawn.of("P", true));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of("e5"), Position.of("g7"), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Q은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of("g7"))).isEqualTo(Pawn.of("P", true));
    }

    @DisplayName("Queen이 이동할 때 기물이 있는 경우 Board에서 실패한다.")
    @ParameterizedTest
    @CsvSource(value = {"d4:Q:true:d5:P:true:d7", "d4:Q:true:f2:p:false:g1", "e5:Q:true:c5:p:false:b5"}, delimiter = ':')
    void queen_cant_move_on_board(String start, String name, boolean color,
                                  String start2, String name2, boolean color2, String destination) {
        pieces.put(Position.of(start), Queen.of(name, color));
        pieces.put(Position.of(start2), Pawn.of(name2, color2));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(destination), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(destination))).isInstanceOf(Empty.class);
    }

    @DisplayName("Rook이 이동할 때 Board에서도 정상적인 이동임을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"d6:R:true:d8", "f4:R:true:c4", "c5:R:true:c2", "d2:R:true:g2"}, delimiter = ':')
    void rook_move_on_board(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), Rook.of(name, color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Rook.of(name, color));
    }

    @DisplayName("Rook이 이동할 때 장애물이 있는 경우 Board에서 실패한다.")
    @ParameterizedTest
    @CsvSource(value = {"d6:R:true:d7:P:true:d8", "f4:R:true:d4:p:false:c4",
            "c5:R:true:c3:P:true:c2", "d2:R:true:f2:p:false:g2"}, delimiter = ':')
    void rook_cant_move_on_board(String start, String name, boolean color,
                                 String start2, String name2, boolean color2, String destination) {
        pieces.put(Position.of(start), Rook.of(name, color));
        pieces.put(Position.of(start2), Pawn.of(name2, color2));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(destination), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(destination))).isInstanceOf(Empty.class);
    }

    @DisplayName("Rook은 목적지에 같은 색깔의 기물이 있는 경우 이동할 수 없다.")
    @Test
    void cant_move_rook_if_same_color_piece_exists() {
        Rook rook = Rook.of("R", true);
        pieces.put(Position.of("d6"), rook);
        pieces.put(Position.of("d7"), Queen.of("Q", true));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of("d6"), Position.of("d7"), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("R은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of("d7"))).isEqualTo(Queen.of("Q", true));
    }

    @Test
    @DisplayName("Rook은 목적지에 적 기물이 있을 경우 목적지로 이동한다.")
    void rook_move_if_enemy_exist() {
        Rook rook = Rook.of("R", true);
        pieces.put(Position.of("d6"), rook);
        pieces.put(Position.of("d7"), Pawn.of("p", false));
        Board board = new Board(pieces);
        board.move(Position.of("d6"), Position.of("d7"), true);
        assertThat(board.getPiece(Position.of("d7"))).isEqualTo(rook);
    }

    @DisplayName("King이 이동할 때 Board에서도 정상적인 이동임을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"e4:K:true:f4", "e4:K:true:e3", "e4:K:true:d4", "e4:K:true:e5",
            "e4:K:true:f5", "e4:K:true:f3", "e4:K:true:d5", "e4:K:true:d3"}, delimiter = ':')
    void king_move_on_board(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), King.of(name, color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(King.of(name, color));
    }

    @DisplayName("King이 이동할 때 목적지에 같은 팀이 있으면 실패한다.")
    @ParameterizedTest
    @CsvSource(value = {"e4:K:true:f4:P:true:f4", "e4:K:true:e3:P:true:e3", "e4:K:true:d4:P:true:d4"}, delimiter = ':')
    void king_cant_move_on_board(String start, String name, boolean color,
                                 String start2, String name2, boolean color2, String destination) {
        pieces.put(Position.of(start), King.of(name, color));
        pieces.put(Position.of(start2), Pawn.of(name2, color2));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(destination), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
    }

    @DisplayName("King은 목적지에 적 기물이 있을 경우 목적지로 이동한다.")
    @ParameterizedTest
    @ValueSource(strings = {"f4", "d3"})
    void cant_move_king_if_different_color_piece_exist(String end) {
        King king = King.of("K", true);
        pieces.put(Position.of("e4"), king);
        pieces.put(Position.of(end), Pawn.of("p", false));
        Board board = new Board(pieces);
        board.move(Position.of("e4"), Position.of(end), true);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(king);
    }

    @DisplayName("Knight가 이동할 때 Board에서도 정상적인 이동임을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"e4:N:true:d6", "e4:N:true:c5", "e4:N:true:f6", "e4:N:true:g5",
            "e4:N:true:c3", "e4:N:true:d2", "e4:N:true:f2", "e4:N:true:g3"}, delimiter = ':')
    void knight_move_on_board(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), Knight.of(name, color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Knight.of(name, color));
    }

    @DisplayName("Knight가 이동할 때 목적지에 같은 팀이 있으면 실패한다.")
    @ParameterizedTest
    @CsvSource(value = {"e4:N:true:d6:P:true:d6", "e4:N:true:c5:P:true:c5", "e4:N:true:f6:P:true:f6",
            "e4:N:true:c3:P:true:c3", "e4:N:true:d2:P:true:d2", "e4:N:true:g3:P:true:g3"}, delimiter = ':')
    void knight_cant_move_on_board(String start, String name, boolean color,
                                   String start2, String name2, boolean color2, String destination) {
        pieces.put(Position.of(start), Knight.of(name, color));
        pieces.put(Position.of(start2), Pawn.of(name2, color2));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(destination), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(destination))).isEqualTo(Pawn.of(name2, color2));
    }

    @DisplayName("Knight은 목적지에 적 기물이 있을 경우 목적지로 이동한다.")
    @ParameterizedTest
    @ValueSource(strings = {"d2", "f2"})
    void knight_move_if_different_color_piece(String end) {
        Knight knight = Knight.of("N", true);
        pieces.put(Position.of("e4"), knight);
        pieces.put(Position.of(end), Pawn.of("p", false));
        Board board = new Board(pieces);
        board.move(Position.of("e4"), Position.of(end), true);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(knight);
    }

    @DisplayName("Pawn이 이동할 때 Board에서도 정상적인 이동임을 확인한다.")
    @ParameterizedTest
    @CsvSource(value = {"a7:P:true:a6", "a7:P:true:a5", "f2:p:false:f4"}, delimiter = ':')
    void pawn_move_on_board(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), Pawn.of(name, color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Pawn.of(name, color));
    }

    @DisplayName("Pawn이 대각선 이동할 때 상대팀 기물이 있는 경우 이동할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"c6:P:p:true:d5", "c6:P:p:true:b5", "e4:p:P:false:d5"}, delimiter = ':')
    void pawn_can_move_diagonal(String start, String name, String name2, boolean color, String end) {
        pieces.put(Position.of(start), Pawn.of(name, color));
        pieces.put(Position.of(end), Pawn.of(name2, !color));
        Board board = new Board(pieces);
        board.move(Position.of(start), Position.of(end), color);
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Pawn.of(name, color));
    }

    @DisplayName("Pawn이 대각선 이동할 때 상대팀 기물이 없는 경우 이동할 수 없다.")
    @ParameterizedTest
    @CsvSource(value = {"c6:P:true:d5", "c6:P:true:b5", "e4:p:false:d5"}, delimiter = ':')
    void pawn_cant_move_diagonal(String start, String name, boolean color, String end) {
        pieces.put(Position.of(start), Pawn.of(name, color));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(end), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(end))).isInstanceOf(Empty.class);
    }

    @DisplayName("Pawn은 최초 위치가 아닐경우 2칸 전진 이동이 불가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"P:true:c6:c4", "p:false:f4:f6"}, delimiter = ':')
    void pawn_cant_move_two_step_if_not_initial_position(String name, boolean color, String start, String end) {
        pieces.put(Position.of(start), Pawn.of(name, color));
        Board board = new Board(pieces);
        assertThatThrownBy(()->board.move(Position.of(start), Position.of(end), color))
                .isInstanceOf(RuntimeException.class)
                .hasMessage(name + "은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(end))).isInstanceOf(Empty.class);
    }

    @DisplayName("Pawn 전진 이동할 때 목적지에 빈칸이 아니면 실패한다.")
    @ParameterizedTest
    @CsvSource(value = {"a7:a6", "a7:a5"}, delimiter = ':')
    void pawn_move_forward_if_same_color_piece(String start, String end) {
        Pawn pawn = Pawn.of("P", true);
        pieces.put(Position.of(start), pawn);
        pieces.put(Position.of(end), Rook.of("R", true));
        Board board = new Board(pieces);
        assertThatThrownBy(() -> board.move(Position.of(start), Position.of(end), true))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("P은 선택된 위치로 이동할 수 없습니다.");
        assertThat(board.getPiece(Position.of(end))).isEqualTo(Rook.of("R", true));
    }

    @DisplayName("빈 칸의 기물을 가져오는 경우 empty가 반환된다.")
    @Test
    void get_piece_in_board_if_piece_not_exist() {
        Board board = new Board(pieces);
        assertThat(board.getPiece(Position.of("a6"))).isInstanceOf(Empty.class);
    }
}