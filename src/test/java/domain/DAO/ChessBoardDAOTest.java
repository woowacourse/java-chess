package domain.DAO;

import chess.DAO.ChessBoardDAO;
import org.junit.jupiter.api.BeforeEach;

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

}
