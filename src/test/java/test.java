import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.dto.BoardDto;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    void 보드출력테스트(){
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        BoardDto boardDto = new BoardDto(chessBoard);
        System.out.println(boardDto.getResult());
    }
}
