package domain;

import domain.piece.PiecesGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class ChessBoardTest {

    @DisplayName("보드 정보를 통해 체스보드를 생성한다")
    @Test
    void createChessBoard() {
        assertThatCode(() -> new ChessBoard(PiecesGenerator.getInstance()))
                .doesNotThrowAnyException();
    }
}
