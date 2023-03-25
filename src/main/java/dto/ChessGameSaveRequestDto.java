package dto;

import domain.game.ChessGame;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.Map;

public class ChessGameSaveRequestDto {
    private final Map<Position, Piece> board;
    private final Side lastTurn;

    private ChessGameSaveRequestDto(Map<Position, Piece> board, Side lastTurn) {
        this.board = board;
        this.lastTurn = lastTurn;
    }

    public static ChessGameSaveRequestDto from(ChessGame chessGame) {
        return new ChessGameSaveRequestDto(chessGame.getBoard(), chessGame.getCurrentTurn());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Side getLastTurn() {
        return lastTurn;
    }
}
