package chess.dto;

import chess.model.piece.Piece;
import chess.model.piece.Type;
import chess.view.PieceTypeSignature;

import java.util.List;

public record RankDTO(List<String> line) {
    public static RankDTO of(List<Piece> rank) {
        List<String> rankSignatures = rank.stream()
                .map(Type::from)
                .map(PieceTypeSignature::getSignature)
                .toList();
        return new RankDTO(rankSignatures);
    }
}
