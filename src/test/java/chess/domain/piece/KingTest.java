package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        King king = King.WHITE;
        Position currentKingPosition = Position.from(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                Position.from(File.F, Rank.TWO), Pawn.ofWhite(),
                Position.from(File.D, Rank.ONE), Queen.WHITE);

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.D, Rank.TWO), Position.from(File.E, Rank.TWO),
                        Position.from(File.F, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        King king = King.WHITE;
        Position currentKingPosition = Position.from(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                Position.from(File.F, Rank.TWO), Pawn.ofWhite(),
                Position.from(File.D, Rank.ONE), Queen.WHITE,
                Position.from(File.D, Rank.TWO), Pawn.ofBlack());

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.D, Rank.TWO), Position.from(File.E, Rank.TWO),
                        Position.from(File.F, Rank.ONE)));
    }
}
