import controller.Controller;
import repository.connector.ProdConnector;
import repository.game.GameDao;
import repository.game.JdbcGameDao;
import repository.room.JdbcRoomDao;
import repository.room.RoomDao;
import service.ChessService;
import service.GameRoomService;

public class Application {
    public static void main(String[] args) {
        RoomDao roomDao = new JdbcRoomDao(new ProdConnector());
        GameRoomService gameRoomService = new GameRoomService(roomDao);

        GameDao gameDao = new JdbcGameDao(new ProdConnector());
        ChessService chessService = new ChessService(gameDao);

        new Controller(gameRoomService, chessService).run();
    }
}
