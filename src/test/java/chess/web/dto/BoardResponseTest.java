package chess.web.dto;

import chess.domain.ChessBoard;
import chess.domain.generator.InitBoardGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardResponseTest {

    @DisplayName("Test")
    @Test
    void testBoard() {
        BoardResponse boardResponse = new BoardResponse(new ChessBoard(new InitBoardGenerator()).getBoard());

        List<PieceResponse> value = boardResponse.getValue();

        for (PieceResponse pieceResponse : value) {
            System.out.println(pieceResponse);
        }
    }
}
