package chess.domain.dto;

import chess.domain.ChessBoard;
import chess.domain.piece.Piece;
import chess.domain.piece.info.Color;
import chess.domain.piece.info.Position;

import java.util.Map;

public class ChessStatusDto {
    private final Map<Position, Piece> chessBoard;
    private final Color turn;
    private final double blackScore;
    private final double whiteScore;

    public ChessStatusDto(Map<Position, Piece> chessBoard, Color turn, double blackScore, double whiteScore) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public Map<Position, Piece> getChessBoard() {
        return chessBoard;
    }

    public Color getTurn() {
        return turn;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
