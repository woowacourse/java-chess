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
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.game.ChessGame;
import chess.utils.PositionConverter;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardCustomSettingTest {
    @DisplayName("보드 Custom 세팅")
    @Test
    void boardCustomSetting() {
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

        ChessGame chessGame = new ChessGame(customBoardSetting);

        List<String> cellsStatus = chessGame.boardCellsStatus();

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