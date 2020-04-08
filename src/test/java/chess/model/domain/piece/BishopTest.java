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

public class BishopTest {

    @Test
    @DisplayName("Null이 of에 들어갔을 때 예외 발생")
    void validNotNull() {
        assertThatThrownBy(() -> Bishop.getPieceInstance(null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Null");
    }

    @ParameterizedTest
    @ValueSource(strings = {"c1", "e1", "c3", "b4", "a5", "e3", "f4", "g5", "h6"})
    @DisplayName("말의 위치(비숍)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeBishop(String input) {
        Piece piece = Bishop.getPieceInstance(Color.WHITE);
        Set<BoardSquare> availableBoardSquares = piece.getAllCheatSheet(BoardSquare.of("d2"));
        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(9);
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "d7", "b5", "d5", "a4", "e4", "e8"})
    @DisplayName("판의 정보를 가져와서 bishop이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableBishopSquareTest(String input) {
        Map<BoardSquare, Piece> board = new HashMap<>();
        board.put(BoardSquare.of("b7"), Pawn.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("c7"), Pawn.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("a6"), King.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("c5"), Pawn.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("e8"), Knight.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("f6"), Queen.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("f3"), Pawn.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("g6"), King.getPieceInstance(Color.BLACK));
        board.put(BoardSquare.of("g2"), Pawn.getPieceInstance(Color.WHITE));

        Piece piece = Bishop.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getCheatSheet(BoardSquare.of("c6"), board);

        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(7);
    }


}
