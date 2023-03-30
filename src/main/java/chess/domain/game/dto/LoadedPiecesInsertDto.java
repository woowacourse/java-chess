package chess.domain.game.dto;

import chess.domain.piece.type.Piece;
import java.util.List;
import java.util.stream.Collectors;

public class LoadedPiecesInsertDto {
    private final List<Integer> files;
    private final List<Integer> ranks;
    private final List<String> sides;
    private final List<String> pieceTypes;

    public LoadedPiecesInsertDto(List<Piece> pieces) {
        this.files = extractFiles(pieces);
        this.ranks = extractRanks(pieces);
        this.sides = extractSides(pieces);
        this.pieceTypes = extractPieceTypes(pieces);
    }

    private List<Integer> extractFiles(final List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getFile)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Integer> extractRanks(final List<Piece> pieces) {
        return pieces.stream()
                .map(Piece::getRank)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<String> extractSides(final List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> piece.getSide().name())
                .collect(Collectors.toUnmodifiableList());
    }

    private List<String> extractPieceTypes(final List<Piece> pieces) {
        return pieces.stream()
                .map(piece -> piece.getClass().getSimpleName())
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Integer> getFiles() {
        return files;
    }

    public List<Integer> getRanks() {
        return ranks;
    }

    public List<String> getSides() {
        return sides;
    }

    public List<String> getPieceTypes() {
        return pieceTypes;
    }
}
