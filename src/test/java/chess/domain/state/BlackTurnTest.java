package chess.domain.state;

import chess.domain.Team;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BlackTurnTest {
    @ParameterizedTest
    @CsvSource(value = {"a1:Rook", "b1:Knight", "c8:Bishop", "d1:Queen", "e1:King", "a2:Pawn", "a3:Blank"}, delimiter = ':')
    void findPiece(String position, String symbol) {
        GameState board = new BlackTurn(BoardInitialize.create());
        assertThat(board.getPiece(Position.from(position)).getClass().getSimpleName()).isEqualTo(symbol);
    }

    @Test
    void movePiece() {
        GameState board = new BlackTurn(BoardInitialize.create());
        String source = "g7";
        String destination = "g6";
        board.move(source, destination);
        assertThat(board.getPiece(Position.from(destination)).isPawn()).isTrue();
        assertThat(board.getPiece(Position.from(source)).isBlank()).isTrue();
    }

    @Test
    @DisplayName("같은 팀 말 kill을 시도할 시, 예외가 발생한다.")
    void killSameTeam() {
        GameState board = new BlackTurn(BoardInitialize.create());
        String source = "d8";
        String destination = "e8";
        Assertions.assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("목적지에 같은 팀 말이 있습니다.");
    }

    @Test
    @DisplayName("다른 팀 말을 움직일 시, 예외가 발생한다.")
    void otherTeamPieceMove() {
        GameState board = new BlackTurn(BoardInitialize.create());
        String source = "c2";
        String destination = "c3";
        Assertions.assertThatThrownBy(() -> board.move(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("상대편 말을 옮길 수 없습니다.");
    }

    @Test
    @DisplayName("말을 움직일 시, WhiteTurn으로 변경된다.")
    void changeTeam() {
        GameState board = new BlackTurn(BoardInitialize.create());
        String source = "e7";
        String destination = "e6";
        GameState whiteTurn = board.move(source, destination);
        assertThat(whiteTurn.getTeam()).isEqualTo(Team.WHITE);
    }
}