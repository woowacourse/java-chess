package chess.dto;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDto {

    private final List<List<String>> chessBoardDto;

    public BoardDto(Map<Position, Piece> chessBoard) {
        chessBoardDto = createDto(chessBoard);
    }

    private List<List<String>> createDto(Map<Position, Piece> chessBoard) {
        List<List<String>> chessBoardString = new ArrayList<>();
        for (Rank rank : Rank.getReversedOrderedRanks()) {
            List<String> piecesOfRank = new ArrayList<>();
            for (Column column : Column.getOrderedColumns()) {
                String piece = chessBoard.get(new Position(column, rank)).findName();
                piecesOfRank.add(piece);
            }
            chessBoardString.add(piecesOfRank);
        }
        return chessBoardString;
    }

    public List<List<String>> getDto() {
        return new ArrayList<>(chessBoardDto);
    }
}
