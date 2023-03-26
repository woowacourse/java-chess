package dto;

import domain.game.ChessGame;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.Map;

public class ChessGameServiceResponseDto {
    private final Map<Position, Piece> board;
    private final Side lastTurn;

    private ChessGameServiceResponseDto(Map<Position, Piece> board, Side lastTurn) {
        this.board = board;
        this.lastTurn = lastTurn;
    }

    public static ChessGameServiceResponseDto from(ChessGame chessGame) {
        return new ChessGameServiceResponseDto(chessGame.getBoard(), chessGame.getCurrentTurn());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Side getLastTurn() {
        return lastTurn;
    }
}
