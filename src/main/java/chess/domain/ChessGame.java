package chess.domain;

import chess.GameStatus;
import chess.action.Action;
import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class ChessGame implements Action {
    
    public static final String GAME_HAS_NOT_STARTED = "[GAME ERROR] 게임이 시작되지 않았습니다.";
    public static final String GAME_ALREADY_HAS_STARTED = "[GAME ERROR] 게임이 이미 시작되었습니다.";
    
    private final Board board;
    private Color turn;
    private GameStatus status;
    
    public ChessGame() {
        this.status = GameStatus.READY;
        this.board = Board.create();
        this.turn = Color.WHITE;
    }
    
    private void isKingCaught() {
        if (this.board.isKingDead()) {
            this.end();
        }
    }
    
    @Override
    public void start() {
        if (this.isNotReady()) {
            throw new IllegalArgumentException(GAME_ALREADY_HAS_STARTED);
        }
        this.status = GameStatus.RUN;
    }
    
    @Override
    public void move(final Position from, final Position to) {
        if (this.isNotRunning()) {
            throw new IllegalArgumentException(GAME_HAS_NOT_STARTED);
        }
        
        this.board.checkColor(from, to, this.turn);
        this.board.checkRoute(from, to);
        this.board.move(from, to);
        this.isKingCaught();
        this.turn = Color.reverse(this.turn);
    }
    
    @Override
    public void end() {
        if (this.isNotRunning()) {
            throw new IllegalArgumentException(GAME_HAS_NOT_STARTED);
        }
        this.status = GameStatus.END;
    }
    
    public boolean isNotRunning() {
        return this.status != GameStatus.RUN;
    }
    
    public boolean isNotReady() {
        return this.status != GameStatus.READY;
    }
    
    public boolean isNotEnd() {
        return this.status != GameStatus.END;
    }
    
    public Board getBoard() {
        return this.board;
    }
}

