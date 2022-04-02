package chess.domain;

import static chess.domain.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Piece;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceConverterTest {

    @Test
    @DisplayName("name이 null이 들어오면 예외발생")
    void parseToPieceExceptionByNullName() {
        assertThatThrownBy(() -> PieceConverter.parseToPiece(null, WHITE))
                .isInstanceOf(NullPointerException.class)
                .hasMessage("name은 null이 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("이름과 color를 받아 Piece를 생성")
    void parseToPiece() {
        Piece piece = PieceConverter.parseToPiece("king", WHITE);
        assertThat(piece).isInstanceOf(Piece.class);
    }
}
