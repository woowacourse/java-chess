package chess.domain;

import chess.GameStatus;
import chess.domain.piece.Piece;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    
    public static final String GAME_CANNOT_EXECUTE_MESSAGE = "실행할 수 없는 명령입니다.";
    private final GameStatus status;
    private final Board board;
    
    public ChessGame() {
        this.status = GameStatus.START;
        this.board = Board.create();
    }
    
    public void start() {
        if (this.status != GameStatus.START) {
            throw new IllegalStateException(GAME_CANNOT_EXECUTE_MESSAGE);
        }
        this.board.initialize();
    }
    
    public List<List<Piece>> getPieces() {
        List<List<Piece>> pieces = new ArrayList<>();
        for (Rank rank : Rank.values()) {
            pieces.add(board.getPiecesAt(rank));
        }
        return pieces;
    }
    
}
