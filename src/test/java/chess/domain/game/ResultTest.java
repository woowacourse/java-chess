package chess.domain.game;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = BoardFactory.createInitChessBoard();
        chessGame = new ChessGame(
                "ChessGame",
                board,
                new GameSwitch(true),
                new Turn(Team.WHITE)
        );
    }

    @Test
    @DisplayName("폰이 수직으로 존재할 경우의 총점을 계산한다.")
    void getWhiteScore_WhenPawnVertical() {
        board.movePiece(Position.of('a', 2), Position.of('b', 3));
        board.movePiece(Position.of('c', 2), Position.of('b', 4));

        assertThat(chessGame.generateResult().getWhiteScore()).isEqualTo(36.5);
    }

    @Test
    @DisplayName("Queen 이 없고 폰이 수직으로 존재하는 경우의 총점을 계산한다.")
    void getWhiteScore_WhenNoQueenAndPawnExistVertically() {
        board.movePiece(Position.of('a', 2), Position.of('d', 1));

        assertThat(chessGame.generateResult().getWhiteScore()).isEqualTo(28);
    }

    @Test
    @DisplayName("Black 팀이 이겼을 때 게임에서 이긴 플레이어의 Team 를 반환")
    void getWinColor_WhenBlackWin() {
        board.movePiece(Position.of('a', 2), Position.of('d', 1));

        assertThat(chessGame.generateResult().getWinningTeam().getValue()).isEqualTo(Team.BLACK.getValue());
    }

    @Test
    @DisplayName("White 팀이 이겼을 때 게임에서 이긴 플레이어의 Team 를 반환")
    void getWinColor_WhenWhiteWin() {
        board.movePiece(Position.of('a', 7), Position.of('b', 6));

        assertThat(chessGame.generateResult().getWinningTeam().getValue()).isEqualTo(Team.WHITE.getValue());
    }
}