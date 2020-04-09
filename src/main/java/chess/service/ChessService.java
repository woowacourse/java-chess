package chess.service;

import chess.dao.ChessBoardDAO;
import chess.dao.CurrentTeamDAO;
import chess.dao.PieceDAO;
import chess.dao.PlayerDAO;

public class ChessService {
    private final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
    private final CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
}
