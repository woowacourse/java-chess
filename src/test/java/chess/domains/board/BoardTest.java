package chess.domains.board;

import chess.domains.piece.Pawn;
import chess.domains.piece.PieceColor;
import chess.domains.piece.Rook;
import chess.domains.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoardTest {
    private static Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @DisplayName("입력한 위치의 말을 찾는 기능 확인")
    @Test
    void findPiece_1() {
        PlayingPiece piece = board.findPiece("a1");

        PlayingPiece playingPiece = new PlayingPiece(Position.ofPositionName("a1"), new Rook(PieceColor.WHITE));
        assertThat(piece).isEqualTo(playingPiece);
    }

    @DisplayName("경로에 있는 말이 모두 Blank일 경우 true 반환")
    @ParameterizedTest
    @CsvSource(value = {"a3, a4, true", "a2, a3, false"})
    void canGo(String route1, String route2, boolean expected) {
        List<Position> route = new ArrayList<>(Arrays.asList(
                Position.ofPositionName(route1),
                Position.ofPositionName(route2)
        ));

        boolean actual = board.canGo(route);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("예외 테스트: 이동하려는 위치에 말이 본인의 말인 경우 예외 발생")
    @Test
    void move_1() {
        Position source = Position.ofPositionName("a1");
        Position target = Position.ofPositionName("a2");

        PlayingPiece sourcePiece = board.findPiece(source);
        PlayingPiece targetPiece = board.findPiece(target);

        assertThatThrownBy(() -> board.move(sourcePiece, targetPiece))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("자신의 말 위치로 이동할 수 없습니다.");
    }

    @DisplayName("이동하려는 위치에 Blank가 있는 경우 이동 테스트")
    @Test
    void move_2() {
        Position source = Position.ofPositionName("a2");
        Position target = Position.ofPositionName("a3");

        PlayingPiece sourcePiece = board.findPiece(source);
        PlayingPiece targetPiece = board.findPiece(target);

        board.move(sourcePiece, targetPiece);
        assertTrue(board.findPiece("a2").isBlank());
        assertThat(board.findPiece("a3").getPiece()).isEqualTo(new Pawn(PieceColor.WHITE));
    }

    @DisplayName("이동하려는 위치에 상대 말이 있는 경우 이동 테스트")
    @Test
    void move_3() {
        Position source = Position.ofPositionName("a2");
        Position target = Position.ofPositionName("a7");

        PlayingPiece sourcePiece = board.findPiece(source);
        PlayingPiece targetPiece = board.findPiece(target);

        board.move(sourcePiece, targetPiece);
        assertTrue(board.findPiece("a2").isBlank());
        assertThat(board.findPiece("a7").getPiece()).isEqualTo(new Pawn(PieceColor.WHITE));
    }
}