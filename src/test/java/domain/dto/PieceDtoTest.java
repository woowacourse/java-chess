package domain.dto;

import domain.piece.Color;
import domain.piece.Piece;
import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.position.File;
import domain.position.Position;
import domain.position.Rank;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PieceDtoTest {
    @Test
    void 도메인에서_DTO로_변환한다() {
        Position position = new Position(File.A, Rank.ONE);
        Piece piece = new King(Color.WHITE);

        PieceDto pieceDto = PieceDto.of(position, piece);

        PieceDto expected = new PieceDto("A", "1", "WHITE", "KING");
        assertThat(pieceDto).isEqualTo(expected);
    }

    @Nested
    class 피스_변환 {
        @Test
        void KING으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "WHITE", "KING");

            assertThat(pieceDto.getPiece()).isInstanceOf(King.class);
        }

        @Test
        void QUEEN으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "WHITE", "QUEEN");

            assertThat(pieceDto.getPiece()).isInstanceOf(Queen.class);
        }

        @Test
        void BISHOP으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "WHITE", "BISHOP");

            assertThat(pieceDto.getPiece()).isInstanceOf(Bishop.class);
        }

        @Test
        void ROOK으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "WHITE", "ROOK");

            assertThat(pieceDto.getPiece()).isInstanceOf(Rook.class);
        }

        @Test
        void KNIGHT으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "WHITE", "KNIGHT");

            assertThat(pieceDto.getPiece()).isInstanceOf(Knight.class);
        }

        @Test
        void WHITE_PAWN으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "WHITE", "PAWN");

            assertThat(pieceDto.getPiece()).isInstanceOf(WhitePawn.class);
        }

        @Test
        void BLACK_PAWN으로_변환한다() {
            PieceDto pieceDto = new PieceDto("A", "1", "BLACK", "PAWN");

            assertThat(pieceDto.getPiece()).isInstanceOf(BlackPawn.class);
        }
    }
}
