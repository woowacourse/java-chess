package chess.domain.board;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.in;

import chess.domain.piece.Blank;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BoardTest {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("피스 이동 기능")
    void move() {
        board.move(new Position("a", "2"), new Position("a", "3"), Team.WHITE);
        assertThat(board.unwrap().get(new Position("a", "2"))).isInstanceOf(Blank.class);
        assertThat(board.unwrap().get(new Position("a", "3"))).isEqualTo(new Pawn(Team.WHITE));
    }

    @ParameterizedTest
    @DisplayName("룩, 비숍, 퀸, 나이트, 킹 이동이 불가능한 경우")
    @ValueSource(strings = {"a,1,a,7", "c,1,h,7", "d,1,d,8", "d,1,a,4", "b,1,c,2", "e,1,d,1"})
    void checkPath(final String input) {
        final String[] inputs = input.split(",");
        final Position start = new Position(inputs[0], inputs[1]);
        final Position end = new Position(inputs[2], inputs[3]);
        assertThatThrownBy(
            () -> board.move(start, end, Team.WHITE))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @ParameterizedTest
    @DisplayName("폰 이동이 불가능한 경우")
    @ValueSource(strings = {"a,2,a,3", "a,2,a,4"})
    void checkPawnPath(final String input) {
        final Team team = Team.WHITE;
        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        chessBoard.put(new Position("a", "3"), new Queen(team));
        board = new Board(chessBoard);

        final String[] inputs = input.split(",");
        final Position start = new Position(inputs[0], inputs[1]);
        final Position end = new Position(inputs[2], inputs[3]);
        assertThatThrownBy(() -> board.move(start, end, team))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 위치로 이동할 수 없습니다.");
    }

    @Test
    @DisplayName("두 플레이어 중 킹 체스말이 없는 플레이어가 있는지 확인하는 기능")
    void checkDieKing() {
        assertThat(board.isKingDead()).isFalse();
        final Map<Position, Piece> chessBoard = new TreeMap<>(board.unwrap());
        chessBoard.put(new Position("e", "1"), Blank.getInstance());
        board = new Board(chessBoard);
        assertThat(board.isKingDead()).isTrue();
    }

    @Test
    @DisplayName("턴에 맞는 위치를 선택했는지 검증하는 기능")
    void isRightTurn() {
        final Team team = Team.BLACK;
        assertThatThrownBy(() -> board.validateRightTurn(new Position("a", "2"), team))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("본인의 턴에 맞는 말을 움직이세요.");
    }


    @Test
    @DisplayName("빈칸 이동 오류 확인")
    void checkBlankError() {
        assertThatThrownBy(
            () -> board.move(new Position("a", "3"), new Position("a", "5"), Team.WHITE))
            .isInstanceOf(UnsupportedOperationException.class)
            .hasMessage("비어 있는 칸입니다.");
    }
}