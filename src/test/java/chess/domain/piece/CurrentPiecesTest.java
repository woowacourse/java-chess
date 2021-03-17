package chess.domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrentPiecesTest {

    @DisplayName("CurrentPieces 객체 생성 확인")
    @Test
    void 현재_기물_객체_생성() {
        CurrentPieces currentPieces = CurrentPieces.generate();
        assertThat(currentPieces.getCurrentPieces().size()).isEqualTo(32);
    }

    @DisplayName("해당 위치에 있는 기물을 찾는다.")
    @Test
    void 해당_위치에_있는_기물_찾기() {
        CurrentPieces currentPieces = CurrentPieces.generate();
        Piece sourcePiece = currentPieces.findByPosition(Position.of('e', '8'));

        assertThat(sourcePiece).isInstanceOf(King.class);
    }

    @DisplayName("해당 위치에 있는 기물을 찾는다. - 없을 경우 Empty")
    @Test
    void 해당_위치에_있는_기물_찾기_EMPTY() {
        CurrentPieces currentPieces = CurrentPieces.generate();
        Piece sourcePiece = currentPieces.findByPosition(Position.of('e', '4'));

        assertThat(sourcePiece).isInstanceOf(Empty.class);
    }
}
