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

class RookTest {
    @DisplayName("룩 - 장애물이 존재하지 않을 경우 이동 경로 끝까지 이동할 수 있다.")
    @Test
    void notExistHurdles() {
        // given
        Position rookPosition = new Position(Row.FOUR, Column.D);
        Rook rook = new Rook(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                rookPosition, rook
        );
        List<Position> expected = List.of(
                new Position(Row.EIGHT, Column.D),
                new Position(Row.SEVEN, Column.D),
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D),
                new Position(Row.THREE, Column.D),
                new Position(Row.TWO, Column.D),
                new Position(Row.ONE, Column.D),

                new Position(Row.FOUR, Column.A),
                new Position(Row.FOUR, Column.B),
                new Position(Row.FOUR, Column.C),
                new Position(Row.FOUR, Column.E),
                new Position(Row.FOUR, Column.F),
                new Position(Row.FOUR, Column.G),
                new Position(Row.FOUR, Column.H)
        );

        // when
        List<Position> destinations = rook.getAvailableDestinations(
                rookPosition, positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("이동경로에 같은 팀 기물이 존재하는 경우, 해당 기물들을 마주하기 전까지의 위치로 움직일 수 있다.")
    @Test
    void existSameTeamHurdle() {
        // given
        Position rookPosition = new Position(Row.FOUR, Column.D);
        Rook rook = new Rook(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                rookPosition, rook,
                new Position(Row.SIX, Column.D), new Bishop(Color.BLACK),
                new Position(Row.FOUR, Column.H), new Bishop(Color.BLACK),
                new Position(Row.THREE, Column.D), new Knight(Color.BLACK)
        );
        List<Position> expected = List.of(
                new Position(Row.FIVE, Column.D),
                new Position(Row.FOUR, Column.A),
                new Position(Row.FOUR, Column.B),
                new Position(Row.FOUR, Column.C),
                new Position(Row.FOUR, Column.E),
                new Position(Row.FOUR, Column.F),
                new Position(Row.FOUR, Column.G)
        );

        // when
        List<Position> destinations = rook.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("이동경로에 다른 팀 기물이 존재하는 경우, 해당 기물들의 위치로 움직일 수 있다.")
    @Test
    void existOtherTeamHurdle() {
        // given
        Position rookPosition = new Position(Row.FOUR, Column.D);
        Rook rook = new Rook(Color.BLACK);
        Map<Position, ChessPiece> positions = Map.of(
                rookPosition, rook,
                new Position(Row.SIX, Column.D), new Bishop(Color.WHITE),
                new Position(Row.FOUR, Column.H), new Bishop(Color.WHITE),
                new Position(Row.THREE, Column.D), new Knight(Color.WHITE)
        );
        List<Position> expected = List.of(
                new Position(Row.SIX, Column.D),
                new Position(Row.FIVE, Column.D),
                new Position(Row.THREE, Column.D),
                new Position(Row.FOUR, Column.A),
                new Position(Row.FOUR, Column.B),
                new Position(Row.FOUR, Column.C),
                new Position(Row.FOUR, Column.E),
                new Position(Row.FOUR, Column.F),
                new Position(Row.FOUR, Column.G),
                new Position(Row.FOUR, Column.H)
        );

        // when
        List<Position> destinations = rook.getAvailableDestinations(
                new Position(Row.FOUR, Column.D), positions
        );

        // then
        assertThat(destinations).containsExactlyInAnyOrderElementsOf(expected);
    }
}
