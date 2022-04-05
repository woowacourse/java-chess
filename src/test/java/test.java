import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.dto.BoardDto;
import chess.domain.position.Position;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    void 보드출력테스트(){
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        BoardDto boardDto = new BoardDto(chessBoard);
        System.out.println(boardDto.getResult());
    }

    @Test
    void 위치테스트(){
        Position a3 = Position.of("A3");
        System.out.println(a3);
    }
}
