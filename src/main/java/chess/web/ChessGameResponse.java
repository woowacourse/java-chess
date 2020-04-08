package chess.web;

import chess.controller.ChessManager;
import chess.controller.dto.Tile;
import chess.domain.piece.Team;

import java.util.List;

public class ChessGameResponse {

    private ChessManager chessManager;

    public ChessGameResponse(ChessManager chessManager) {
        this.chessManager = chessManager;
    }

    public List<Tile> getTiles() {
        return chessManager.getTileDto().getTiles();
    }

    public Team getCurrentTeam() {
        return chessManager.getCurrentTeam();
    }
}
