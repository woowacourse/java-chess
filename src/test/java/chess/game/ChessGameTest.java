package chess.game;

import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChessGameTest {

    @Test
    @DisplayName("화이트팀 차례에 블랙팀이 움직이는 경우 예외가 발생한다.")
    void movePieceNotWithWrongTeam1() {
        ChessGame chessGame = new ChessGame();
        Position source = Position.of(0, 6);
        Position target = Position.of(0, 5);

        assertThatThrownBy(() -> chessGame.move(source, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] WHITE팀의 말만 이동할 수 있습니다.");

    }

    @Test
    @DisplayName("블랙팀 차례에 화이트팀이 움직이는 경우 예외가 발생한다.")
    void movePieceNotWithWrongTeam2() {
        ChessGame chessGame = new ChessGame();
        Position whiteTeamSource = Position.of(0, 1);
        Position whiteTeamTarget = Position.of(0, 2);
        chessGame.move(whiteTeamSource, whiteTeamTarget);

        Position blackTeamSource = Position.of(0, 2);
        Position blackTeamTarget = Position.of(0, 3);

        assertThatThrownBy(() -> chessGame.move(blackTeamSource, blackTeamTarget))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] BLACK팀의 말만 이동할 수 있습니다.");
    }

}
