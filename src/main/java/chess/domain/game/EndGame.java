package chess.domain.game;

import chess.domain.board.PieceProvider;
import chess.domain.position.Position;

public class EndGame implements Game {
    
    public static final String GAME_HAS_ALREADY_STARTED = GAME_ERROR_PREFIX + "게임이 이미 시작되었습니다.";
    public static final String METHOD_NOT_SUPPORTED = GAME_ERROR_PREFIX + "지원하지 않는 기능입니다.";
    
    
    public EndGame() {
    }
    
    @Override
    public Game start() {
        throw new IllegalStateException(GAME_HAS_ALREADY_STARTED);
    }
    
    @Override
    public Game move(final Position from, final Position to) {
        throw new IllegalStateException(METHOD_NOT_SUPPORTED);
    }
    
    @Override
    public Status status() {
        throw new IllegalStateException(METHOD_NOT_SUPPORTED);
    }
    
    @Override
    public Game end() {
        throw new IllegalStateException(METHOD_NOT_SUPPORTED);
    }
    
    @Override
    public PieceProvider getBoard() {
        throw new IllegalStateException(METHOD_NOT_SUPPORTED);
    }
    
    @Override
    public boolean isContinued() {
        return false;
    }
    
    @Override
    public boolean isEnd() {
        return true;
    }
    
    @Override
    public boolean isOver() {
        return false;
    }
}

