package chess.domain.piece;

import chess.domain.Position;
import chess.domain.TeamColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PiecesTest {

    @Test
    @DisplayName("공격가능한 포지션들 테스트")
    void testAttackPosition() {
        //given
        Pieces pieces = new Pieces();
        Piece pawn = new Pawn(TeamColor.WHITE, Position.of(0, 0));
        Piece bishop = new Knight(TeamColor.WHITE, Position.of(7, 0));
        pieces.add(pawn);
        pieces.add(bishop);
        pieces.updateMovablePositions();
        Set<Position> actualPositions = new HashSet<>(Arrays.asList(
                Position.of(0, 1),
                Position.of(5, 1),
                Position.of(0, 2)
        ));

        //when
        Set<Position> expectedAttackPositions = pieces.attackPositions(TeamColor.WHITE);

        //than
        assertThat(expectedAttackPositions).isEqualTo(actualPositions);
    }

    @Test
    @DisplayName("색에 따른 King 반환 테스트")
    void testKingByColor() {
        //given
        Pieces pieces = new Pieces();
        Piece pawn = new Pawn(TeamColor.WHITE, Position.of(0, 0));
        Piece king = new King(TeamColor.WHITE, Position.of(7, 0));
        pieces.add(pawn);
        pieces.add(king);
        pieces.updateMovablePositions();

        //when
        Piece expectedKing = pieces.kingByColor(TeamColor.WHITE).get();

        //than
        assertThat(expectedKing).isEqualTo(king);
    }
}
