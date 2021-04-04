package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.statistics.ChessGameStatistics;

import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public class GameStatusDto {
    private final Map<String, PieceDto> chessBoard;
    private final Color currentTurnColor;
    private final ChessGameStatistics chessGameStatistics;

    public GameStatusDto(Map<String, PieceDto> chessBoard, Color currentTurnColor, ChessGameStatistics chessGameStatistics) {
        this.chessBoard = chessBoard;
        this.currentTurnColor = currentTurnColor;
        this.chessGameStatistics = chessGameStatistics;
    }

    public static GameStatusDto from(ChessBoard chessBoard, Color currentTurnColor, ChessGameStatistics chessGameStatistics) {
        return chessBoard.board().entrySet()
                .stream()
                .filter(entrySet -> entrySet.getValue().isNotBlank())
                .collect(collectingAndThen(
                        toMap(entrySet -> entrySet.getKey().getNotation(), entrySet -> PieceDto.from(entrySet.getValue())),
                        map -> new GameStatusDto(map, currentTurnColor, chessGameStatistics)));
    }
}
