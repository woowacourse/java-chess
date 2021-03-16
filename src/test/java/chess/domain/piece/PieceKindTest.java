package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PieceKindTest {

    @DisplayName("체스 말 이름 getter 테스트")
    @Test
    void getName_pieceName_NotNull() {
        PieceColor blackPieceColor = PieceColor.BLACK;
        PieceColor whitePieceColor = PieceColor.WHITE;

        String blackPieceSymbol = PieceKind.KING.getName(blackPieceColor);
        String whitePieceSymbol = PieceKind.BISHOP.getName(whitePieceColor);

        assertEquals(blackPieceSymbol, "K");
        assertEquals(whitePieceSymbol, "b");
    }

    @DisplayName("말 초기 X 값 리스트 테스트")
    @Test
    void bringInitialXPositions() {
        List<Character> initialXPositions = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');
        List<Character> expectedInitialXPositions = PieceKind.PAWN.bringInitialXPositions();

        assertTrue(initialXPositions.containsAll(expectedInitialXPositions));
    }

    @DisplayName("말 초기 Y 값 리스트 테스트")
    @Test
    void bringInitialYPositions() {
        List<Integer> initialYPositions = Arrays.asList(2);
        List<Integer> expectedInitialYPositions = PieceKind.PAWN.bringInitialYPositions();

        assertTrue(initialYPositions.containsAll(expectedInitialYPositions));
    }
}