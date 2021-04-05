package chess.domain.chess;

import java.util.List;

import chess.domain.board.Board;
import chess.domain.board.BoardDTO;
import chess.domain.piece.PieceDTO;

public class ChessDTO {
    private final String turn;
    private final List<PieceDTO> boardDTO;

    public ChessDTO(String turn, List<PieceDTO> boardDTO) {
        this.turn = Color.valueOf(turn)
                         .color();
        this.boardDTO = boardDTO;
    }

    public String getTurn() {
        return turn;
    }

    public Board makeBoard() {
        return Board.from(new BoardDTO(boardDTO));
    }
}
