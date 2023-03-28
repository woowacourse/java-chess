package chess.history;

import chess.domain.game.ActionHandler;
import database.MoveDAO;
import java.util.List;

public final class MoveHistory implements History {
    
    
    private static final MoveDAO MOVE_DAO = new MoveDAO("moves");
    
    private final List<Move> moveHistory;
    
    private MoveHistory(final List<Move> moveHistory) {
        this.moveHistory = moveHistory;
    }
    
    public static MoveHistory create() {
        return new MoveHistory(MOVE_DAO.fetchAllMoves());
    }
    
    @Override
    public void add(final Move move) {
        this.moveHistory.add(move);
        MOVE_DAO.addMove(move);
    }
    
    @Override
    public void reset() {
    
    }
    
    @Override
    public void apply(final ActionHandler action) {
        System.out.println("전 이동 기록을 적용합니다.");
        this.moveHistory.forEach(move -> action.move(move.getFrom(), move.getTo()));
    }
    
}
