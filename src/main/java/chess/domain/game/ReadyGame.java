package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.board.PieceProvider;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class ReadyGame implements Game {
    
    public static final String GAME_HAS_NOT_STARTED = GAME_ERROR_PREFIX + "게임이 시작되지 않았습니다.";
    private final Board board;
    private final Color turn;
    
    public ReadyGame() {
        this.board = ChessBoard.create();
        this.turn = Color.WHITE;
    }
    
    @Override
    public Game start() {
        return new RunGame(this.board, this.turn);
    }
    
    @Override
    public Game move(final Position from, final Position to) {
        throw new IllegalStateException(GAME_HAS_NOT_STARTED);
    }
    
    @Override
    public Status status() {
        throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        
    }
    
    @Override
    public Game end() {
        throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        
    }
    
    @Override
    public PieceProvider getBoard() {
        return this.board;
    }
    
    @Override
    public boolean isContinued() {
        return true;
    }
    
    @Override
    public boolean isEnd() {
        return false;
    }
    
    @Override
    public boolean isOver() {
        return false;
    }
    
}

