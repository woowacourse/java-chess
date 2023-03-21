package chess.domain.piece.maker;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.property.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

class StartingPiecesGeneratorTest {

    @Test
    @DisplayName("초기 체스판이 정상적으로 생성된다")
    void init_test() {
        final List<Piece> pieces = new StartingPiecesGenerator().generate();

        assertThat(pieces).extracting(Piece::getPosition, Piece::getColor, Piece::getClass)
                .containsExactlyInAnyOrder(
                        tuple(Position.of(File.A, Rank.EIGHT), Color.BLACK, Rook.class),
                        tuple(Position.of(File.B, Rank.EIGHT), Color.BLACK, Knight.class),
                        tuple(Position.of(File.C, Rank.EIGHT), Color.BLACK, Bishop.class),
                        tuple(Position.of(File.D, Rank.EIGHT), Color.BLACK, Queen.class),
                        tuple(Position.of(File.E, Rank.EIGHT), Color.BLACK, King.class),
                        tuple(Position.of(File.F, Rank.EIGHT), Color.BLACK, Bishop.class),
                        tuple(Position.of(File.G, Rank.EIGHT), Color.BLACK, Knight.class),
                        tuple(Position.of(File.H, Rank.EIGHT), Color.BLACK, Rook.class),
                        tuple(Position.of(File.A, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.B, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.C, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.D, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.E, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.F, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.G, Rank.SEVEN), Color.BLACK, Pawn.class),
                        tuple(Position.of(File.H, Rank.SEVEN), Color.BLACK, Pawn.class),

                        tuple(Position.of(File.A, Rank.ONE), Color.WHITE, Rook.class),
                        tuple(Position.of(File.B, Rank.ONE), Color.WHITE, Knight.class),
                        tuple(Position.of(File.C, Rank.ONE), Color.WHITE, Bishop.class),
                        tuple(Position.of(File.D, Rank.ONE), Color.WHITE, Queen.class),
                        tuple(Position.of(File.E, Rank.ONE), Color.WHITE, King.class),
                        tuple(Position.of(File.F, Rank.ONE), Color.WHITE, Bishop.class),
                        tuple(Position.of(File.G, Rank.ONE), Color.WHITE, Knight.class),
                        tuple(Position.of(File.H, Rank.ONE), Color.WHITE, Rook.class),
                        tuple(Position.of(File.A, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.B, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.C, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.D, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.E, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.F, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.G, Rank.TWO), Color.WHITE, Pawn.class),
                        tuple(Position.of(File.H, Rank.TWO), Color.WHITE, Pawn.class)
                );
    }

}
