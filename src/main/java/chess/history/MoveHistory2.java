package chess.history;

import chess.domain.game.ActionHandler2;
import database.MoveDAO2;
import java.util.List;

public final class MoveHistory2 implements History2 {
    
    
    private static final MoveDAO2 MOVE_DAO = new MoveDAO2("move");
    private final List<Move> moveHistory;
    private final int gameID;
    
    private MoveHistory2(final List<Move> moveHistory, final int gameID) {
        this.moveHistory = moveHistory;
        this.gameID = gameID;
    }
    
    public static MoveHistory2 create(final int gameID) {
        List<Move> moves = MOVE_DAO.fetchMoves(gameID);
        return new MoveHistory2(moves, gameID);
    }
    
    @Override
    public void fetch(final int gameID) {
        this.moveHistory.addAll(MOVE_DAO.fetchMoves(gameID));
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
    public void apply(final ActionHandler2 action) {
        System.out.println("전 이동 기록을 적용합니다.");
        this.moveHistory.forEach(move -> action.move(move.getFrom(), move.getTo()));
    }
}
