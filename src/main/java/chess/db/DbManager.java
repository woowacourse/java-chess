package chess.db;

import chess.domain.Board;
import chess.domain.Movement;
import chess.domain.piece.abstractPiece.Piece;
import chess.domain.piece.character.Team;

public class DbManager {
    private final BoardDao boardDao;
    private final TurnDao turnDao;

    public DbManager() {
        this.boardDao = new BoardDao();
        this.turnDao = new TurnDao();
    }

    public void initialize(Board board, Team team) {
        boardDao.deleteAll();
        boardDao.addAll(board);
        turnDao.delete();
        turnDao.add(team);
    }

    public void update(Movement movement, Piece piece, Team currentTeam) {
        boardDao.save(movement, piece);
        turnDao.change(currentTeam);
    }

    public Board loadBoard() {
        return boardDao.loadAll();
    }

    public Team loadCurrentTeam() {
        return turnDao.load();
    }
}
