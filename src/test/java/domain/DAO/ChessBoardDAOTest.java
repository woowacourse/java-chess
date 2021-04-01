package domain.DAO;

import chess.DAO.ChessBoardDAO;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.sql.SQLException;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessBoardDAOTest {

    private ChessBoardDAO chessBoardDAO;

    @BeforeEach
    void setDB() {
        this.chessBoardDAO = new ChessBoardDAO();
    }

//    @Test  // Caution! : insert data!
//    void add() {
//        try {
//            chessBoardDAO.addRoomInformation("test2");
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    @Test
    void find() {
        try {
            System.out.println(chessBoardDAO.findChessBoardByRoom("test", new Gson()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void convertTest() {
        ChessBoard chessBoard = new ChessBoard();

        Map<Position, Piece> pieceOnBoard = chessBoard.getChessBoard();

        Gson gson = new Gson();
        JsonElement json = gson.toJsonTree(chessBoard).getAsJsonObject();
        System.out.println(json);
        gson.fromJson(json, chessBoard.getClass());


    }
}
