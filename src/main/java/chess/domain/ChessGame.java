package chess.domain;

import chess.GameStatus;
import chess.action.Action;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class ChessGame implements Action {
    
    public static final String GAME_HAS_NOT_STARTED = "게임이 시작되지 않았습니다.";
    public static final String GAME_ALREADY_HAS_STARTED = "게임이 이미 시작되었습니다.";
    private static final String COMMAND_ERROR_PREFIX = "[GAME ERROR] ";
    private final Board board;
    private Color turn;
    private GameStatus status;
    
    public ChessGame() {
        this.status = GameStatus.READY;
        this.board = Board.create();
        this.turn = Color.WHITE;
    }
    
    @Override
    public void start() {
        if (this.isNotReady()) {
            throw new IllegalArgumentException(COMMAND_ERROR_PREFIX + GAME_ALREADY_HAS_STARTED);
        }
        this.board.initialize();
        this.status = GameStatus.RUN;
    }
    
    @Override
    public void move(final Position from, final Position to) {
        if (this.isNotRunning()) {
            throw new IllegalArgumentException(COMMAND_ERROR_PREFIX + GAME_HAS_NOT_STARTED);
        }
        this.checkPieceMove(from, to);
        this.board.replace(from, to);
        this.turn = Color.reverse(this.turn);
    }
    
    @Override
    public void end() {
        if (this.isNotRunning()) {
            throw new IllegalArgumentException(COMMAND_ERROR_PREFIX + GAME_HAS_NOT_STARTED);
        }
        this.status = GameStatus.END;
    }
    
    public boolean isNotRunning() {
        return this.status != GameStatus.RUN;
    }
    
    private void checkPieceMove(final Position source, final Position destination) {
        Piece sourcePiece = this.board.getValidSourcePiece(source, this.turn);
        sourcePiece.canMove(source, destination);
        this.board.checkSameColor(destination, this.turn);
        this.checkRoute(source, destination, sourcePiece);
        this.checkPawnMove(source, destination, sourcePiece);
    }
    
    private void checkRoute(final Position source, final Position destination, final Piece sourcePiece) {
        if (!(sourcePiece.getType() == PieceType.KNIGHT)) {
            this.board.checkBetweenRoute(source, destination);
        }
    }
    
    private void checkPawnMove(final Position source, final Position destination, final Piece sourcePiece) {
        if (sourcePiece.getType() == PieceType.PAWN) {
            this.board.checkRestrictionForPawn(source, destination, this.turn);
        }
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

