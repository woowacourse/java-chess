package chess.domain.board.setting;


import static chess.domain.piece.type.PieceWithColorType.B_BP;
import static chess.domain.piece.type.PieceWithColorType.B_KG;
import static chess.domain.piece.type.PieceWithColorType.B_PN;
import static chess.domain.piece.type.PieceWithColorType.B_QN;
import static chess.domain.piece.type.PieceWithColorType.B_RK;
import static chess.domain.piece.type.PieceWithColorType.W_NT;
import static chess.domain.piece.type.PieceWithColorType.W_PN;
import static chess.domain.piece.type.PieceWithColorType.W_QN;
import static chess.domain.piece.type.PieceWithColorType.W_RK;
import static chess.utils.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.controller.dto.response.BoardStatusResponseDTOForDB;
import chess.domain.game.ChessGameForDB;
import chess.utils.DBCleaner;
import java.sql.SQLException;
import java.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardCustomSettingTest {

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("보드 Custom 세팅")
    @Test
    void boardCustomSetting() throws SQLException {
        BoardSetting customBoardSetting = new BoardCustomSetting(
            Arrays.asList(
                null, B_KG, B_RK, null, null, null, null, null,
                B_PN, null, B_PN, B_BP, null, null, null, null,
                null, B_PN, null, null, B_QN, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, W_NT, W_QN, null,
                null, null, null, null, null, W_PN, null, W_PN,
                null, null, null, null, null, W_PN, W_PN, null,
                null, null, null, null, W_RK, null, null, null)
        );

        ChessGameForDB chessGame = new ChessGameForDB();
        Long gameId = chessGame.createNew(customBoardSetting, TEST_TITLE);

        BoardStatusResponseDTOForDB boardStatus = chessGame.getBoardStatus(gameId);

        assertThat(boardStatus.getCellsStatus()).containsExactly(
            ".", "K", "R", ".", ".", ".", ".", ".",
            "P", ".", "P", "B", ".", ".", ".", ".",
            ".", "P", ".", ".", "Q", ".", ".", ".",
            ".", ".", ".", ".", ".", ".", ".", ".",
            ".", ".", ".", ".", ".", "n", "q", ".",
            ".", ".", ".", ".", ".", "p", ".", "p",
            ".", ".", ".", ".", ".", "p", "p", ".",
            ".", ".", ".", ".", "r", ".", ".", "."
        );
    }

    @DisplayName("보드 Custom 세팅 객체 생성 에러")
    @Test
    void boardCustomSettingError() {
        assertThatThrownBy(() -> new BoardCustomSetting(Arrays.asList(
            null, B_KG, B_RK, null, null, null, null, null,
            B_PN, null, B_PN, B_BP, null, null, null, null,
            null, B_PN, null, null, B_QN, null, null, null,
            null, null, null, null, null, null, null, null,
            null, null, null, null, null, W_NT, W_QN, null,
            null, null, null, null, null, W_PN, null, W_PN,
            null, null, null, null, null, W_PN, W_PN, null,
            null, null, null, null, W_RK, null, null
        ))).isInstanceOf(IllegalArgumentException.class);
    }
}