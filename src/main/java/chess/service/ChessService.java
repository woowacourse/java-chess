package chess.service;

import chess.dao.*;
import chess.domain.ChessRunner;
import chess.dto.TeamDTO;
import chess.dto.TileDTO;

import java.util.Collections;
import java.util.List;

public class ChessService {
    private final ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
    private final CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
    private final PieceOnBoardDAO pieceOnBoardDAO = new PieceOnBoardDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();

    private ChessRunner chessRunner;
    private ChessBoard chessBoard;
    private CurrentTeam currentTeam;
    private PieceOnBoards originalPieces;

    public List<Player> players() {
        return Collections.unmodifiableList(this.playerDAO.findAllPlayer());
    }

    public void newGame(Player player) {
        this.chessRunner = new ChessRunner();

        chessBoardDAO.addChessBoard();
        this.chessBoard = chessBoardDAO.findRecentChessBoard();

        this.currentTeam = new CurrentTeam(this.chessRunner.getCurrentTeam());
        currentTeamDAO.addCurrentTeam(this.chessBoard, this.currentTeam);

        List<PieceOnBoard> pieces = this.chessRunner.getPieceOnBoards(this.chessBoard.getChessBoardId());
        pieceOnBoardDAO.addPiece(this.chessBoard, pieces);
        updateOriginalPieces(pieceOnBoardDAO);

        playerDAO.addPlayer(this.chessBoard, player);
    }

    private void updateOriginalPieces(PieceOnBoardDAO pieceOnBoardDAO) {
        List<PieceOnBoard> pieces = pieceOnBoardDAO.findPiece(this.chessBoard);
        this.originalPieces = PieceOnBoards.create(pieces);
    }

    public List<TileDTO> getTiles() {
        return this.chessRunner.entireTileDtos();
    }

    public TeamDTO getCurrentTeam() {
        return new TeamDTO(this.chessRunner.getCurrentTeam());
    }
}
