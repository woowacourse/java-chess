package chess.domain.game;

import static chess.domain.PositionFixture.A_1;
import static chess.domain.PositionFixture.A_2;
import static chess.domain.PositionFixture.B_8;
import static chess.domain.PositionFixture.C_1;
import static chess.domain.PositionFixture.C_6;
import static chess.domain.PositionFixture.D_1;
import static chess.domain.PositionFixture.D_2;
import static chess.domain.PositionFixture.D_4;
import static chess.domain.PositionFixture.E_2;
import static chess.domain.PositionFixture.E_4;
import static chess.domain.PositionFixture.E_5;
import static chess.domain.PositionFixture.E_7;
import static chess.domain.PositionFixture.E_8;
import static chess.domain.PositionFixture.F_4;
import static chess.domain.PositionFixture.F_5;
import static chess.domain.PositionFixture.F_7;
import static chess.domain.PositionFixture.H_5;
import static chess.domain.piece.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Pawn;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(ReplaceUnderscores.class)
@SuppressWarnings("NonAsciiCharacters")
class GameResultTest {

    @Test
    void 초기점수를_계산한다() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        GameResult result = chessGame.getResult();

        assertThat(result.calculateScore(WHITE)).isEqualTo(38.0);
    }

    @Test
    void 폰이_같은_File에_있을_때의_점수를_계산한다() {
        Board board = new Board(Map.of(A_1, new Pawn(WHITE), A_2, new Pawn(WHITE)));
        ChessGame chessGame = new ChessGame(board, WHITE);
        GameResult result = chessGame.getResult();

        assertThat(result.calculateScore(WHITE)).isEqualTo(1.0);
    }

    @Test
    void 왕이_잡힌_경우_왕을_잡은쪽이_승리한다() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(E_2, E_4);
        chessGame.move(E_7, E_5);
        chessGame.move(D_1, H_5);
        chessGame.move(F_7, F_5);
        chessGame.move(H_5, E_8);

        GameResult result = chessGame.getResult();

        assertThat(result.getWinner()).isEqualTo(Color.WHITE);
    }

    @Test
    void 왕이_잡히지_않은_경우_점수를_비교하여_결과를_계산한다() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        chessGame.move(D_2, D_4);
        chessGame.move(B_8, C_6);
        chessGame.move(C_1, F_4);
        chessGame.move(C_6, D_4);
        GameResult result = chessGame.getResult();

        assertThat(result.getWinner()).isEqualTo(Color.BLACK);
    }

    @Test
    void 양쪽다_왕이_살아있고_점수가_동일할_경우_EMPTY를_반환한다() {
        ChessGame chessGame = new ChessGame();
        chessGame.start();
        GameResult result = chessGame.getResult();

        assertThat(result.getWinner()).isEqualTo(Color.EMPTY);
    }
}
