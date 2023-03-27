package chess.domain;

import chess.db.dao.BoardDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class ChessGameTest {

    @Test
    @DisplayName("ChessGame이 정상적으로 생성된다.")
    void createInitialPawn() {
        Board board = new Board(BoardGenerator.init());
        Team team = Team.WHITE;

        assertDoesNotThrow(() -> new ChessGame(board, team));
    }

    @DisplayName("입력받은 칸에 맞춰 Piece를 움직인다.")
    @ParameterizedTest(name = "현재 팀: {2} , source: {0}, destination: {1}")
    @CsvSource({"a2,a3,WHITE", "b7,b5,BLACK"})
    void movePiece_success(String source, String destination, Team team) {
        Board board = new Board(BoardGenerator.init());
        ChessGame chessGame = new ChessGame(board, team);

        assertDoesNotThrow(() -> chessGame.movePiece(source, destination));
    }

    @DisplayName("다른 팀 말을 움직일 경우 예외가 발생한다.")
    @ParameterizedTest(name = "현재 팀: {2} , source: {0}, destination: {1}")
    @CsvSource({"a2,a3,BLACK", "b7,b5,WHITE"})
    void movePiece_fail(String source, String destination, Team team) {
        Board board = new Board(BoardGenerator.init());
        ChessGame chessGame = new ChessGame(board, team);
        BoardDao boardDao = new BoardDao();
        boardDao.insert(BoardGenerator.init());

        assertThatThrownBy(() -> chessGame.movePiece(source, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 팀 말을 움직여 주세요.");
    }

    @Test
    @DisplayName("남아있는 말들의 점수를 통해 이긴 진영을 구한다.")
    void calculateWinnerTeam() {
        ChessGame chessGame = new ChessGame(new Board(BoardGenerator.init()), Team.WHITE);

        assertThat(chessGame.calculateWinnerTeam()).isEqualTo(Team.EMPTY);
    }
}