package chess.domain.board.maker;

import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class StartingPiecesFactoryTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void succeeds_creation() {
        final List<Piece> pieces = new StartingPiecesFactory().generate();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .containsExactlyInAnyOrder(
                        tuple(new Position(File.A, Rank.EIGHT), Color.BLACK, Rook.class),
                        tuple(new Position(File.B, Rank.EIGHT), Color.BLACK, Knight.class),
                        tuple(new Position(File.C, Rank.EIGHT), Color.BLACK, Bishop.class),
                        tuple(new Position(File.D, Rank.EIGHT), Color.BLACK, Queen.class),
                        tuple(new Position(File.E, Rank.EIGHT), Color.BLACK, King.class),
                        tuple(new Position(File.F, Rank.EIGHT), Color.BLACK, Bishop.class),
                        tuple(new Position(File.G, Rank.EIGHT), Color.BLACK, Knight.class),
                        tuple(new Position(File.H, Rank.EIGHT), Color.BLACK, Rook.class),
                        tuple(new Position(File.A, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.B, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.C, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.D, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.E, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.F, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.G, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(new Position(File.H, Rank.SEVEN), Color.BLACK, Pawn.class),

                        tuple(new Position(File.A, Rank.ONE), Color.WHITE, Rook.class),
                        tuple(new Position(File.B, Rank.ONE), Color.WHITE, Knight.class),
                        tuple(new Position(File.C, Rank.ONE), Color.WHITE, Bishop.class),
                        tuple(new Position(File.D, Rank.ONE), Color.WHITE, Queen.class),
                        tuple(new Position(File.E, Rank.ONE), Color.WHITE, King.class),
                        tuple(new Position(File.F, Rank.ONE), Color.WHITE, Bishop.class),
                        tuple(new Position(File.G, Rank.ONE), Color.WHITE, Knight.class),
                        tuple(new Position(File.H, Rank.ONE), Color.WHITE, Rook.class),
                        tuple(new Position(File.A, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.B, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.C, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.D, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.E, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.F, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.G, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(new Position(File.H, Rank.TWO), Color.WHITE, Pawn.class)
                );
    }

}
