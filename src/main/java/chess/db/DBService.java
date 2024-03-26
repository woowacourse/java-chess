package chess.db;

import chess.dto.PieceDto;
import chess.dto.TurnDto;
import java.sql.Connection;
import java.util.List;
import java.util.function.Supplier;

public class DBService {
    private final PiecesDao piecesDao;
    private final TurnsDao turnsDao;

    public DBService(Supplier<Connection> connector) {
        this.piecesDao = new PiecesDao(connector);
        this.turnsDao = new TurnsDao(connector);
    }

    public boolean hasPreviousData() {
        return piecesDao.count() != 0;
    }

    public List<PieceDto> findPreviousPieces() {
        return piecesDao.findAll();
    }

    public TurnDto findCurrentTurn() {
        return turnsDao.find();
    }
}
