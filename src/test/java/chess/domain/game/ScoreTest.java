package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    @Test
    @DisplayName("폰이 수직으로 존재할 경우 총점을 계산한다.")
    void getWhiteScoreWhenPawnVertical() {
        Board board = Board.createChessBoard();
        board.movePiece(Position.valueOf("a2"), Position.valueOf("b3"));
        board.movePiece(Position.valueOf("c2"), Position.valueOf("b4"));

        Score score = new Score(board.getBoard());
        assertThat(score.getWhiteScore()).isEqualTo(36.5);
    }

    @Test
    @DisplayName("queen이 없고 폰이 수직으로 존재하는 경우 총점을 계산한다.")
    void getWhiteScoreWhenNoQueenPawnVertical() {
        Board board = Board.createChessBoard();
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"));

        Score score = new Score(board.getBoard());
        assertThat(score.getWhiteScore()).isEqualTo(28);
    }

    @Test
    @DisplayName("게임에서 이긴 플레이어의 Color 를 반환")
    void getWinColorWhenBlackWin() {
        Board board = Board.createChessBoard();
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"));

        Score score = new Score(board.getBoard());
        assertThat(score.getWinColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("게임에서 이긴 플레이어의 Color 를 반환")
    void getWinColorWhenWhiteWin() {
        Board board = Board.createChessBoard();
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"));

        Score score = new Score(board.getBoard());
        assertThat(score.getWinColor()).isEqualTo(Color.WHITE);
    }
}