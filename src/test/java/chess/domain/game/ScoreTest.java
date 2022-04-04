package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ScoreTest {

    private Board board;
    private DeadPieces deadPieces;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createChessBoard();
        deadPieces = new DeadPieces();
    }

    @Test
    @DisplayName("폰이 수직으로 존재할 경우의 총점을 계산한다.")
    void getWhiteScore_WhenPawnVertical() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("b3"), deadPieces);
        board.movePiece(Position.valueOf("c2"), Position.valueOf("b4"), deadPieces);

        assertThat(new Score(board.getBoard(), deadPieces).getWhiteScore()).isEqualTo(36.5);
    }

    @Test
    @DisplayName("Queen 이 없고 폰이 수직으로 존재하는 경우의 총점을 계산한다.")
    void getWhiteScore_WhenNoQueenAndPawnExistVertically() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"), deadPieces);

        assertThat(new Score(board.getBoard(), deadPieces).getWhiteScore()).isEqualTo(28);
    }

    @Test
    @DisplayName("Black 팀이 이겼을 때 게임에서 이긴 플레이어의 Team 를 반환")
    void getWinColor_WhenBlackWin() {
        board.movePiece(Position.valueOf("a2"), Position.valueOf("d1"), deadPieces);

        assertThat(new Score(board.getBoard(), deadPieces).getWinningTeam().getValue()).isEqualTo(Team.BLACK.getValue());
    }

    @Test
    @DisplayName("White 팀이 이겼을 때 게임에서 이긴 플레이어의 Team 를 반환")
    void getWinColor_WhenWhiteWin() {
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"), deadPieces);

        assertThat(new Score(board.getBoard(), deadPieces).getWinningTeam().getValue()).isEqualTo(Team.WHITE.getValue());
    }
}