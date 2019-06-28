package chess.dto;

import chess.domain.piece.PieceColor;

import java.util.Map;

public class ChessGameDto {
    private PieceColor turn;
    private ChessBoardDto board;
    private Map<PieceColor, Double> status;

    public PieceColor getTurn() {
        return turn;
    }

    public void setTurn(PieceColor turn) {
        this.turn = turn;
    }

    public ChessBoardDto getBoard() {
        return board;
    }

    public void setBoard(ChessBoardDto board) {
        this.board = board;
    }

    public Map<PieceColor, Double> getStatus() {
        return status;
    }

    public void setStatus(Map<PieceColor, Double> status) {
        this.status = status;
    }
}
