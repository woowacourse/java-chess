package chess.domain.chess;

import chess.domain.board.BoardDTO;

public class ChessDTO {
    private final String status;
    private final String turn;
    private final BoardDTO boardDTO;

    public ChessDTO(Chess chess) {
        this(chess.status(), chess.color(), BoardDTO.from(chess));
    }

    public ChessDTO(String status, String turn, BoardDTO boardDTO) {
        this.status = status;
        this.turn = turn;
        this.boardDTO = boardDTO;
    }

    public String getStatus() {
        return status;
    }

    public String getTurn() {
        return turn;
    }

    public BoardDTO getBoardDTO() {
        return boardDTO;
    }
}
