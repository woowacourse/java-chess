package chess.domain;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
class InitialPieceTest {

    @Test
    @DisplayName("같은 색, 같은 종류의 말이면 같은 객체이다.")
    void 같은_색_같은_종류_말_캐싱() {
        Map<Position, Piece> piecesWithPosition = InitialPiece.getPiecesWithPosition();
        Position firstWhiteRookPosition = Position.from("A1");
        Position secondWhiteRookPosition = Position.from("H1");

        assertThat(piecesWithPosition.get(firstWhiteRookPosition)).isSameAs(
            piecesWithPosition.get(secondWhiteRookPosition));
    }

    @Test
    @DisplayName("다른 색이면 같은 종류의 말이어도 다른 객체이다.")
    void 다른_색_같은_종류_말_캐싱() {
        Map<Position, Piece> piecesWithPosition = InitialPiece.getPiecesWithPosition();
        Position blackRookPosition = Position.from("H8");
        Position whiteRookPosition = Position.from("H1");

        assertThat(piecesWithPosition.get(whiteRookPosition)).isNotSameAs(
            piecesWithPosition.get(blackRookPosition));
    }

}