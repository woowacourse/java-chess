package chess.model.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.board.BoardSquare;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KnightTest {

    @Test
    @DisplayName("Null이 of에 들어갔을 때 예외 발생")
    void validNotNull() {
        assertThatThrownBy(() -> Knight.getPieceInstance(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b1", "a2", "b5", "a4", "e4", "d1", "d5", "e2"})
    @DisplayName("말의 위치(knight)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKnight(String input) {
        Piece piece = Knight.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getAllCheatSheet(BoardSquare.of("c3"));
        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c4", "d5", "d1", "f1", "f5", "g2"})
    @DisplayName("판의 정보를 가져와서 나이트가 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKnightSquareTest(String input) {
        Map<BoardSquare, Piece> board = new HashMap<>();
        board.put(BoardSquare.of("d5"), King.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("c2"), Queen.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("g4"), Pawn.getPieceInstance(Color.WHITE));
        Piece piece = Knight.getPieceInstance(Color.WHITE);
        Set<BoardSquare> availableBoardSquares = piece.getCheatSheet(BoardSquare.of("e3"), board);
        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(6);
    }

    @Test
    @DisplayName("두 동일한 객체를 가져왔을 때 같은지 확인")
    void checkSameInstance() {
        Piece piece = Knight.getPieceInstance(Color.BLACK);
        assertThat(piece).isEqualTo(Knight.getPieceInstance(Color.BLACK));
    }

    @Test
    @DisplayName("체스 말이 같은 색인지 검증하는 테스트")
    void isBlack() {
        assertThat(Knight.getPieceInstance(Color.BLACK).isSameColor(Color.BLACK)).isTrue();
        assertThat(Knight.getPieceInstance(Color.BLACK).isSameColor(Color.WHITE)).isFalse();
        assertThat(Knight.getPieceInstance(Color.WHITE).isSameColor(Color.BLACK)).isFalse();
        assertThat(Knight.getPieceInstance(Color.WHITE).isSameColor(Color.WHITE)).isTrue();
    }


}
