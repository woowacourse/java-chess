package chess.domain;

import chess.dto.CommandDto;
import database.FakeBoardJdbcDao;
import database.FakeGameJdbcDao;
import database.FakeJdbcTemplate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class ChessGameTest {
    @Test
    @DisplayName("킹을 잡는 경우 테스트")
    void capturing_king() throws SQLException {
        ChessGame chessGame = ChessGame.from(new FakeGameJdbcDao(), new FakeBoardJdbcDao(), new FakeJdbcTemplate());
        chessGame.start();
        chessGame.move(CommandDto.from("move e2 e4".split(" ")));
        chessGame.move(CommandDto.from("move f7 f5".split(" ")));
        chessGame.move(CommandDto.from("move e4 f5".split(" ")));
        chessGame.move(CommandDto.from("move g7 g5".split(" ")));
        chessGame.move(CommandDto.from("move d1 h5".split(" ")));
        chessGame.move(CommandDto.from("move a7 a5".split(" ")));
        chessGame.move(CommandDto.from("move h5 e8".split(" ")));

        Assertions.assertThat(chessGame.isGameEnd()).isEqualTo(true);
    }

}
