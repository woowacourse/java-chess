package chess.webdao;

import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DAOtoPieceTest {
    @Test
    @DisplayName("DB에서 넘겨받은 기물 정보에 대응하여, Domain Piece를 정상 생성하는지 확인")
    void DAOtoPiece() throws SQLException {
        Piece piece = DAOtoPiece.generatePiece("white", "Knight", true);
        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(piece.isFirstMove()).isTrue();

        piece = DAOtoPiece.generatePiece("black", "Queen", false);
        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(piece.isFirstMove()).isFalse();

        piece = DAOtoPiece.generatePiece("black", "Pawn", true);
        assertThat(piece).isInstanceOf(Pawn.class);
        assertThat(piece.isFirstMove()).isTrue();
        assertThat(piece.equals(new Pawn(-1)));
    }
}
