package chess.web.dto;

import chess.repository.entity.ChessEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SavedGameBundleResponse {
    private final List<SavedGameResponse> savedGameResponses;

    public SavedGameBundleResponse(List<ChessEntity> chessEntities) {
        this.savedGameResponses = chessEntities.stream()
                .filter(ChessEntity::isActive)
                .map(SavedGameResponse::new)
                .collect(Collectors.toList());
    }

    public List<SavedGameResponse> getSavedGameResponses() {
        return savedGameResponses;
    }
}
