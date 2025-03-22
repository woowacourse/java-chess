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
        Position pawnPosition = new Position(Row.SEVEN, Column.D);
        Pawn pawn = new Pawn(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn
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

    @DisplayName("BLACK - 이전에 움직인 이력이 있는 경우, 앞으로 한 칸만 전진할 수 있따.")
    @Test
    void onlyOneForwardMoved_BLACK() {
        // given
        Pawn pawn = new Pawn(Color.BLACK);
        Position previous = new Position(Row.SEVEN, Column.D);
        Position current = new Position(Row.SIX, Column.D);
        pawn.move(previous, current, Map.of());

        List<Position> expect = List.of(
                new Position(Row.FIVE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(current, Map.of());

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expect);
    }

    @DisplayName("WHITE - 처음 움직이는 경우 두 칸까지 전진할 수 있다.")
    @Test
    void notExistHurdleWhite() {
        // given
        Position pawnPosition = new Position(Row.TWO, Column.D);
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn
        );
        List<Position> expected = List.of(
                new Position(Row.THREE, Column.D),
                new Position(Row.FOUR, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                pawnPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("WHITE - 전진 방향의 대각선에 상대편 기물이 있는 경우 해당 위치로 이동할 수 있다.")
    @Test
    void existEnemyAtDiagonal_WHITE() {
        // given
        Position pawnPosition = new Position(Row.TWO, Column.D);
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.THREE, Column.E), new Knight(Color.BLACK)
        );
        List<Position> expected = List.of(
                new Position(Row.THREE, Column.D),
                new Position(Row.FOUR, Column.D),
                new Position(Row.THREE, Column.E)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                pawnPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("WHITE - 전진 방향의 대각선에 같은편 기물이 있는 경우 해당 위치로 이동할 수 없다.")
    @Test
    void existSameTeamHurdle_WHITE() {
        // given
        Position pawnPosition = new Position(Row.TWO, Column.D);
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.THREE, Column.E), new Knight(Color.WHITE)
        );
        List<Position> expected = List.of(
                new Position(Row.THREE, Column.D),
                new Position(Row.FOUR, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(
                pawnPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("WHITE - 이전에 움직인 이력이 있는 경우, 앞으로 한 칸만 전진할 수 있따.")
    @Test
    void onlyOneForwardMoved_WHITE() {
        // given
        Pawn pawn = new Pawn(Color.WHITE);
        Position previous = new Position(Row.TWO, Column.D);
        Position current = new Position(Row.THREE, Column.D);
        pawn.move(previous, current, Map.of());

        List<Position> expect = List.of(
                new Position(Row.FOUR, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(current, Map.of());

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expect);
    }

    @DisplayName("처음 움직일 때 두 칸 앞에 적 기물이 존재하는 경우 두 칸 이동이 불가능하다.")
    @Test
    void firstMoveExistEnemy() {
        // given
        Position pawnPosition = new Position(Row.TWO, Column.D);
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.FOUR, Column.D), new Knight(Color.BLACK)
        );
        List<Position> expected = List.of(
                new Position(Row.THREE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(pawnPosition, positions);

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("처음 움직일 때 두 칸 앞에 같은 팀 기물이 존재하는 경우 두 칸 이동이 불가능하다.")
    @Test
    void firstMoveExistSameTeam() {
        // given
        Position pawnPosition = new Position(Row.TWO, Column.D);
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.FOUR, Column.D), new Knight(Color.WHITE)
        );
        List<Position> expected = List.of(
                new Position(Row.THREE, Column.D)
        );

        // when
        List<Position> destinations = pawn.getAvailableDestinations(pawnPosition, positions);

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("이동방향 바로 앞에 상대 기물이 존재하는 경우 움직일 수 없다.")
    @Test
    void firstMoveExistEnemyFront() {
        // given
        Position pawnPosition = new Position(Row.TWO, Column.D);
        Pawn pawn = new Pawn(Color.WHITE);
        Map<Position, ChessPiece> positions = Map.of(
                pawnPosition, pawn,
                new Position(Row.THREE, Column.D), new Knight(Color.BLACK)
        );
        List<Position> expected = List.of();

        // when
        List<Position> destinations = pawn.getAvailableDestinations(pawnPosition, positions);

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
