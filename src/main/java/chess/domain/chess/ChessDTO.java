package chess.domain.chess;

import chess.domain.board.BoardDTO;

public class ChessDTO {
    private final String turn;
    private final BoardDTO boardDTO;

    public ChessDTO(String turn, BoardDTO boardDTO) {
        this.turn = Color.valueOf(turn).color();
        this.boardDTO = boardDTO;
    }
}
