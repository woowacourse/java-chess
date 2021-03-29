package chess.utils.position.converter;

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

import chess.domain.board.setting.BoardCustomSetting;
import chess.domain.board.setting.BoardSetting;
import chess.domain.game.ChessGameForDB;
import chess.utils.DBCleaner;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PositionConverterTest {
    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("위치 -> 셀 상태 리스트의 인덱스 변환 테스트")
    @Test
    void convert() throws SQLException {
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
        List<String> cellsStatus = chessGame.getBoardStatus(gameId).getCellsStatus();

        int cellIndexOfBlackPawn = PositionConverter.convertToCellsStatusIndex("a7");
        int cellIndexOfBlackKing = PositionConverter.convertToCellsStatusIndex("b8");
        int cellIndexOfEmpty1 = PositionConverter.convertToCellsStatusIndex("b7");
        int cellIndexOfWhiteKnight = PositionConverter.convertToCellsStatusIndex("f4");
        int cellIndexOfWhiteQueen = PositionConverter.convertToCellsStatusIndex("g4");
        int cellIndexOfEmpty2 = PositionConverter.convertToCellsStatusIndex("g3");

        assertThat(cellsStatus.get(cellIndexOfBlackPawn)).isEqualTo("P");
        assertThat(cellsStatus.get(cellIndexOfBlackKing)).isEqualTo("K");
        assertThat(cellsStatus.get(cellIndexOfEmpty1)).isEqualTo(".");
        assertThat(cellsStatus.get(cellIndexOfWhiteKnight)).isEqualTo("n");
        assertThat(cellsStatus.get(cellIndexOfWhiteQueen)).isEqualTo("q");
        assertThat(cellsStatus.get(cellIndexOfEmpty2)).isEqualTo(".");
    }
}
