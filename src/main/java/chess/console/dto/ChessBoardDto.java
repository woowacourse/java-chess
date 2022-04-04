package chess.console.dto;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public class ChessBoardDto {
    private final Map<PositionDto, String> chessBoard;

    private ChessBoardDto(Map<PositionDto, String> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoardDto of(Map<Position, Piece> chessBoard) {
        Map<PositionDto, String> chessBoardDto = new HashMap<>();
        for (Position position : chessBoard.keySet()) {
            Piece piece = chessBoard.get(position);
            chessBoardDto.put(PositionDto.of(position), piece.getName());
        }

        return new ChessBoardDto(chessBoardDto);
    }

    public Map<PositionDto, String> getChessBoard() {
        return chessBoard;
    }
}
