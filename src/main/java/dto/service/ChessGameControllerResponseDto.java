package dto.service;

import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;
import dto.ChessGameServiceResponseDto;

import java.util.Map;

public class ChessGameControllerResponseDto {
    private final Map<Position, Piece> board;
    private final Side lastTurn;

    private ChessGameControllerResponseDto(Map<Position, Piece> board, Side lastTurn) {
        this.board = board;
        this.lastTurn = lastTurn;
    }

    public static ChessGameControllerResponseDto from(ChessGameServiceResponseDto chessGameResponseDto) {
        return new ChessGameControllerResponseDto(chessGameResponseDto.getBoard(), chessGameResponseDto.getLastTurn());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Side getLastTurn() {
        return lastTurn;
    }
}
