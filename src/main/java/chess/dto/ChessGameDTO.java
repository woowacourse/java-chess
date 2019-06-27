package chess.dto;

import chess.domain.piece.PieceColor;

import java.util.Map;

public class ChessGameDTO {
    private PieceColor turn;
    private ChessBoardDTO board;
    private Map<PieceColor, Double> status;

    public PieceColor getTurn() {
        return turn;
    }

    public void setTurn(PieceColor turn) {
        this.turn = turn;
    }

    public ChessBoardDTO getBoard() {
        return board;
    }

    public void setBoard(ChessBoardDTO board) {
        this.board = board;
    }

    public Map<PieceColor, Double> getStatus() {
        return status;
    }

    public void setStatus(Map<PieceColor, Double> status) {
        this.status = status;
    }
}
