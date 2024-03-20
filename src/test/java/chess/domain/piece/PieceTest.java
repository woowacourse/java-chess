package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.attribute.Color;
import chess.domain.attribute.File;
import chess.domain.attribute.Rank;
import chess.domain.attribute.Square;

class PieceTest {

    @DisplayName("기물이 이동할 수 있는 칸을 찾는다.")
    @Test
    void movableSquares() {
        Piece piece = new King(Color.WHITE);
        Square currentSquare = Square.of(File.startKingFile(), Rank.startRankOf(Color.WHITE));
        assertThat(piece.movableSquares(currentSquare))
                .isEqualTo(Set.of(
                        Square.of(File.D, Rank.ONE),
                        Square.of(File.F, Rank.ONE),
                        Square.of(File.D, Rank.TWO),
                        Square.of(File.E, Rank.TWO),
                        Square.of(File.F, Rank.TWO)
                ));
    }
}
