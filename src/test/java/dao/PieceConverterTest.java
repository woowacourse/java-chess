package dao;

import domain.piece.Color;
import domain.piece.nonsliding.King;
import domain.piece.nonsliding.Knight;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;
import domain.piece.sliding.Bishop;
import domain.piece.sliding.Queen;
import domain.piece.sliding.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class PieceConverterTest {

    @Test
    @DisplayName("폰은 문자열로 변환될 수 있다")
    void parsePawn() {
        assertThat(PieceConverter.parse(new WhitePawn(Color.WHITE))).isEqualTo("pawn");
        assertThat(PieceConverter.parse(new BlackPawn(Color.BLACK))).isEqualTo("pawn");
        assertThat(PieceConverter.parse(new Rook(Color.WHITE))).isEqualTo("rook");
        assertThat(PieceConverter.parse(new Bishop(Color.WHITE))).isEqualTo("bishop");
        assertThat(PieceConverter.parse(new Knight(Color.WHITE))).isEqualTo("knight");
        assertThat(PieceConverter.parse(new Queen(Color.WHITE))).isEqualTo("queen");
        assertThat(PieceConverter.parse(new King(Color.WHITE))).isEqualTo("king");
    }

    @Test
    @DisplayName("문자열은 기물 객체로 변환될 수 있다")
    void combine() {
        assertThat(PieceConverter.combine("pawn", Color.WHITE)).isInstanceOf(WhitePawn.class);
        assertThat(PieceConverter.combine("pawn", Color.BLACK)).isInstanceOf(BlackPawn.class);
        assertThat(PieceConverter.combine("rook", Color.WHITE)).isInstanceOf(Rook.class);
        assertThat(PieceConverter.combine("bishop", Color.WHITE)).isInstanceOf(Bishop.class);
        assertThat(PieceConverter.combine("knight", Color.WHITE)).isInstanceOf(Knight.class);
        assertThat(PieceConverter.combine("queen", Color.WHITE)).isInstanceOf(Queen.class);
        assertThat(PieceConverter.combine("king", Color.WHITE)).isInstanceOf(King.class);
    }
}
