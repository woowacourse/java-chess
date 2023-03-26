package dto.dao;

import domain.game.GameState;
import domain.piece.Piece;
import domain.piece.Position;
import domain.piece.Side;

import java.util.Map;

public class ChessGameDaoResponseDto {
    private final Map<Position, Piece> board;
    private final Side lastTurn;
    private final GameState state;

    public ChessGameDaoResponseDto(Map<Position, Piece> board, Side lastTurn, GameState state) {
        this.board = board;
        this.lastTurn = lastTurn;
        this.state = state;
    }

//    public static ChessGameCreateResponseDto from() {
//        return new ChessGameCreateResponseDto(chessGame.getBoard(), chessGame.getCurrentTurn(), gameId);
//    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Side getLastTurn() {
        return lastTurn;
    }

    public GameState getState() {
        return state;
    }
}
