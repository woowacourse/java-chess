package chess.controller;

import chess.domain.piece.Piece;
import java.util.List;

public class RankDto {

    private final String stringRank;

    private RankDto(String stringRank) {
        this.stringRank = stringRank;
    }

    public static RankDto create(List<Piece> pieces) {
        StringBuilder builder = new StringBuilder();
        for (Piece piece : pieces) {
            builder.append(PieceMapper.map(piece));
        }
        return new RankDto(builder.toString());
    }

    public String getStringRank() {
        return stringRank;
    }
}
