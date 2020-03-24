package chess.domain;

import chess.Team;
import chess.domain.chesspiece.ChessPiece;
import chess.factory.ChessPieceFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {
    @Test
    @DisplayName("ChessBoard 생성 테스트")
    void create() {
        List<ChessPiece> blackChessPieces = ChessPieceFactory.create(Team.BLACK);
        List<ChessPiece> whiteChessPieces = ChessPieceFactory.create(Team.WHITE);
        ChessBoard chessBoard = new ChessBoard(blackChessPieces, whiteChessPieces);
        assertThat(chessBoard).isInstanceOf(ChessBoard.class);
    }
}
