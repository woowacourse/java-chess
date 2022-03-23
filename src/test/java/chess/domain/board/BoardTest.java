package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Blank;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardTest {

    @DisplayName("체스판 초기화 시 모든 기물이 제자리에 생성되었는지 확인")
    @Test
    void initializeBoard() {
        Board board = new Board();
        board.initialize();

        List<List<String>> signatures = List.of(
                List.of("R", "N", "B", "Q", "K", "B", "N", "R"),
                List.of("P", "P", "P", "P", "P", "P", "P", "P"),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
                List.of(".", ".", ".", ".", ".", ".", ".", "."),
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

    @DisplayName("말 이동 명령 성공 시 원래 자리에 빈 칸이 생성된다.")
    @Test
    void move() {
        Board board = new Board();
        board.initialize();
        board.move(new Position("b8"), new Position("a6"));

        assertThat(board.getPiece(new Position("b8")))
                .isInstanceOf(Blank.class);
        assertThat(board.getPiece(new Position("a6")))
                .isInstanceOf(Knight.class);
    }
}
