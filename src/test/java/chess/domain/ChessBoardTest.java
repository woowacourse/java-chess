package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.factory.RowFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("ChessBoard 생성 테스트")
    void create() {
        List<ChessPiece> blackChessPieces = RowFactory.blackTeamCreate();
        List<ChessPiece> whiteChessPieces = RowFactory.whiteTeamCreate();
        ChessBoard chessBoard = new ChessBoard(blackChessPieces, whiteChessPieces);
        assertThat(chessBoard).isInstanceOf(ChessBoard.class);
    }
}
