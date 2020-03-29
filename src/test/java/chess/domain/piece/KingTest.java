package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.BoardSquare;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"e6", "e8", "f6", "f7", "f8", "d6", "d7", "d8"})
    @DisplayName("말의 위치(king)를 받고 말의 종류에 따라 이동할 수 있는 칸 리스트 반환")
    void calculateScopeKing(String input) {
        Piece piece = King.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getAllCheatSheet(BoardSquare.of("e7"));
        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a5", "a7", "b5", "b7"})
    @DisplayName("판의 정보를 가져와서 king이 갈 수 있는 칸에 장애물이 있는지 판단하여 이동할 수 있는 리스트 반환하는 테스트")
    void movableKingSquareTest(String input) {
        Map<BoardSquare, Piece> board = new HashMap<>();
        board.put(BoardSquare.of("a5"), Pawn.getPieceInstance(Color.WHITE));
        board.put(BoardSquare.of("b6"), Pawn.getPieceInstance(Color.BLACK));
        Piece piece = King.getPieceInstance(Color.BLACK);
        Set<BoardSquare> availableBoardSquares = piece.getCheatSheet(BoardSquare.of("a6"), board);

        assertThat(availableBoardSquares.contains(BoardSquare.of(input))).isTrue();
        assertThat(availableBoardSquares.size()).isEqualTo(4);
    }

}
