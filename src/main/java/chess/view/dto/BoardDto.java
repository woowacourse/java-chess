package chess.view.dto;

import chess.domain.board.Board;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class BoardDto {

    private final int row;
    private final int column;
    private final List<PieceDto> pieceDtos;
    private final boolean isFinished;

    public BoardDto(final Board board) {
        this(false, board);
    }

    public BoardDto(final boolean isFinished, final Board board) {
        this.isFinished = isFinished;
        this.row = board.getRow();
        this.column = board.getColumn();
        this.pieceDtos = board.getPieces().stream()
                .map(PieceDto::new)
                .collect(toList());
    }

    public boolean isFinished() {
        return isFinished;
    }

    public List<PieceDto> getPieceDtos() {
        return pieceDtos;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

}
