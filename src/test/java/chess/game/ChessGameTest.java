package chess.game;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessGameTest {

    @Test
    @DisplayName("화이트팀 차례에 블랙팀이 움직이는 경우 예외가 발생한다.")
    void movePieceNotWithWrongTeam1() {
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));
        Position source = Position.of(0, 6);
        Position target = Position.of(0, 5);

        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] WHITE팀의 말만 이동할 수 있습니다.");

    }

    @Test
    @DisplayName("블랙팀 차례에 화이트팀이 움직이는 경우 예외가 발생한다.")
    void movePieceNotWithWrongTeam2() {
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));
        Position whiteTeamSource = Position.of(0, 1);
        Position whiteTeamTarget = Position.of(0, 2);
        chessGame.move(whiteTeamSource, whiteTeamTarget);

        Position blackTeamSource = Position.of(0, 2);
        Position blackTeamTarget = Position.of(0, 3);

        assertThatThrownBy(() -> chessGame.move(blackTeamSource, blackTeamTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] BLACK팀의 말만 이동할 수 있습니다.");
    }

    @Test
    @DisplayName("말의 이동 경로에 말이 있으면 예외가 발생해야 한다.")
    void move_With_Collision() {
        // given
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));

        Position source = Position.of(0, 0);
        Position target = Position.of(0, 2);

        // expect
        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("폰을 움직일 때 바로 위에 상대 말이 있으면 움직일 수 없다.")
    void move_Pawn_Forward_Enemy() {
        // given
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));
        chessGame.move(Position.of(0, 1), Position.of(0, 3));
        chessGame.move(Position.of(0, 6), Position.of(0, 4));

        Position source = Position.of(0, 3);
        Position target = Position.of(0, 4);

        // expect
        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 2})
    @DisplayName("폰을 움직일 때 대각선에 상대 말이 없으면 움직일 수 없다.")
    void move_Pawn_With_Diagonal_Empty(int x) {
        // given
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));

        Position source = Position.of(1, 1);
        Position target = Position.of(x, 3);

        // expect
        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("아군 말의 위치로 이동하면 예외가 발생해야 한다.")
    void move_Same_Team_Position() {
        // given
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));

        Position source = Position.of(0, 0);
        Position target = Position.of(0, 1);

        // expect
        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }

    @Test
    @DisplayName("Knight는 이동 경로에 말이 있어도 움직일 수 있다.")
    void move_Knight_Ignore_Collision() {
        // given
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));
        chessGame.move(Position.of(1, 1), Position.of(1, 3));
        chessGame.move(Position.of(0, 6), Position.of(0, 4));

        Position source = Position.of(1, 0);
        Position target = Position.of(2, 2);

        // expect
        assertDoesNotThrow(() -> chessGame.move(source, target));
    }

    @Test
    @DisplayName("같은 위치로 움직이면 예외가 발생한다.")
    void move_Duplicate_Position() {
        // given
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.create()), new Turn(Team.WHITE));

        Position source = Position.of(1, 1);
        Position target = Position.of(1, 1);

        // expect
        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 이동할 수 없는 위치입니다.");
    }
}
