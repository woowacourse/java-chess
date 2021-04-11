package chess.domain.chess;

import java.util.List;

import chess.domain.piece.PieceDTO;

public class ChessDTO {
    private final String status;
    private final String turn;
    private final List<PieceDTO> boardDTO;

    public ChessDTO(String status, String turn, List<PieceDTO> boardDTO) {
        this.status = status;
        this.turn = turn;
        this.boardDTO = boardDTO;
    }

    public String getTurn() {
        return turn;
    }
}
