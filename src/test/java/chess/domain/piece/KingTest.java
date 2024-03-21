package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("생성 테스트")
    @Test
    void create() {
        assertThatCode(() -> new King(Color.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetTeamMThenStop() {
        King king = new King(Color.WHITE);
        Position currentKingPosition = Position.of(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                Position.of(File.F, Rank.TWO), Pawn.ofWhite(),
                Position.of(File.D, Rank.ONE), new Queen(Color.WHITE));

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.D, Rank.TWO), Position.of(File.E, Rank.TWO),
                        Position.of(File.F, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        King king = new King(Color.WHITE);
        Position currentKingPosition = Position.of(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                Position.of(File.F, Rank.TWO), Pawn.ofWhite(),
                Position.of(File.D, Rank.ONE), new Queen(Color.WHITE),
                Position.of(File.D, Rank.TWO), Pawn.ofBlack());

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.of(File.D, Rank.TWO), Position.of(File.E, Rank.TWO),
                        Position.of(File.F, Rank.ONE)));
    }
}
