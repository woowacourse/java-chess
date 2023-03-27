package chess.service;

import chess.dao.chess.ChessGameDao;
import chess.dao.chess.ChessGameDaoImpl;
import chess.dao.chess.PieceDao;
import chess.dao.chess.PieceDaoImpl;
import chess.dao.user.UserDaoImpl;
import chess.database.JdbcTemplate;

public final class ServiceManager implements Service {
    private final JdbcTemplate jdbcTemplate;

    public ServiceManager(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserService userService() {
        return new UserService(new UserDaoImpl(jdbcTemplate));
    }

    @Override
    public ChessGameService chessGameService() {
        final ChessGameDao chessGameDao = new ChessGameDaoImpl(jdbcTemplate);
        final PieceDao pieceDao = new PieceDaoImpl(jdbcTemplate);
        final ChessBoardService chessBoardService = new ChessBoardService(pieceDao);
        return new ChessGameService(chessGameDao, chessBoardService);
    }
}
