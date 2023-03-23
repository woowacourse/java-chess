package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.ChessBoard;
import chess.domain.board.PieceProvider;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceScore;
import chess.domain.piece.PieceType;
import chess.domain.piece.Score;
import chess.domain.position.File;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessGame implements Action {
    
    public static final String GAME_HAS_NOT_STARTED = "[GAME ERROR] 게임이 시작되지 않았습니다.";
    public static final String GAME_HAS_ALREADY_STARTED = "[GAME ERROR] 게임이 이미 시작되었습니다.";
    
    private final Board board;
    private Color turn;
    private GameProgress progress;
    
    public ChessGame() {
        this.progress = GameProgress.READY;
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
            throw new IllegalStateException(GAME_HAS_ALREADY_STARTED);
        }
        this.progress = GameProgress.RUN;
    }
    
    @Override
    public void move(final Position from, final Position to) {
        if (this.isNotRunning()) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
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
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        Map<Color, Score> scoreMap = new HashMap<>();
        scoreMap.put(Color.WHITE, Score.from(0));
        scoreMap.put(Color.BLACK, Score.from(0));
        for (File file : File.values()) {
            List<Piece> filePieces = this.board.getFilePieces(file);
            List<Piece> whitePawnStack = new ArrayList<>();
            List<Piece> blackPawnStack = new ArrayList<>();
            for (Piece piece : filePieces) {
                PieceType type = piece.getType();
                if (type == PieceType.EMPTY) {
                    continue;
                }
                if (type == PieceType.PAWN && piece.getColor() == Color.WHITE) {
                    whitePawnStack.add(piece);
                    continue;
                }
                if (type == PieceType.PAWN && piece.getColor() == Color.BLACK) {
                    blackPawnStack.add(piece);
                    continue;
                }
                Score newScore = PieceScore.getScore(type);
                scoreMap.put(piece.getColor(), newScore.add(scoreMap.get(piece.getColor())));
            }
            if (whitePawnStack.size() > 1) {
                Score pawnScore = PieceScore.getScore(PieceType.PAWN);
                pawnScore = pawnScore.multiply(whitePawnStack.size() * 0.5);
                scoreMap.put(Color.WHITE, pawnScore.add(scoreMap.get(Color.WHITE)));
            }
            if (blackPawnStack.size() > 1) {
                Score pawnScore = PieceScore.getScore(PieceType.PAWN);
                pawnScore = pawnScore.multiply(blackPawnStack.size() * 0.5);
                scoreMap.put(Color.BLACK, pawnScore.add(scoreMap.get(Color.BLACK)));
            }
            if (whitePawnStack.size() == 1) {
                Score pawnScore = PieceScore.getScore(PieceType.PAWN);
                scoreMap.put(Color.WHITE, pawnScore.add(scoreMap.get(Color.WHITE)));
            }
            if (blackPawnStack.size() == 1) {
                Score pawnScore = PieceScore.getScore(PieceType.PAWN);
                scoreMap.put(Color.BLACK, pawnScore.add(scoreMap.get(Color.BLACK)));
            }
        }
        return Status.from(scoreMap);
    }
    
    @Override
    public void end() {
        if (this.isNotRunning()) {
            throw new IllegalStateException(GAME_HAS_NOT_STARTED);
        }
        this.progress = GameProgress.END;
    }
    
    public boolean isNotRunning() {
        return this.progress != GameProgress.RUN;
    }
    
    public boolean isNotReady() {
        return this.progress != GameProgress.READY;
    }
    
    public boolean isNotEnd() {
        return this.progress != GameProgress.END;
    }
    
    public PieceProvider getBoard() {
        return this.board;
    }
}

