import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardGenerator;
import chess.domain.dao.ChessGame;
import chess.domain.dao.ChessGameDAO;
import chess.domain.dao.Movement;
import chess.domain.dao.MovementDAO;
import chess.web.dto.BoardDTO;
import chess.domain.position.Position;
import chess.web.dto.ChessGameDTO;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class test {

    @Test
    void 보드출력테스트(){
        ChessBoard chessBoard = new ChessBoard(new ChessBoardGenerator());
        BoardDTO boardDto = new BoardDTO(chessBoard);
        System.out.println(boardDto.getResult());
    }

    @Test
    void 위치테스트(){
        Position a3 = Position.of("A3");
        System.out.println(a3);
    }

    @Test
    void 추가테스트(){
        MovementDAO movementDao = new MovementDAO();
        Movement movement = new Movement(Position.of("B2"), Position.of("B4"));
        movement.setGameId("3");

        Assertions.assertDoesNotThrow(() -> movementDao.addMoveCommand(movement));
    }

    @Test
    void 체스게임방_추가(){
        ChessGameDAO chessGameDAO = new ChessGameDAO();
        try{
            ChessGame chessGame = ChessGame.initChessGame();
            chessGame.setName("false");
            chessGame.setEnd(false);
            chessGameDAO.addGame(chessGame);
        }catch (Exception e){
        }
    }

    @Test
    void 체스게임방_출력(){
        ChessGameDAO chessGameDAO = new ChessGameDAO();
        try{
            List<ChessGameDTO> activeGames = chessGameDAO.findActiveGames();
            System.out.println(activeGames);
        }catch (Exception e){
        }
    }
}
