package chess.domain;

import chess.GameStatus;
import chess.action.Action;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame implements Action {
    
    
    public static final String GAME_CANNOT_EXECUTE_MESSAGE = "실행할 수 없는 명령입니다.";
    public static final String GAME_HAS_NOT_STARTED = "게임이 시작되지 않았습니다.";
    private final Board board;
    private Color turn;
    private GameStatus status;
    
    public ChessGame() {
        this.status = GameStatus.START;
        this.board = Board.create();
        this.turn = Color.WHITE;
    }
    
    
    public void move(final List<String> arguments) {
        if (this.status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        Position source = Position.from(arguments.get(0));
        Position destination = Position.from(arguments.get(1));
        
        this.checkPieceMove(source, destination);
        this.board.replace(source, destination);
        this.turn = Color.reverse(this.turn);
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
    
    @Override
    public void start() {
        if (this.status != GameStatus.START) {
            throw new IllegalStateException(GAME_CANNOT_EXECUTE_MESSAGE);
        }
        this.board.initialize();
        this.status = GameStatus.MOVE;
    }
    
    @Override
    public void move(final Position from, final Position to) {
        if (this.status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        this.checkPieceMove(from, to);
        this.board.replace(from, to);
        this.turn = Color.reverse(this.turn);
    }
    
    @Override
    public void end() {
        if (this.status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        this.status = GameStatus.END;
    }
    
    public boolean isNotEnd() {
        return this.status != GameStatus.END;
    }
    
    
    public Board getBoard() {
        return this.board;
    }
}

