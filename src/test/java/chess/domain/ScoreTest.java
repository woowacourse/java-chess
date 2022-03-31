package chess.domain;

import chess.domain.position.Column;
import chess.domain.state.BoardInitialize;
import chess.domain.state.GameState;
import chess.domain.state.WhiteTurn;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class ScoreTest {
    @Test
    @DisplayName("Board와 Team을 입력 받아 현재 말의 총 점수를 조회한다.")
    void createTotalScore() {
//        WhiteTurn whiteTurn = new WhiteTurn(BoardInitialize.create());
//        int totalScore = (int) new Score(whiteTurn.getBoard(), Team.WHITE).getTotalScore();
//        assertThat(totalScore).isEqualTo(38);
    }

    @Test
    @DisplayName("")
    void ScoreTest() {
        GameState chessGame = new WhiteTurn(BoardInitialize.create());
        chessGame = chessGame.move("f2", "f4");
        chessGame = chessGame.move("e7", "e5");
        chessGame = chessGame.move("f4", "e5");
//        double totalScore = new Score(chessGame.getBoard(), Team.WHITE).getTotalScore();
//        double totalScoreBlack = new Score(chessGame.getBoard(), Team.BLACK).getTotalScore();
//        System.out.println(totalScore);
//        System.out.println(totalScoreBlack);
    }
}