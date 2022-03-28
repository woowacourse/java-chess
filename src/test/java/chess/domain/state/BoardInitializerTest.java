package chess.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.state.BoardInitializer;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardInitializerTest {

    @DisplayName("체스판 초기화 시 모든 기물이 제자리에 생성되었는지 확인")
    @Test
    void initializeBoard() {
        Board board = new Board(BoardInitializer.initBoard());

        List<List<String>> signatures = List.of(
                List.of("R", "N", "B", "Q", "K", "B", "N", "R"),
                List.of("P", "P", "P", "P", "P", "P", "P", "P"),
                List.of("·", "·", "·", "·", "·", "·", "·", "·"),
                List.of("·", "·", "·", "·", "·", "·", "·", "·"),
                List.of("·", "·", "·", "·", "·", "·", "·", "·"),
                List.of("·", "·", "·", "·", "·", "·", "·", "·"),
                List.of("p", "p", "p", "p", "p", "p", "p", "p"),
                List.of("r", "n", "b", "q", "k", "b", "n", "r")
        );

        for (int i = 0; i < 8; i++) {
            assertThat(board.getRank(7 - i).getPieces().stream()
                    .map(Piece::getSignature)
                    .collect(Collectors.toList())
            ).isEqualTo(signatures.get(i));
        }
    }
}
