package chess.domain.game.dto;

import java.util.List;

public class LoadedPiecesSelectDto {
    private final List<Integer> files;
    private final List<Integer> ranks;
    private final List<String> sides;
    private final List<String> pieceTypes;

    public LoadedPiecesSelectDto(final List<Integer> files, final List<Integer> ranks, final List<String> sides,
                                 final List<String> pieceTypes) {
        this.files = files;
        this.ranks = ranks;
        this.sides = sides;
        this.pieceTypes = pieceTypes;
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
