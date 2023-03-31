package chess.service;

import chess.mysql.ConnectionPoolImpl;
import chess.mysql.JdbcTemplate;
import chess.repository.chess.ChessGameDao;
import chess.repository.chess.ChessGameRepository;
import chess.repository.chess.ChessGameRepositoryImpl;
import chess.repository.chess.MoveDao;
import chess.repository.user.UserDao;
import chess.repository.user.UserRepositoryImpl;
import chess.service.game.EndChessGameService;
import chess.service.game.GamesService;
import chess.service.game.LoadChessGameService;
import chess.service.game.MoveChessGameService;
import chess.service.game.StartChessGameService;
import chess.service.game.StatusChessGameService;
import chess.service.room.CreateRoomService;
import chess.service.user.LoginService;

public class ServiceFactory {

    private static final ServiceFactory instance = new ServiceFactory();

    private final JdbcTemplate jdbcTemplate;
    private final StartChessGameService startChessGameService;
    private final EndChessGameService endChessGameService;
    private final MoveChessGameService moveChessGameService;
    private final LoadChessGameService loadChessGameService;
    private final StatusChessGameService statusChessGameService;
    private final CreateRoomService createRoomService;
    private final LoginService loginService;
    private final GamesService gamesService;

    private ServiceFactory() {
        jdbcTemplate = new JdbcTemplate(ConnectionPoolImpl.getInstance());
        ChessGameRepository chessGameRepository = new ChessGameRepositoryImpl(createChessGameDao(), createMoveDao());
        loadChessGameService = new LoadChessGameService(chessGameRepository);
        startChessGameService = new StartChessGameService(loadChessGameService, chessGameRepository);
        endChessGameService = new EndChessGameService(loadChessGameService, chessGameRepository);
        moveChessGameService = new MoveChessGameService(loadChessGameService, chessGameRepository);
        statusChessGameService = new StatusChessGameService(loadChessGameService);
        gamesService = new GamesService(chessGameRepository);
        createRoomService = new CreateRoomService(chessGameRepository);
        loginService = new LoginService(createUserRepository());
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public StatusChessGameService getStatusChessGameService() {
        return statusChessGameService;
    }

    public CreateRoomService getCreateRoomService() {
        return createRoomService;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public GamesService getGamesService() {
        return gamesService;
    }

    private ChessGameDao createChessGameDao() {
        return new ChessGameDao(jdbcTemplate);
    }

    private UserRepositoryImpl createUserRepository() {
        return new UserRepositoryImpl(new UserDao(jdbcTemplate));
    }

    private MoveDao createMoveDao() {
        return new MoveDao(jdbcTemplate);
    }

    public StartChessGameService getStartChessGameService() {
        return startChessGameService;
    }

    public EndChessGameService getEndChessGameService() {
        return endChessGameService;
    }

    public MoveChessGameService getMoveChessGameService() {
        return moveChessGameService;
    }

    public LoadChessGameService getLoadChessGameService() {
        return loadChessGameService;
    }
}
