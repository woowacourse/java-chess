package chess.service.dto;

import chess.entity.ChessGame;

import java.util.List;
import java.util.stream.Collectors;

public class SavedGameBundleResponse {
    private final List<SavedGameResponse> savedGameResponses;

    public SavedGameBundleResponse(List<ChessGame> chessEntities) {
        this.savedGameResponses = chessEntities.stream()
                .filter(ChessGame::isActive)
                .map(SavedGameResponse::new)
                .collect(Collectors.toList());
    }

    public List<SavedGameResponse> getSavedGameResponses() {
        return savedGameResponses;
    }
}
