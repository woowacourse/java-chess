package gameinitializer;

import database.dao.ChessGameDao;
import domain.piece.Piece;
import domain.piece.Team;
import domain.position.Position;
import java.util.Map;

public final class DatabaseChessAlignment implements ChessGameInitializer {
    private final ChessGameDao chessGameDao;
    private final String boardName;

    public DatabaseChessAlignment(final ChessGameDao chessGameDao, String boardName) {
        this.chessGameDao = chessGameDao;
        this.boardName = boardName;
    }

    @Override
    public Map<Position, Piece> initPiecePosition() {
        return chessGameDao.findPiecesByBoardName(boardName);
    }

    @Override
    public Team initTeam() {
        return chessGameDao.findStartTeamByBoardName(boardName);
    }
}
