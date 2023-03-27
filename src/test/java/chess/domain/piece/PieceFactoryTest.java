package chess.domain.piece;

import chess.domain.piece.pawn.BlackPawn;
import chess.domain.piece.pawn.WhitePawn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceFactoryTest {

    @Test
    @DisplayName("흰 폰 생성 테스트")
    void create_white_pawn() {
        assertThat(PieceFactory.create(PieceType.PAWN, Color.WHITE))
                .isInstanceOf(WhitePawn.class);
    }

    @Test
    @DisplayName("검정 폰 생성 테스트")
    void create_black_pawn() {
        assertThat(PieceFactory.create(PieceType.PAWN, Color.BLACK))
                .isInstanceOf(BlackPawn.class);
    }

    @Test
    @DisplayName("흰 나이트 생성 테스트")
    void create_white_knight() {
        Piece piece = PieceFactory.create(PieceType.KNIGHT, Color.WHITE);

        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    @DisplayName("검정 나이트 생성 테스트")
    void create_black_knight() {
        Piece piece = PieceFactory.create(PieceType.KNIGHT, Color.BLACK);

        assertThat(piece).isInstanceOf(Knight.class);
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 비숍 생성 테스트")
    void create_white_bishop() {
        Piece piece = PieceFactory.create(PieceType.BISHOP, Color.WHITE);

        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    @DisplayName("검정 비숍 생성 테스트")
    void create_black_bishop() {
        Piece piece = PieceFactory.create(PieceType.BISHOP, Color.BLACK);

        assertThat(piece).isInstanceOf(Bishop.class);
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 룩 생성 테스트")
    void create_white_rook() {
        Piece piece = PieceFactory.create(PieceType.ROOK, Color.WHITE);

        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    @DisplayName("검정 룩 생성 테스트")
    void create_black_rook() {
        Piece piece = PieceFactory.create(PieceType.ROOK, Color.BLACK);

        assertThat(piece).isInstanceOf(Rook.class);
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 퀸 생성 테스트")
    void create_white_queen() {
        Piece piece = PieceFactory.create(PieceType.QUEEN, Color.WHITE);

        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    @DisplayName("검정 퀸 생성 테스트")
    void create_black_queen() {
        Piece piece = PieceFactory.create(PieceType.QUEEN, Color.BLACK);

        assertThat(piece).isInstanceOf(Queen.class);
        assertThat(piece.isBlack()).isTrue();
    }

    @Test
    @DisplayName("흰 킹 생성 테스트")
    void create_white_king() {
        Piece piece = PieceFactory.create(PieceType.KING, Color.WHITE);

        assertThat(piece).isInstanceOf(King.class);
        assertThat(piece.isWhite()).isTrue();
    }

    @Test
    @DisplayName("검정 킹 생성 테스트")
    void create_black_king() {
        Piece piece = PieceFactory.create(PieceType.KING, Color.BLACK);

        assertThat(piece).isInstanceOf(King.class);
        assertThat(piece.isBlack()).isTrue();
    }
}