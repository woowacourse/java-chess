package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;

public class Chess {
    private final Board board;
    private final Status status;
    private final Color turn;
    
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
    
    public boolean isRunning() {
        return status.isRunning();
    }
    
    public void checkGameIsRunning() {
        if (!isRunning()) {
            throw new IllegalStateException("게임 진행 중이 아닙니다.");
        }
    }
    
    public Chess movePiece(String source, String target) {
        return movePiece(Position.of(source), Position.of(target));
    }
    
    public Chess movePiece(Position sourcePosition, Position targetPosition) {
        checkGameIsRunning();
        final Piece sourcePiece = board.get(sourcePosition);
        final Piece targetPiece = board.get(targetPosition);
        checkColorOfPieceThatWantToMove(sourcePiece);
        checkColorOfPieceIsAtTargetPosition(targetPiece);
        
        board.move(sourcePiece, sourcePosition, targetPosition);
        if (targetPiece instanceof King) {
            return new Chess(board, Status.STOP, turn);
        }
        
        return new Chess(board, status, turn.next());
    }
    
    private void checkColorOfPieceThatWantToMove(Piece sourcePiece) {
        if (!sourcePiece.isSameColorAs(turn)) {
            throw new IllegalArgumentException("움직이려 하는 말은 상대방의 말입니다.");
        }
    }
    
    private void checkColorOfPieceIsAtTargetPosition(Piece targetPiece) {
        if (targetPiece.isSameColorAs(turn)) {
            throw new IllegalArgumentException("이동하려는 위치에 자신의 말이 있습니다.");
        }
    }
    
    public Board getBoard() {
        return board;
    }
    
    public double score(Color color) {
        return board.score(color);
    }
    
    public Color winner() {
        return turn;
    }
}
