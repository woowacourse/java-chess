package chess.domain.generator;

import chess.domain.Position;
import chess.domain.piece.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WhiteGeneratorTest {

    @Test
    @DisplayName("모든 체스말을 생성한다.")
    void generatePieces() {
        final List<Piece> expected = new ArrayList<>(createPawn());
        expected.addAll(List.of(
                new Rook(Position.of(1, 'a')),
                new Rook(Position.of(1, 'h')),
                new Knight(Position.of(1, 'b')),
                new Knight(Position.of(1, 'g')),
                new Bishop(Position.of(1, 'c')),
                new Bishop(Position.of(1, 'f')),
                new Queen(Position.of(1, 'd')),
                new King(Position.of(1, 'e'))
        ));
        final WhiteGenerator generator = new WhiteGenerator();

        final List<Piece> actual = generator.generate();

        assertThat(actual).isEqualTo(expected);
    }

    private List<Piece> createPawn() {
        final List<Piece> pawns = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            pawns.add(new Pawn(Position.of(2, (char) ('a' + i))));
        }
        return pawns;
    }
}
