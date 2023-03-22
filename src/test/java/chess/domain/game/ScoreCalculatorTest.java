package chess.domain.game;

import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreCalculatorTest {

    ChessGame chessGame;

    @BeforeEach
    void setUp() {
        chessGame = new ChessGame(new Board());
        chessGame.inputGameCommand(GameCommand.START);
    }

    /*
    RNBQKBNR
    PPPPPPPP
    ........
    ........
    ........
    ........
    pppppppp
    rnbqkbnr
     */
    @Test
    @DisplayName("처음 상태의 체스판에서는 기물 점수가 38점이어야 한다.")
    void calculateScore() {
        double expectedScore = 38d;
        chessGame.inputGameCommand(GameCommand.END);

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(expectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(expectedScore);
    }

    /*
    RNBQKBNR
    P.PPPPPP
    ........
    .p......
    ........
    ........
    .ppppppp
    rnbqkbnr
     */
    @Test
    @DisplayName("같은 File에 같은 팀 폰이 2개이상이면 0.5점씩 처리한다.")
    void calculateScorePawn() {
        double expectedScore = 37d;
        chessGame.progress(new Position(2, 1), new Position(4, 1));
        chessGame.progress(new Position(7, 2), new Position(5, 2));
        chessGame.progress(new Position(4, 1), new Position(5, 2));

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(expectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(expectedScore);
    }

    /*
    .NBQKBNR
    ..PPPPPP
    r.......
    ........
    ........
    ........
    .ppppppp
    .nbqkbnr
     */
    @Test
    @DisplayName("룩은 5점으로 처리한다.")
    void calculateScoreRook() {
        double blackExpectedScore = 31d;
        double whiteExpectedScore = 37d;
        chessGame.progress(new Position(2, 1), new Position(4, 1));
        chessGame.progress(new Position(7, 2), new Position(5, 2));
        chessGame.progress(new Position(4, 1), new Position(5, 2));
        chessGame.progress(new Position(7, 1), new Position(6, 1));
        chessGame.progress(new Position(5, 2), new Position(6, 1));
        chessGame.progress(new Position(8, 1), new Position(6, 1));
        chessGame.progress(new Position(1, 1), new Position(6, 1));

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(blackExpectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(whiteExpectedScore);
    }

    /*
    .rBQKBNR
    ..PPPPP.
    ........
    .......P
    ........
    ........
    .ppppppp
    .nbqkbnr
     */
    @Test
    @DisplayName("나이트는 2.5점으로 처리한다.")
    void calculateScoreKnight() {
        double blackExpectedScore = 28.5d;
        double whiteExpectedScore = 37d;
        chessGame.progress(new Position(2, 1), new Position(4, 1));
        chessGame.progress(new Position(7, 2), new Position(5, 2));
        chessGame.progress(new Position(4, 1), new Position(5, 2));
        chessGame.progress(new Position(7, 1), new Position(6, 1));
        chessGame.progress(new Position(5, 2), new Position(6, 1));
        chessGame.progress(new Position(8, 1), new Position(6, 1));
        chessGame.progress(new Position(1, 1), new Position(6, 1));
        chessGame.progress(new Position(7, 8), new Position(6, 8));
        chessGame.progress(new Position(6, 1), new Position(8, 1));
        chessGame.progress(new Position(6, 8), new Position(5, 8));
        chessGame.progress(new Position(8, 1), new Position(8, 2));

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(blackExpectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(whiteExpectedScore);
    }

    /*
    ..rQKBNR
    ..PPPPP.
    ........
    ........
    .......P
    ........
    .ppppppp
    .nbqkbnr
     */
    @Test
    @DisplayName("나이트는 3점으로 처리한다.")
    void calculateScoreBishop() {
        double blackExpectedScore = 25.5d;
        double whiteExpectedScore = 37d;
        chessGame.progress(new Position(2, 1), new Position(4, 1));
        chessGame.progress(new Position(7, 2), new Position(5, 2));
        chessGame.progress(new Position(4, 1), new Position(5, 2));
        chessGame.progress(new Position(7, 1), new Position(6, 1));
        chessGame.progress(new Position(5, 2), new Position(6, 1));
        chessGame.progress(new Position(8, 1), new Position(6, 1));
        chessGame.progress(new Position(1, 1), new Position(6, 1));
        chessGame.progress(new Position(7, 8), new Position(6, 8));
        chessGame.progress(new Position(6, 1), new Position(8, 1));
        chessGame.progress(new Position(6, 8), new Position(5, 8));
        chessGame.progress(new Position(8, 1), new Position(8, 2));
        chessGame.progress(new Position(5, 8), new Position(4, 8));
        chessGame.progress(new Position(8, 2), new Position(8, 3));

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(blackExpectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(whiteExpectedScore);
    }

    /*
    ...rKBNR
    ..PPPPP.
    ........
    ........
    ........
    .......P
    .ppppppp
    .nbqkbnr
     */
    @Test
    @DisplayName("퀸은 9점으로 처리한다.")
    void calculateScoreQueen() {
        double blackExpectedScore = 16.5d;
        double whiteExpectedScore = 37d;
        chessGame.progress(new Position(2, 1), new Position(4, 1));
        chessGame.progress(new Position(7, 2), new Position(5, 2));
        chessGame.progress(new Position(4, 1), new Position(5, 2));
        chessGame.progress(new Position(7, 1), new Position(6, 1));
        chessGame.progress(new Position(5, 2), new Position(6, 1));
        chessGame.progress(new Position(8, 1), new Position(6, 1));
        chessGame.progress(new Position(1, 1), new Position(6, 1));
        chessGame.progress(new Position(7, 8), new Position(6, 8));
        chessGame.progress(new Position(6, 1), new Position(8, 1));
        chessGame.progress(new Position(6, 8), new Position(5, 8));
        chessGame.progress(new Position(8, 1), new Position(8, 2));
        chessGame.progress(new Position(5, 8), new Position(4, 8));
        chessGame.progress(new Position(8, 2), new Position(8, 3));
        chessGame.progress(new Position(4, 8), new Position(3, 8));
        chessGame.progress(new Position(8, 3), new Position(8, 4));

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(blackExpectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(whiteExpectedScore);
    }

    /*
    ....rBNR
    ..PPPP..
    ......P.
    ........
    ........
    .......P
    .ppppppp
    .nbqkbnr
     */
    @Test
    @DisplayName("킹은 0점으로 처리한다.")
    void calculateScoreKing() {
        double blackExpectedScore = 16.5d;
        double whiteExpectedScore = 37d;
        chessGame.progress(new Position(2, 1), new Position(4, 1));
        chessGame.progress(new Position(7, 2), new Position(5, 2));
        chessGame.progress(new Position(4, 1), new Position(5, 2));
        chessGame.progress(new Position(7, 1), new Position(6, 1));
        chessGame.progress(new Position(5, 2), new Position(6, 1));
        chessGame.progress(new Position(8, 1), new Position(6, 1));
        chessGame.progress(new Position(1, 1), new Position(6, 1));
        chessGame.progress(new Position(7, 8), new Position(6, 8));
        chessGame.progress(new Position(6, 1), new Position(8, 1));
        chessGame.progress(new Position(6, 8), new Position(5, 8));
        chessGame.progress(new Position(8, 1), new Position(8, 2));
        chessGame.progress(new Position(5, 8), new Position(4, 8));
        chessGame.progress(new Position(8, 2), new Position(8, 3));
        chessGame.progress(new Position(4, 8), new Position(3, 8));
        chessGame.progress(new Position(8, 3), new Position(8, 4));
        chessGame.progress(new Position(7, 7), new Position(6, 7));
        chessGame.progress(new Position(8, 4), new Position(8, 5));

        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.BLACK).calculateScore()).isEqualTo(blackExpectedScore);
        assertThat(new ScoreCalculator(chessGame.getBoard(), Team.WHITE).calculateScore()).isEqualTo(whiteExpectedScore);
    }
}
