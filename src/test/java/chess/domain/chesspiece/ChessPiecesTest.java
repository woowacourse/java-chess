package chess.domain.chesspiece;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ChessPiecesTest {

    @Test
    void find_잘못된_이름() {
        String wrongName = "A";
        assertThrows(InvalidChessPieceNameException.class, () -> ChessPieces.getInstance().find(wrongName, true));
    }

    @Test
    void find_WhitePawn() {
        String name = "P";
        boolean isWhiteTeam = true;

        assertThat(ChessPieces.getInstance().find(name, isWhiteTeam)).isEqualTo(WhitePawn.getInstance());
    }

    @Test
    void find_BlackPawn() {
        String name = "P";
        boolean isWhiteTeam = false;

        assertThat(ChessPieces.getInstance().find(name, isWhiteTeam)).isEqualTo(BlackPawn.getInstance());
    }

    @Test
    void find_폰이_아닌_다른_말들() {
        String name = "K";
        boolean isWhiteTeam = false;

        assertThat(ChessPieces.getInstance().find(name, isWhiteTeam)).isEqualTo(King.getInstance());
    }
}