package chess.service;

import chess.dao.*;
import chess.domain.ChessRunner;

import java.util.Collections;
import java.util.List;

public class ChessService {
    private final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
    private final CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    private ChessRunner chessRunner;

    public List<Player> players() {
        return Collections.unmodifiableList(this.playerDAO.findAllPlayer());
    }
}
