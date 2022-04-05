package chess.dto;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RankDTO {
    private final List<PieceDTO> pieces;
    private final int rankLine;

    public RankDTO(List<PieceDTO> pieces, int rankLine) {
        this.pieces = pieces;
        this.rankLine = rankLine;
    }

    public static RankDTO toDTO(Rank rank, int rankLine) {
        return new RankDTO(toPieceSignature(rank), rankLine);
    }

    private static List<PieceDTO> toPieceSignature(Rank rank) {
        return rank.getPieces()
                .stream()
                .map(PieceDTO::toDTO)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public List<PieceDTO> getPieces() {
        return pieces;
    }

    public int getRankLine() {
        return rankLine;
    }
}
