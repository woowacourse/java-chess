package chess.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.ordinary.Knight;
import chess.domain.piece.pawn.InitialPawn;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import chess.domain.square.Squares;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

class PiecesTest {
    @Test
    @DisplayName("32개의 piece 위치를 초기화 한다.")
    void initPieces() {
        Map<Square, Piece> pieces = Pieces.init();
        Square square1 = Squares.getSquare(File.A, Rank.TWO);
        Square square2 = Squares.getSquare(File.G, Rank.EIGHT);

        assertThat(pieces).hasSize(32);
        assertThat(pieces.get(square1)).isInstanceOf(InitialPawn.class);
        assertThat(pieces.get(square2)).isInstanceOf(Knight.class);
    }
}
