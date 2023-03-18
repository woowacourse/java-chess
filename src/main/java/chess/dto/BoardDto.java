package chess.dto;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Rank;
import chess.domain.piece.type.Piece;

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
        for (Rank rank : Rank.getOrderedRanks()) {
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
