package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.PieceProvider;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class RunGame implements Game2 {
    
    public static final String GAME_HAS_ALREADY_STARTED = GAME_ERROR_PREFIX + "게임이 이미 시작되었습니다.";
    
    private final Board board;
    private Color turn;
    
    public RunGame(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }
    
    @Override
    public Game2 start() {
        throw new IllegalStateException(GAME_HAS_ALREADY_STARTED);
    }
    
    @Override
    public Game2 move(final Position from, final Position to) {
        this.board.checkColor(from, to, this.turn);
        this.board.checkRoute(from, to);
        this.board.move(from, to);
        this.turn = Color.reverse(this.turn);
        return this;
    }
    
    @Override
    public Status status() {
        return Status.from(this.board);
    }
    
    @Override
    public Game2 end() {
        return new EndGame();
    }
    
    @Override
    public PieceProvider getBoard() {
        return this.board;
    }
    
    @Override
    public boolean isContinued() {
        return !this.board.isKingDead();
    }
    
    @Override
    public boolean isEnd() {
        return false;
    }
    
    @Override
    public boolean isOver() {
        return this.board.isKingDead();
    }
    
}

