package chess.piece;

import chess.Color;
import chess.Column;
import chess.Position;
import chess.Row;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {
    @DisplayName("BLACK - 처음 움직이는 경우 두 칸까지 전진할 수 있다.")
    @Test
    void notExistHurdle() {
        // given
        Map<Position, ChessPiece> positions = Map.of();
        Pawn pawn = new Pawn(Color.BLACK);
        List<Position> expected = List.of(
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                new Position(Row.SEVEN, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("BLACK - 전진 방향의 대각선에 상대편 기물이 있는 경우 해당 위치로 이동할 수 있다.")
    @Test
    void existEnemyAtDiagonal() {
        // given
        Position pawnPosition = new Position(Row.SEVEN, Column.D);
        Pawn pawn = new Pawn(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.SIX, Column.C), new Knight(Color.WHITE)
        );
        List<Position> expected = List.of(
                new Position(Row.SIX, Column.C),
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                pawnPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("BLACK - 전진 방향의 대각선에 같은편 기물이 있는 경우 해당 위치로 이동할 수 없다.")
    @Test
    void existSameTeamHurdle() {
        // given
        Position pawnPosition = new Position(Row.SEVEN, Column.D);
        Pawn pawn = new Pawn(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.SIX, Column.C), new Knight(Color.BLACK)
        );
        List<Position> expected = List.of(
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                pawnPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
