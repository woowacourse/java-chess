package chess.domain;

import chess.domain.piece.*;
import chess.domain.piece.character.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class BoardFactoryTest {
    @DisplayName("Board에서 위치와 Character를 알 수 있다.")
    @Test
    void mapPositionToCharacter() {
        Map<Position, Piece> expected = Map.ofEntries(
                Map.entry(Position.of(1, 1), new Rook(Team.WHITE)),
                Map.entry(Position.of(1, 2), new Knight(Team.WHITE)),
                Map.entry(Position.of(1, 3), new Bishop(Team.WHITE)),
                Map.entry(Position.of(1, 4), new Queen(Team.WHITE)),
                Map.entry(Position.of(1, 5), new King(Team.WHITE)),
                Map.entry(Position.of(1, 6), new Bishop(Team.WHITE)),
                Map.entry(Position.of(1, 7), new Knight(Team.WHITE)),
                Map.entry(Position.of(1, 8), new Rook(Team.WHITE)),

                Map.entry(Position.of(2, 1), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 2), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 3), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 4), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 5), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 6), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 7), new Pawn(Team.WHITE)),
                Map.entry(Position.of(2, 8), new Pawn(Team.WHITE)),

                Map.entry(Position.of(7, 1), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 2), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 3), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 4), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 5), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 6), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 7), new Pawn(Team.BLACK)),
                Map.entry(Position.of(7, 8), new Pawn(Team.BLACK)),

                Map.entry(Position.of(8, 1), new Rook(Team.BLACK)),
                Map.entry(Position.of(8, 2), new Knight(Team.BLACK)),
                Map.entry(Position.of(8, 3), new Bishop(Team.BLACK)),
                Map.entry(Position.of(8, 4), new Queen(Team.BLACK)),
                Map.entry(Position.of(8, 5), new King(Team.BLACK)),
                Map.entry(Position.of(8, 6), new Bishop(Team.BLACK)),
                Map.entry(Position.of(8, 7), new Knight(Team.BLACK)),
                Map.entry(Position.of(8, 8), new Rook(Team.BLACK))
        );

        assertThat(BoardFactory.generateStartBoard()).isEqualTo(expected);
    }
}
