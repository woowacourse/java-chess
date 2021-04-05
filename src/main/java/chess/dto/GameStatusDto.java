package chess.dto;

import chess.domain.ChessGameManager;
import chess.domain.piece.Color;
import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

public class GameStatusDto {
    private final Map<String, PieceDto> chessBoard;
    private final Color currentTurnColor;
    private final ChessGameStatistics chessGameStatistics;
    private final boolean isEnd;

    public GameStatusDto(Map<String, PieceDto> chessBoard, Color currentTurnColor, ChessGameStatistics chessGameStatistics, boolean isEnd) {
        this.chessBoard = chessBoard;
        this.currentTurnColor = currentTurnColor;
        this.chessGameStatistics = chessGameStatistics;
        this.isEnd = isEnd;
    }

    public static GameStatusDto from(ChessGameManager chessGameManager) {
        return new GameStatusDto(
                ChessBoardDto.from(chessGameManager.getBoard()).board(),
                chessGameManager.getCurrentTurnColor(),
                chessGameManager.getStatistics(),
                chessGameManager.isEnd());
    }
}
