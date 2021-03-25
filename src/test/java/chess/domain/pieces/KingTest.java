package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.util.ColumnConverter;
import chess.domain.position.Position;
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

class KingTest {
    Board board;
    Position whiteTeamPawnPosition = new Position(0, 2);
    Position crossBlackTeamPawnPosition = new Position(0, 0);
    Position straightBlackTeamPawnPosition = new Position(1, 0);
    Position crossBlankPosition = new Position(2, 0);
    Position straightBlankPosition = new Position(2, 1);

    @ParameterizedTest
    @DisplayName("King이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"e"})
    void blackTeamPositionCheck(String col) {
        King king = King.of(Team.BLACK, ColumnConverter.getLocation(col));
        Position kingPosition = king.getPosition();

        assertThat(kingPosition.getRow()).isEqualTo(0);
        assertThat(kingPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("King이 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"e"})
    void whiteTeamPositionCheck(String col) {
        King king = King.of(Team.WHITE, ColumnConverter.getLocation(col));
        Position kingPosition = king.getPosition();

        assertThat(kingPosition.getRow()).isEqualTo(7);
        assertThat(kingPosition.getRow()).isNotEqualTo(1);
    }


    @ParameterizedTest
    @DisplayName("King 초기 col 위치가 e가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "b", "c", "d", "f", "g", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> King.of(Team.BLACK, ColumnConverter.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("King이 Black 팀으로 생성되면, initial은 대문자 K이다.")
    void blackTeamInitialCheck() {
        King king = King.of(Team.BLACK, 4);
        assertThat(king.getInitial()).isEqualTo("K");
    }

    @Test
    @DisplayName("King이 White 팀으로 생성되면, initial은 소문자 k이다.")
    void whiteTeamInitialCheck() {
        King king = King.of(Team.WHITE, 4);
        assertThat(king.getInitial()).isEqualTo("k");
    }

    void set(final King king) {
        Pieces blackTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.BLACK, crossBlackTeamPawnPosition),
                Pawn.of(Team.BLACK, straightBlackTeamPawnPosition)
        ));
        Pieces whiteTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.WHITE, whiteTeamPawnPosition),
                king
        ));
        Map<Team, Pieces> boardMap = new HashMap<>();
        boardMap.put(Team.BLACK, blackTeamPieces);
        boardMap.put(Team.WHITE, whiteTeamPieces);
        board = new Board(boardMap);
    }

    @Test
    @DisplayName("White팀 King이 board를 받으면, 갈 수 있는 위치를 반환한다.")
    void movablePositionsCheck() {
        King king = King.of(Team.WHITE, new Position(1, 1));
        set(king);

        List<Position> movablePositions = king.getMovablePositions(board);

        assertTrue(movablePositions.contains(crossBlackTeamPawnPosition));
        assertTrue(movablePositions.contains(crossBlankPosition));
        assertTrue(movablePositions.contains(straightBlankPosition));
        assertTrue(movablePositions.contains(straightBlackTeamPawnPosition));

        assertFalse(movablePositions.contains(whiteTeamPawnPosition));
    }
}