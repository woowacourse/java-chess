package chess.domain;

import chess.GameStatus;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import java.util.List;

public class ChessGame {
    
    
    public static final String GAME_CANNOT_EXECUTE_MESSAGE = "실행할 수 없는 명령입니다.";
    public static final String GAME_HAS_NOT_STARTED = "게임이 시작되지 않았습니다.";

    private final Board board;
    private Color turn;
    private GameStatus status;
    
    public ChessGame() {
        status = GameStatus.START;
        board = Board.create();
        turn = Color.WHITE;
    }
    
    public void start() {
        if (status != GameStatus.START) {
            throw new IllegalStateException(GAME_CANNOT_EXECUTE_MESSAGE);
        }
        board.initialize();
        status = GameStatus.MOVE;
    }
    
    public void move(final List<String> arguments) {
        if (status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        Position source = Position.from(arguments.get(0));
        Position destination = Position.from(arguments.get(1));
    
        checkPieceMove(source, destination);
        board.replace(source, destination);
        turn = Color.reverse(turn);
    }
    
    private void checkPieceMove(final Position source, final Position destination) {
        Piece sourcePiece = board.getValidSourcePiece(source, turn);
        sourcePiece.canMove(source, destination);
        board.checkSameColor(destination, turn);
        checkRoute(source, destination, sourcePiece);
        checkPawnMove(source, destination, sourcePiece);
    }
    
    private void checkPawnMove(final Position source, final Position destination, final Piece sourcePiece) {
        if (sourcePiece.getType() == PieceType.PAWN) {
            board.checkRestrictionForPawn(source, destination, turn);
        }
    }
    
    private void checkRoute(final Position source, final Position destination, final Piece sourcePiece) {
        if (!(sourcePiece.getType() == PieceType.KNIGHT)) {
            board.checkBetweenRoute(source, destination);
        }
    }
    
    public void end() {
        if (status != GameStatus.MOVE) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        status = GameStatus.END;
    }
    
    public boolean isGameEnd() {
        return status == GameStatus.END;
    }
    
    public Board getBoard() {
        return board;
    }
}

