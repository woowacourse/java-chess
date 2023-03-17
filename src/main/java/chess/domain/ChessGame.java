package chess.domain;

import chess.GameStatus;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    
    
    public static final String GAME_CANNOT_EXECUTE_MESSAGE = "실행할 수 없는 명령입니다.";
    public static final String GAME_HAS_NOT_STARTED = "게임이 시작되지 않았습니다.";
    private final Board board;
    private GameStatus status;
    
    public ChessGame() {
        this.status = GameStatus.START;
        this.board = Board.create();
    }
    
    public void start() {
        if (this.status != GameStatus.START) {
            throw new IllegalStateException(GAME_CANNOT_EXECUTE_MESSAGE);
        }
        this.board.initialize();
        this.status = GameStatus.MOVE;
    }
    
    public void move(final List<String> arguments, Color color) {
        if (this.status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        Position source = Position.from(arguments.get(0));
        Position destination = Position.from(arguments.get(1));
        
        Piece sourcePiece = this.board.getPiece(source, color);
        sourcePiece.canMove(source, destination);
        this.board.checkSameColor(destination, color);
        if (!(sourcePiece.getType() == PieceType.KNIGHT)) {
            this.board.checkBetweenRoute(source, destination);
        }
        if (sourcePiece.getType() == PieceType.PAWN) {
            this.board.checkRestrictionForPawn(source, destination, color);
        }
        this.board.replace(source, destination);
    }
    
    public void end() {
        if (this.status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
    }
    
    public List<List<Piece>> getPieces() {
        List<List<Piece>> pieces = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            pieces.add(this.board.getPiecesAt(rank));
        }
        return pieces;
    }
}

