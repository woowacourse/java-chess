package chess.domain.piece;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PiecesTest {
    @Test
    @DisplayName("32개의 piece 위치를 초기화 한다.")
    void initPieces() {
        assertThat(Pieces.init()).hasSize(32);
    }

    @ParameterizedTest(name = "team과 piecetype 을 입력에 맞는 piece를 생성한다.")
    @CsvSource({"BLACK,PAWN","BLACK,QUEEN","BLACK,KING"})
    void createPiece(Team team, PieceType pieceType) {
        assertDoesNotThrow((() -> Pieces.of(team, pieceType)));
    }
}
