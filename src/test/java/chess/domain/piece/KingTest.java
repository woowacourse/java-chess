package chess.domain.piece;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {
    @DisplayName("생성 테스트")
    @Test
    public void create() {
        assertThatCode(() -> new King(Color.BLACK)).doesNotThrowAnyException();
    }

    @DisplayName("아군을 만나기 직전까지만 이동 가능하다.")
    @Test
    public void givenKingMoveWhenMeetTeamMThenStop() {
        King king = new King(Color.WHITE);
        Position currentKingPosition = new Position(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                new Position(File.F, Rank.TWO), new Pawn(Color.WHITE),
                new Position(File.D, Rank.ONE), new Queen(Color.WHITE));

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.D, Rank.TWO), new Position(File.E, Rank.TWO),
                        new Position(File.F, Rank.ONE)));
    }

    @DisplayName("적군을 만난 위치까지 이동 가능하다.")
    @Test
    public void givenKingMoveWhenMeetEnemyThenStopAtEnemyPosition() {
        King king = new King(Color.WHITE);
        Position currentKingPosition = new Position(File.E, Rank.ONE);
        Map<Position, Piece> board = Map.of(currentKingPosition, king,
                new Position(File.F, Rank.TWO), new Pawn(Color.WHITE),
                new Position(File.D, Rank.ONE), new Queen(Color.WHITE),
                new Position(File.D, Rank.TWO), new Pawn(Color.BLACK));

        Set<Position> movablePositions = king.calculateMovablePositions(currentKingPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(new Position(File.D, Rank.TWO), new Position(File.E, Rank.TWO),
                        new Position(File.F, Rank.ONE)));
    }
}
