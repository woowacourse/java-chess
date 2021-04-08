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

class BishopTest {
    private Board board;

    @ParameterizedTest
    @DisplayName("Bishop이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"c", "f"})
    void blackTeamPositionCheck(String col) {
        Bishop bishop = Bishop.of(Team.BLACK, ColumnConverter.getLocation(col));
        Position bishopPosition = bishop.getPosition();

        assertThat(bishopPosition.getRow()).isEqualTo(0);
        assertThat(bishopPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Bishop이 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"c", "f"})
    void whiteTeamPositionCheck(String col) {
        Bishop bishop = Bishop.of(Team.WHITE, ColumnConverter.getLocation(col));
        Position bishopPosition = bishop.getPosition();

        assertThat(bishopPosition.getRow()).isEqualTo(7);
        assertThat(bishopPosition.getRow()).isNotEqualTo(1);
    }


    @ParameterizedTest
    @DisplayName("Bishop 초기 col 위치가 c혹은 f가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "b", "d", "e", "g", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Bishop.of(Team.BLACK, ColumnConverter.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Bishop이 Black 팀으로 생성되면, initial은 대문자 B이다.")
    void blackTeamInitialCheck() {
        Bishop bishop = Bishop.of(Team.BLACK, 2);
        assertThat(bishop.getInitial()).isEqualTo("B");
    }

    @Test
    @DisplayName("Bishop이 White 팀으로 생성되면, initial은 소문자 b이다.")
    void whiteTeamInitialCheck() {
        Bishop bishop = Bishop.of(Team.WHITE, 2);
        assertThat(bishop.getInitial()).isEqualTo("b");
    }

    void set(final Bishop bishop) {
        Pieces blackTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.BLACK, PositionsForTest.crossBlackTeamPawnPosition),
                Pawn.of(Team.BLACK, PositionsForTest.straightBlackTeamPawnPosition)
        ));
        Pieces whiteTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.WHITE, PositionsForTest.whiteTeamPawnPosition),
                bishop
        ));
        Map<Team, Pieces> boardMap = new HashMap<>();
        boardMap.put(Team.BLACK, blackTeamPieces);
        boardMap.put(Team.WHITE, whiteTeamPieces);
        board = new Board(boardMap);
    }

    @Test
    @DisplayName("White팀 Bishop이 board를 받으면, 갈 수 있는 위치를 반환한다.")
    void movablePositionsCheck() {
        Bishop bishop = Bishop.of(Team.WHITE, new Position(1, 1));
        set(bishop);

        List<Position> movablePositions = bishop.getMovablePositions(board);

        assertTrue(movablePositions.contains(PositionsForTest.crossBlackTeamPawnPosition));
        assertTrue(movablePositions.contains(PositionsForTest.crossBlankPosition));

        assertFalse(movablePositions.contains(PositionsForTest.straightBlankPosition));
        assertFalse(movablePositions.contains(PositionsForTest.straightBlackTeamPawnPosition));
        assertFalse(movablePositions.contains(PositionsForTest.whiteTeamPawnPosition));
    }
}
