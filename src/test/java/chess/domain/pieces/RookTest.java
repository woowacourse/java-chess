package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.moving.RookMoving;
import chess.domain.position.Col;
import chess.domain.position.Position;
import chess.exception.WrongInitPositionException;
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

class RookTest {
    private Board board;
    private final Position whiteTeamPawnPosition = new Position(0, 2);
    private final Position crossBlackTeamPawnPosition = new Position(0, 0);
    private final Position straightBlackTeamPawnPosition = new Position(1, 0);
    private final Position crossBlankPosition = new Position(2, 0);
    private final Position straightBlankPosition = new Position(2, 1);

    @ParameterizedTest
    @DisplayName("Rook이 Black 팀으로 생성되면, row의 실제 좌표 위치는 0이다.")
    @ValueSource(strings = {"a", "h"})
    void blackTeamPositionCheck(String col) {
        Rook rook = Rook.black(Col.location(col));
        Position rookPosition = rook.position();
        assertThat(rookPosition.row()).isEqualTo(0);
        assertThat(rookPosition.row()).isNotEqualTo(1);
    }

    @ParameterizedTest
    @DisplayName("Rook이 White 팀으로 생성되면, row의 실제 좌표 위치는 y이다.")
    @ValueSource(strings = {"a", "h"})
    void whiteTeamPositionCheck(String col) {
        Rook rook = Rook.white(Col.location(col));
        Position rookPosition = rook.position();
        assertThat(rookPosition.row()).isEqualTo(7);
        assertThat(rookPosition.row()).isNotEqualTo(8);
    }

    @ParameterizedTest
    @DisplayName("Rook 초기 col 위치가 a혹은 h가 아니면, 예외가 발생한다.")
    @ValueSource(strings = {"b", "c", "d", "e", "f", "g"})
    void wrongInitColCheck(String col) {
        assertThatThrownBy(() -> Rook.white(Col.location(col))).isInstanceOf(WrongInitPositionException.class);
    }

    @Test
    @DisplayName("Rook이 Black 팀으로 생성되면, initial은 R이다.")
    void blackTeamInitialCheck() {
        Rook rook = Rook.black(0);
        assertThat(rook.initial()).isEqualTo("R");
    }

    @Test
    @DisplayName("Rook이 White 팀으로 생성되면, initial은 R이다.")
    void whiteTeamInitialCheck() {
        Rook rook = Rook.white(0);
        assertThat(rook.initial()).isEqualTo("R");
    }

    void set(final Rook rook) {
        Pieces blackTeamPieces = new Pieces(Arrays.asList(
                new Pawn(crossBlackTeamPawnPosition),
                new Pawn(straightBlackTeamPawnPosition)
        ));
        Pieces whiteTeamPieces = new Pieces(Arrays.asList(
                new Pawn(whiteTeamPawnPosition),
                rook
        ));
        Map<Team, Pieces> boardMap = new HashMap<>();
        boardMap.put(Team.BLACK, blackTeamPieces);
        boardMap.put(Team.WHITE, whiteTeamPieces);
        board = new Board(boardMap);
    }

    @Test
    @DisplayName("White팀 Rook이 board를 받으면, 갈 수 있는 위치를 반환한다.")
    void movablePositionsCheck() {
        Rook rook = new Rook(new Position(1, 1));
        set(rook);

        List<Position> movablePositions = new RookMoving().allMovablePositions(rook, board);

        assertTrue(movablePositions.contains(straightBlackTeamPawnPosition));
        assertTrue(movablePositions.contains(straightBlankPosition));

        assertFalse(movablePositions.contains(whiteTeamPawnPosition));
        assertFalse(movablePositions.contains(crossBlackTeamPawnPosition));
        assertFalse(movablePositions.contains(crossBlankPosition));
    }
}