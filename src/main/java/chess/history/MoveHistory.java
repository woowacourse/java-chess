package chess.history;

import chess.domain.game.ActionHandler;
import database.MoveDAO;
import java.util.List;

public final class MoveHistory implements History {
    
    
    private static final MoveDAO MOVE_DAO = new MoveDAO("move");
    private final List<Move> moveHistory;
    private final int gameID;
    
    private MoveHistory(final List<Move> moveHistory, final int gameID) {
        this.moveHistory = moveHistory;
        this.gameID = gameID;
    }
    
    public static MoveHistory create(final int gameID) {
        List<Move> moves = MOVE_DAO.fetchMoves(gameID);
        return new MoveHistory(moves, gameID);
    }
    
    @Override
    public void add(final Move move) {
        this.moveHistory.add(move);
        MOVE_DAO.addMove(move, this.gameID);
    }
    
    @Override
    public void clear(final int moveID) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void apply(final ActionHandler action) {
        System.out.println("전 이동 기록을 적용합니다.");
        this.moveHistory.forEach(move -> action.move(move.getFrom(), move.getTo()));
    }
}
