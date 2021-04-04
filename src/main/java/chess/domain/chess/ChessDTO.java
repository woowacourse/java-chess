package chess.domain.chess;

import chess.domain.board.BoardDTO;

public class ChessDTO {
    private final String currentTurn;
    private final BoardDTO boardDTO;

    public ChessDTO(String lastTurn, BoardDTO boardDTO) {
        this.currentTurn = Color.valueOf(lastTurn).next().color();
        this.boardDTO = boardDTO;
    }
}
