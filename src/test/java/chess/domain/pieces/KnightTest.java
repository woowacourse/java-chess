package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.ColumnConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KnightTest {
    private Board board;

    @ParameterizedTest
    @DisplayName("Knight가 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"b", "g"})
    void blackTeamPositionCheck(String col) {
        Knight knight = Knight.of(Team.BLACK, ColumnConverter.getLocation(col));
        Position KnightPosition = knight.getPosition();

        assertThat(KnightPosition.getRow()).isEqualTo(0);
        assertThat(KnightPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Knight가 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"b", "g"})
    void whiteTeamPositionCheck(String col) {
        Knight knight = Knight.of(Team.WHITE, ColumnConverter.getLocation(col));
        Position KnightPosition = knight.getPosition();

        assertThat(KnightPosition.getRow()).isEqualTo(7);
        assertThat(KnightPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Knight 초기 col 위치가 b혹은 g가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "c", "d", "e", "f", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Knight.of(Team.BLACK, ColumnConverter.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Knight가 Black 팀으로 생성되면, initial은 대문자 N이다.")
    void blackTeamInitialCheck() {
        Knight knight = Knight.of(Team.BLACK, 1);
        assertThat(knight.getInitial()).isEqualTo("N");
    }

    @Test
    @DisplayName("Knight가 White 팀으로 생성되면, initial은 소문자 n이다.")
    void whiteTeamInitialCheck() {
        Knight knight = Knight.of(Team.WHITE, 1);
        assertThat(knight.getInitial()).isEqualTo("n");
    }

    void set(final Knight knight) {
        Pieces blackTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.BLACK, PositionsForTest.crossBlackTeamPawnPosition),
                Pawn.of(Team.BLACK, PositionsForTest.straightBlackTeamPawnPosition),
                Pawn.of(Team.BLACK, PositionsForTest.straightCrossBlackTeamPawnPosition)
        ));
        Pieces whiteTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.WHITE, PositionsForTest.whiteTeamPawnPosition),
                knight
        ));
        Map<Team, Pieces> boardMap = new HashMap<>();
        boardMap.put(Team.BLACK, blackTeamPieces);
        boardMap.put(Team.WHITE, whiteTeamPieces);
        board = new Board(boardMap);
    }

    @Test
    @DisplayName("White팀 Knight가 board를 받으면, 갈 수 있는 위치를 반환한다.")
    void movablePositionsCheck() {
        Knight knight = Knight.of(Team.WHITE, new Position(1, 1));
        set(knight);

        List<Position> movablePositions = knight.getMovablePositions(board);

        assertTrue(movablePositions.contains(PositionsForTest.straightCrossBlackTeamPawnPosition));
        assertTrue(movablePositions.contains(PositionsForTest.straightCrossBlankPosition));

        assertFalse(movablePositions.contains(PositionsForTest.crossBlackTeamPawnPosition));
        assertFalse(movablePositions.contains(PositionsForTest.crossBlankPosition));
        assertFalse(movablePositions.contains(PositionsForTest.straightBlankPosition));
        assertFalse(movablePositions.contains(PositionsForTest.straightBlackTeamPawnPosition));
        assertFalse(movablePositions.contains(PositionsForTest.whiteTeamPawnPosition));
    }
}