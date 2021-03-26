package chess.domain.piece;

import chess.domain.board.Position;
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

    @DisplayName("말 초기 위치 값 리스트 테스트")
    @Test
    void bringInitialPositions() {
        List<Position> initialYPositions = Arrays.asList(
            Position.of('a', 2),
            Position.of('b',2),
            Position.of('c',2),
            Position.of('d',2),
            Position.of('e',2),
            Position.of('f',2),
            Position.of('g',2),
            Position.of('h',2)
        );
        InitialLocation pawnInitialLocation = InitialLocation.matchPiece(PieceKind.PAWN);
        List<Position> expectedInitialYPositions = pawnInitialLocation.bringPositions();

        assertTrue(initialYPositions.containsAll(expectedInitialYPositions));
    }
}