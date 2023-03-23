package chess.domain;

import chess.domain.board.File;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static chess.domain.board.Positions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ChessGameTest {

    public static final Position A2 = new Position(File.A, Rank.TWO);
    public static final Position A3 = new Position(File.A, Rank.THREE);

    @DisplayName("턴을 진행하면 턴이 바뀐다")
    @Test
    void playTurn_change() {
        ChessGame chessGame = new ChessGame();
        final var source = A2;
        final var target = A3;
        chessGame.playTurn(source, target);
        assertThat(chessGame.getTurn()).isEqualTo(Color.BLACK);
    }

    @DisplayName("킹이 죽었는지 알 수 있다")
    @Test
    void isKingDead_true() {
        ChessGame chessGame = new ChessGame();
        chessGame.playTurn(E2, E4);
        chessGame.playTurn(D7, D5);
        chessGame.playTurn(E1, E2);
        chessGame.playTurn(D8, D7);
        chessGame.playTurn(A2, A4);
        chessGame.playTurn(D7, F5);
        chessGame.playTurn(A4, A5);
        chessGame.playTurn(F5, G4);
        chessGame.playTurn(A5, A6);
        chessGame.playTurn(G4, E2);

        assertThat(chessGame.isKingDead()).isTrue();
    }

    @DisplayName("킹이 안죽었다")
    @Test
    void isKingDead_false() {
        ChessGame chessGame = new ChessGame();
        assertThat(chessGame.isKingDead()).isFalse();
    }


    @DisplayName("초기 백의 점수를 계산한다")
    @Test
    void calculateScore_White() {
        ChessGame chessGame = new ChessGame();
        assertThat(chessGame.calculateWhiteScore()).isEqualTo(38);
    }

    @DisplayName("초기 흑의 점수를 계산한다")
    @Test
    void calculateScore_Black() {
        ChessGame chessGame = new ChessGame();
        assertThat(chessGame.calculateBlackScore()).isEqualTo(38);
    }
}
