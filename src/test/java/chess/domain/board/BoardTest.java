package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.position.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class BoardTest {
    @DisplayName("보드 생성 실패")
    @Test
    void constructFail() {
        Map<Position, Piece> initializedPieces = new HashMap<>();
        for (int row = 8; row >= 1; row--) {
            for (int col = 1; col <= 7; col++) {
                String position = (char)(col + 96) + String.valueOf(row);
                initializedPieces.put(Position.of(position), Piece.of(PieceType.BLANK));
            }
        }
        assertThatThrownBy(() -> {
            Board board = new Board(initializedPieces);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("보드가 제대로 생성되지 못했습니다.");
    }

    @DisplayName("피스 위치 변경")
    @ParameterizedTest
    @MethodSource("getCasesForPieceMove")
    void movePiece(String from, String to, Object fromPiece, Object toPiece) {
        Board board = BoardFactory.createBoard();
        board.initialize();
        board.move(from, to);
        assertThat(board.findBy(Position.of(from)).getClass()).isEqualTo(toPiece);
        assertThat(board.findBy(Position.of(to)).getClass()).isEqualTo(fromPiece);
    }

    private static Stream<Arguments> getCasesForPieceMove() {
        return Stream.of(
                Arguments.of("a2", "a4", Pawn.class, Blank.class),
                Arguments.of("b1", "c3", Knight.class, Blank.class)
        );
    }
}
