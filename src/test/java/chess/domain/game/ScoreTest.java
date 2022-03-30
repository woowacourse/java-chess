package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    CatchPieces catchPieces = new CatchPieces();
    Board board;

    @BeforeEach
    void setUp() {
        board = Board.createChessBoard();
    }

    @Test
    @DisplayName("폰이 수직으로 존재할 경우 총점을 계산한다.")
    void getWhiteScore_WhenPawnVertical() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("b3"), catchPieces);
        board.movePiece(Position.valueOf("c2"), Position.valueOf("b4"), catchPieces);

        assertThat(new Score(board.getBoard()).getWhiteScore()).isEqualTo(36.5);
    }

    @Test
    @DisplayName("Queen 이 없고 폰이 수직으로 존재하는 경우 총점을 계산한다.")
    void getWhiteScore_WhenNoQueenAndPawnExistVertically() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"), catchPieces);

        assertThat(new Score(board.getBoard()).getWhiteScore()).isEqualTo(28);
    }

    @Test
    @DisplayName("Black 팀이 이겼을 때 게임에서 이긴 플레이어의 Color 를 반환")
    void getWinColor_WhenBlackWin() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"), catchPieces);

        assertThat(new Score(board.getBoard()).getWinColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("White 팀이 이겼을 때 게임에서 이긴 플레이어의 Color 를 반환")
    void getWinColor_WhenWhiteWin() {
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"), catchPieces);

        assertThat(new Score(board.getBoard()).getWinColor()).isEqualTo(Color.WHITE);
    }
}