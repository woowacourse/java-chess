package chess.web.dto;

import chess.domain.Board;
import chess.domain.ChessBoard;
import chess.domain.generator.InitBoardGenerator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardResponseTest {

    @DisplayName("BoardResponse 생성")
    @Test
    void 생성() {
        ChessBoard chessBoard = new ChessBoard(new InitBoardGenerator());
        Board board = chessBoard.getBoard();

        BoardResponse boardResponse = new BoardResponse(board);
        List<PieceResponse> pieces = boardResponse.getValue();

        pieces.forEach(System.out::println);
    }
}
