package chess.dto;

import chess.domain.board.Rank;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class RankDTO {
    private final List<PieceDTO> pieces; // PieceDTO 로 바꿔서 positionString 가지게 하기
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
                .collect(Collectors.toList());
    }

    public List<PieceDTO> getPieces() {
        return pieces;
    }

    public int getRankLine() {
        return rankLine;
    }
}
