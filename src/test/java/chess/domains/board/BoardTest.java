package chess.domains.board;

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
}