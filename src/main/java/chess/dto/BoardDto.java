package chess.dto;

import chess.domain.board.Board;
import chess.domain.board.position.Column;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {
    private final Board board;

    public BoardDto(Board board) {
        this.board = board;
    }

    public List<PieceDto> getBoardWeb() {
        List<PieceDto> result = new ArrayList<>();
        for (Rank rank : Rank.reverseRanks()) {
            addPiecesDtoRank(result, board.getSquares(), rank);
        }
        return result;
    }

    private void addPiecesDtoRank(List<PieceDto> result, Map<Position, Piece> squares, Rank rank) {
        for (Column column : Column.values()) {
            Piece piece = squares.get(new Position(column, rank));
            result.add(new PieceDto(piece));
        }
    }
}
