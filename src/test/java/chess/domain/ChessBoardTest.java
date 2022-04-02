package chess.domain;

import static chess.domain.piece.Team.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChessBoardTest {

    @DisplayName("모든 피스들의 합은 38이다.")
    @Test
    public void calculateScore() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        //when
        double score = chessBoard.calculateScoreByTeam(WHITE);

        //then
        assertThat(score).isEqualTo(38);
    }

    @DisplayName("폰이 같은 file에 2개 이상인 경우 0.5로 계산한다.")
    @Test
    public void calculatePawnByHalf() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.move(Command.from("move a2 a4"));
        chessBoard.move(Command.from("move b7 b5"));
        chessBoard.move(Command.from("move a4 b5"));

        //when
        double score = chessBoard.calculateScoreByTeam(WHITE);

        //then
        assertThat(score).isEqualTo(37);
    }
    
    @DisplayName("처음에 King이 2개 존재하는지 확인")
    @Test
    public void testExistKing() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        //when
        boolean existKing = chessBoard.isExistKing();

        //then
        assertThat(existKing).isTrue();
    }

    @DisplayName("King이 하나 잡히면 isExistKing()이 false가 반환되어야한다.")
    @Test
    public void testExistKing2() {
        //given
        ChessBoard chessBoard = new ChessBoard();

        chessBoard.move(Command.from("move e2 e4"));
        chessBoard.move(Command.from("move f7 f5"));
        chessBoard.move(Command.from("move d1 h5"));
        chessBoard.move(Command.from("move a7 a5"));
        chessBoard.move(Command.from("move h5 e8"));

        //when
        boolean existKing = chessBoard.isExistKing();

        //then
        assertThat(existKing).isFalse();
    }

    @DisplayName("이긴 팀 구하는 findWinTeam 테스트")
    @Test
    public void findWinTeam() {
        //given
        ChessGame chessGame = new ChessGame();

        chessGame.progress(Command.from("start"));
        chessGame.progress(Command.from("move e2 e4"));
        chessGame.progress(Command.from("move f7 f5"));
        chessGame.progress(Command.from("move d1 h5"));
        chessGame.progress(Command.from("move a7 a5"));
        chessGame.progress(Command.from("move h5 e8"));

        //when
        String winTeam = chessGame.getWinTeamName();

        //then
        assertThat(winTeam).isEqualTo("WHITE");
    }
}
