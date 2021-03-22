package chess.domain;

import chess.domain.board.Board;
import chess.domain.board.EmptyBoard;
import chess.domain.board.InitializedBoard;
import chess.domain.piece.Piece;
import chess.domain.position.MovePosition;

public class Chess {
    
    private static final String ERROR_GAME_IS_NOT_RUNNING = "게임 진행 중이 아닙니다.";
    private static final String ERROR_SOURCE_PIECE_IS_BLANK = "움직이려 하는 위치에 기물이 없습니다.";
    private static final String ERROR_SOURCE_PIECE_IS_NOT_MINE = "움직이려 하는 기물은 상대방의 기물입니다.";
    private static final String ERROR_TARGET_PIECE_IS_MINE = "이동하려는 위치에 자신의 말이 있습니다.";
    
    private final Board board;
    private Status status;
    private Color turn;
    
    public Chess(Board board) {
        this(board, Status.RUNNING, Color.WHITE);
    }
    
    public Chess(Board board, Status status, Color turn) {
        this.board = board;
        this.status = status;
        this.turn = turn;
    }
    
    public static Chess createWithInitializedBoard() {
        return new Chess(InitializedBoard.create());
    }
    
    public static Chess createWithEmptyBoard() {
        return new Chess(EmptyBoard.create(), Status.STOP, Color.WHITE);
    }
    
    public boolean isRunning() {
        return status.isRunning();
    }
    
    public void checkGameIsRunning() {
        if (!isRunning()) {
            throw new IllegalStateException(ERROR_GAME_IS_NOT_RUNNING);
        }
    }
    
    public void movePiece(MovePosition movePosition) {
        checkColorOfPiece(movePosition);
        status = board.move(movePosition);
        turn = turn.next();
    }
    
    private void checkColorOfPiece(MovePosition movePosition) {
        final Piece sourcePiece = board.get(movePosition.getSourcePosition());
        final Piece targetPiece = board.get(movePosition.getTargetPosition());
        
        if (sourcePiece.isBlank()) {
            throw new IllegalArgumentException(ERROR_SOURCE_PIECE_IS_BLANK);
        }
        
        if (!sourcePiece.isSameColorAs(turn)) {
            throw new IllegalArgumentException(ERROR_SOURCE_PIECE_IS_NOT_MINE);
        }
        
        if (targetPiece.isSameColorAs(turn)) {
            throw new IllegalArgumentException(ERROR_TARGET_PIECE_IS_MINE);
        }
    }
    
    public Board getBoard() {
        return board;
    }
    
    public double score(Color color) {
        return board.score(color);
    }
    
    public Color winner() {
        return turn.next();
    }
    
    public boolean isTerminated() {
        return status.isTerminated();
    }
    
    public void stop() {
        status = Status.STOP;
    }
    
    public void terminate() {
        status = Status.TERMINATED;
    }
    
    public boolean isKingDead() {
        return status == Status.KING_DEAD;
    }
    
    public boolean isStop() {
        return status == Status.STOP;
    }
}
