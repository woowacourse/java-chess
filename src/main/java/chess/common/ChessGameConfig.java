package chess.common;

import chess.application.ChessGameService;
import chess.domain.game.ChessGameRepository;
import chess.infrastructure.persistence.dao.ChessGameDao;
import chess.infrastructure.persistence.dao.JdbcTemplate;
import chess.infrastructure.persistence.dao.PieceDao;
import chess.infrastructure.persistence.repository.JdbcChessGameRepository;

public class ChessGameConfig {

    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate();
    }

    public PieceDao pieceDao() {
        return new PieceDao(jdbcTemplate());
    }

    public ChessGameDao chessGameDao() {
        return new ChessGameDao(jdbcTemplate());
    }

    public ChessGameRepository chessGameRepository() {
        return new JdbcChessGameRepository(pieceDao(), chessGameDao());
    }

    public ChessGameService chessGameService() {
        return new ChessGameService(chessGameRepository());
    }
}
