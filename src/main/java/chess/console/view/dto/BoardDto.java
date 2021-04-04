package chess.console.view.dto;

import chess.domain.board.Board;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BoardDto {

    private final int row;
    private final int column;
    private List<PieceDto> pieceDtos;

    public BoardDto(final Board board) {
        this.row = Board.getRow();
        this.column = Board.getColumn();
        this.pieceDtos = board.getAllPieces().stream()
                .map(PieceDto::new)
                .collect(toList());
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

}
