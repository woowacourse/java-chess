package chess.domain;

import static chess.domain.piece.Team.BLACK;
import static chess.domain.piece.Team.WHITE;
import static chess.domain.position.File.D;
import static chess.domain.position.Rank.ONE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameTest {

    @DisplayName("킹이 양팀에 모두 존재하는지 여부 테스트")
    @Test
    public void testExistKing() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        boolean existKing = chessGame.isExistKing();

        //then
        assertThat(existKing).isTrue();
    }

    @DisplayName("점수 계산 기능 테스트")
    @Test
    public void calculateResult() {
        //given
        ChessGame chessGame = new ChessGame();

        chessGame.progress(Command.from("start"));
        chessGame.progress(Command.from("move a2 a4"));
        chessGame.progress(Command.from("move b7 b5"));
        chessGame.progress(Command.from("move a4 b5"));

        //when
        Map<Team, Double> teamScores = chessGame.calculateResult();

        //then
        assertThat(teamScores.get(WHITE)).isEqualTo(37);
        assertThat(teamScores.get(BLACK)).isEqualTo(37);
    }

    @DisplayName("위치로 심볼을 구하는 기능 테스트")
    @Test
    public void testSymbolByPosition() {
        //given
        ChessGame chessGame = new ChessGame();

        //when
        String symbol = chessGame.getSymbolByPosition(Position.of(D, ONE));

        //then
        assertThat(symbol).isEqualTo("q");
    }
}
