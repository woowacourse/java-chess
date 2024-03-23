package chess.dto;

import chess.model.Position;
import chess.model.board.Board;
import chess.model.piece.Piece;
import java.util.ArrayList;
import java.util.List;

public class RankDto {

    private static final int MAX_INDEX = 7;

    private final List<String> rank;

    public RankDto(List<String> rank) {
        this.rank = rank;
    }

    public static RankDto of(Board board, int row) {
        List<String> rank = new ArrayList<>();
        for (int i = 0; i <= MAX_INDEX; i++) {
            Position position = new Position(row, i);
            Piece piece = board.findPiece(position);
            rank.add(PieceMapper.serialize(piece));
        }
        return new RankDto(rank);
    }

    public List<String> getRank() {
        return rank;
    }
}
