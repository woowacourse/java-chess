package domain;

import domain.piece.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class PieceFactoryTest {
    @DisplayName("PieceFactory create 호출 시 자동으로 말들이 자동 생성된다.")
    @Test
    void peice_factory_generate() {
        Map<Position, Piece> pieces = PieceFactory.createPieces();
        Map<Position, Piece> expected = new HashMap<Position, Piece>() {
            {
                put(Position.of("c8"), Bishop.of("B", true));
                put(Position.of("f8"), Bishop.of("B", true));
                put(Position.of("c1"), Bishop.of("b", false));
                put(Position.of("f5"), Bishop.of("b", false));
                put(Position.of("e8"), King.of("K", true));
                put(Position.of("e1"), King.of("k", false));
                put(Position.of("b8"), Knight.of("N", true));
                put(Position.of("g8"), Knight.of("N", true));
                put(Position.of("b1"), Knight.of("n", false));
                put(Position.of("g1"), Knight.of("n", false));
                put(Position.of("d8"), Queen.of("Q", true));
                put(Position.of("d3"), Queen.of("q", false));
                put(Position.of("a7"), Pawn.of("P", true));
                put(Position.of("b7"), Pawn.of("P", true));
                put(Position.of("c7"), Pawn.of("P", true));
                put(Position.of("d7"), Pawn.of("P", true));
                put(Position.of("e7"), Pawn.of("P", true));
                put(Position.of("f7"), Pawn.of("P", true));
                put(Position.of("g7"), Pawn.of("P", true));
                put(Position.of("h7"), Pawn.of("P", true));
                put(Position.of("a6"), Pawn.of("p", false));
                put(Position.of("b6"), Pawn.of("p", false));
                put(Position.of("c6"), Pawn.of("p", false));
                put(Position.of("d6"), Pawn.of("p", false));
                put(Position.of("e6"), Pawn.of("p", false));
                put(Position.of("f6"), Pawn.of("p", false));
                put(Position.of("g6"), Pawn.of("p", false));
                put(Position.of("h6"), Pawn.of("p", false));
                put(Position.of("a8"), Rook.of("R", true));
                put(Position.of("h8"), Rook.of("R", true));
                put(Position.of("a1"), Rook.of("r", false));
                put(Position.of("h1"), Rook.of("r", false));
            }
        };
        Assertions.assertThat(pieces).containsAllEntriesOf(expected);
    }
}