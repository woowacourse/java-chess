package chess.dto;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class RankDTO {
    private final List<String> pieces;
    private final int rankLine;

    public RankDTO(List<String> pieces, int rankLine) {
        this.pieces = pieces;
        this.rankLine = rankLine;
    }

    public static RankDTO toDTO(Rank rank, int rankLine) {
        return new RankDTO(toPieceSignature(rank), rankLine);
    }

    private static List<String> toPieceSignature(Rank rank) {
        return rank.getPieces()
                .stream()
                .map(Piece::getSignature)
                .collect(Collectors.toList());
    }

    public List<String> getPieces() {
        return pieces;
    }

    public int getRankLine() {
        return rankLine;
    }
}
