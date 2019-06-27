package chess.persistence.dto;

import chess.domain.direction.core.Square;
import chess.domain.piece.core.Piece;

import java.util.Map;
import java.util.Objects;


public class ChessBoardDTO {
    private int gameId;
    private int roundNo;
    private Map<Square, Piece> board;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getRoundNo() {
        return roundNo;
    }

    public void setRoundNo(int roundNo) {
        this.roundNo = roundNo;
    }

    public Map<Square, Piece> getBoard() {
        return board;
    }

    public void setBoard(Map<Square, Piece> board) {
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessBoardDTO that = (ChessBoardDTO) o;
        return gameId == that.gameId &&
                roundNo == that.roundNo &&
                Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, roundNo, board);
    }
}
