package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceScore;
import chess.domain.piece.PieceType;
import chess.domain.piece.Score;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame implements Action {
    
    public static final String GAME_HAS_NOT_STARTED = "[GAME ERROR] 게임이 시작되지 않았습니다.";
    public static final String GAME_ALREADY_HAS_STARTED = "[GAME ERROR] 게임이 이미 시작되었습니다.";
    
    private final Board board;
    private Color turn;
    private GameStatus status;
    
    public ChessGame() {
        this.status = GameStatus.READY;
        this.board = ChessBoard.create();
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
    public Status status() {
        if (this.isNotRunning()) {
            throw new IllegalArgumentException(GAME_HAS_NOT_STARTED);
        }
        Map<Color, Score> scoreMap = new HashMap<>();
        for (File file : File.values()) {
            List<Piece> filePieces = this.board.getFilePieces(file);
            Score score = Score.from(0);
            boolean isPawnInFile = false;
            for (Piece piece : filePieces) {
                PieceType type = piece.getType();
                if (type == PieceType.EMPTY) {
                    continue;
                }
                if (type == PieceType.PAWN) {
                    isPawnInFile = true;
                }
                Score newScore = PieceScore.getScore(type).add(score);
                if (isPawnInFile) {
                    newScore = newScore.multiply(0.5);
                }
                score = newScore;
                scoreMap.put(piece.getColor(), score);
            }
        }
        return Status.from(scoreMap);
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

