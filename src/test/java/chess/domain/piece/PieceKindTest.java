package chess.domain.piece;

import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
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
        List<XPosition> initialXPositions = Arrays.asList(XPosition.values());
        List<XPosition> expectedInitialXPositions = PieceKind.PAWN.bringInitialXPositions();

        assertTrue(initialXPositions.containsAll(expectedInitialXPositions));
    }

    @DisplayName("말 초기 Y 값 리스트 테스트")
    @Test
    void bringInitialYPositions() {
        List<YPosition> initialYPositions = Collections.singletonList(YPosition.TWO);
        List<YPosition> expectedInitialYPositions = PieceKind.PAWN.bringInitialYPositions();

        assertTrue(initialYPositions.containsAll(expectedInitialYPositions));
    }
}