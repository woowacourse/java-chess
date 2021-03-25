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

class QueenTest {
    Board board;
    Position whiteTeamPawnPosition = new Position(0, 2);
    Position crossBlackTeamPawnPosition = new Position(0, 0);
    Position straightBlackTeamPawnPosition = new Position(1, 0);
    Position crossBlankPosition = new Position(2, 0);
    Position straightBlankPosition = new Position(2, 1);

    @ParameterizedTest
    @DisplayName("Queen이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"d"})
    void blackTeamPositionCheck(String col) {
        Queen queen = Queen.of(Team.BLACK, ColumnConverter.getLocation(col));
        Position queenPosition = queen.getPosition();

        assertThat(queenPosition.getRow()).isEqualTo(0);
        assertThat(queenPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Queen이 White 팀으로 생성되면, row의 실제 좌표 위치는 7이다.")
    @ValueSource(strings = {"d"})
    void whiteTeamPositionCheck(String col) {
        Queen queen = Queen.of(Team.WHITE, ColumnConverter.getLocation(col));
        Position queenPosition = queen.getPosition();

        assertThat(queenPosition.getRow()).isEqualTo(7);
        assertThat(queenPosition.getRow()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Queen 초기 col 위치가 d가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"a", "b", "c", "e", "f", "g", "h"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Queen.of(Team.BLACK, ColumnConverter.getLocation(col))).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Queen이 Black 팀으로 생성되면, initial은 대문자 Q이다.")
    void blackTeamInitialCheck() {
        Queen queen = Queen.of(Team.BLACK, 3);
        assertThat(queen.getInitial()).isEqualTo("Q");
    }

    @Test
    @DisplayName("Queen이 White 팀으로 생성되면, initial은 소문자 q이다.")
    void whiteTeamInitialCheck() {
        Queen queen = Queen.of(Team.WHITE, 3);
        assertThat(queen.getInitial()).isEqualTo("q");
    }

    void set(final Queen queen) {
        Pieces blackTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.BLACK, crossBlackTeamPawnPosition),
                Pawn.of(Team.BLACK, straightBlackTeamPawnPosition)
        ));
        Pieces whiteTeamPieces = new Pieces(Arrays.asList(
                Pawn.of(Team.WHITE, whiteTeamPawnPosition),
                queen
        ));
        Map<Team, Pieces> boardMap = new HashMap<>();
        boardMap.put(Team.BLACK, blackTeamPieces);
        boardMap.put(Team.WHITE, whiteTeamPieces);
        board = new Board(boardMap);
    }

    @Test
    @DisplayName("White팀 Queen이 board를 받으면, 갈 수 있는 위치를 반환한다.")
    void movablePositionsCheck() {
        Queen queen = Queen.of(Team.WHITE, new Position(1, 1));
        set(queen);

        List<Position> movablePositions = queen.getMovablePositions(board);

        assertTrue(movablePositions.contains(crossBlackTeamPawnPosition));
        assertTrue(movablePositions.contains(crossBlankPosition));
        assertTrue(movablePositions.contains(straightBlankPosition));
        assertTrue(movablePositions.contains(straightBlackTeamPawnPosition));

        assertFalse(movablePositions.contains(whiteTeamPawnPosition));
    }
}