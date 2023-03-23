package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Score;
import chess.domain.position.Position;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StatusFactoryTest {
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 초기상태")
    void createStatus() {
        Board board = ChessBoard.create();
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(38));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(38));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 검은색 폰 1개 잡힘")
    void createStatus2() {
        Board board = ChessBoard.create();
        board.move(Position.from("a2"), Position.from("a4"));
        board.move(Position.from("b7"), Position.from("b5"));
        board.move(Position.from("a4"), Position.from("b5"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(37));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(37));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 검은색 폰 2개 잡힘")
    void createStatus3() {
        Board board = ChessBoard.create();
        board.move(Position.from("a2"), Position.from("a4"));
        board.move(Position.from("b7"), Position.from("b5"));
        board.move(Position.from("a4"), Position.from("b5"));
        board.move(Position.from("c7"), Position.from("c6"));
        board.move(Position.from("b5"), Position.from("c6"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(37));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(36));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 검은색 폰 3개 잡힘")
    void createStatus4() {
        Board board = ChessBoard.create();
        board.move(Position.from("a2"), Position.from("a4"));
        board.move(Position.from("b7"), Position.from("b5"));
        board.move(Position.from("a4"), Position.from("b5"));
        board.move(Position.from("c7"), Position.from("c6"));
        board.move(Position.from("b5"), Position.from("c6"));
        board.move(Position.from("d7"), Position.from("d5"));
        board.move(Position.from("c6"), Position.from("d5"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(37));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(35));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 비숍 잡힙")
    void createStatus5() {
        Board board = ChessBoard.create();
        board.move(Position.from("c2"), Position.from("c8"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(38));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(35));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 퀸 잡힙")
    void createStatus6() {
        Board board = ChessBoard.create();
        board.move(Position.from("d2"), Position.from("d8"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(38));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(29));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 킹 잡힙")
    void createStatus7() {
        Board board = ChessBoard.create();
        board.move(Position.from("e2"), Position.from("e8"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScore(Color.WHITE)).isEqualTo(Score.from(38));
        Assertions.assertThat(status.getScore(Color.BLACK)).isEqualTo(Score.from(38));
    }
    
    @Test
    @DisplayName("Status 올바른 계산 확인 - 점수 차이")
    void calculateScoreDifference() {
        Board board = ChessBoard.create();
        board.move(Position.from("a2"), Position.from("a7"));
        Status status = StatusFactory.createStatus(board);
        Assertions.assertThat(status.getScoreDifference()).isEqualTo(1);
    }
}