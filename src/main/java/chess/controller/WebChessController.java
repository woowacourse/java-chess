package chess.controller;

import chess.dao.*;
import chess.domain.ChessRunner;
import chess.dto.MoveResultDTO;
import chess.dto.TeamDTO;
import chess.dto.TileDTO;

import java.util.*;
import java.util.stream.Collectors;

public class WebChessController {
    private static final String MESSAGE_STYLE_BLACK = "color:black;";
    private static final String MESSAGE_STYLE_RED = "color:red;";
    private static final String DELIMITER = ": ";
    private static final String NEW_LINE = "\n";
    private static final String ARROW = " -> ";
    private static final String WINNER = " 가 이겼습니다.";

    private ChessRunner chessRunner;
    private ChessBoard chessBoard;
    private CurrentTeam currentTeam;
    private PieceOnBoards originalPieces;

    public void newGame(Player player) throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
        PieceDAO pieceDAO = new PieceDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        this.chessRunner = new ChessRunner();

        chessBoardDAO.addChessBoard();
        this.chessBoard = chessBoardDAO.findRecentChessBoard();

        this.currentTeam = new CurrentTeam(this.chessRunner.getCurrentTeam());
        currentTeamDAO.addCurrentTeam(this.chessBoard, this.currentTeam);

        List<TileDTO> tileDtos = this.chessRunner.pieceTileDtos();
        pieceDAO.addPiece(this.chessBoard, tileDtos);
        updateOriginalPieces(pieceDAO);

        playerDAO.addPlayer(this.chessBoard, player);
    }

    public List<Player> players() throws Exception {
        PlayerDAO playerDAO = new PlayerDAO();

        return Collections.unmodifiableList(playerDAO.findAllPlayer());
    }

    public Player getPlayer() throws Exception {
        PlayerDAO playerDAO = new PlayerDAO();

        return playerDAO.findPlayer(this.chessBoard);
    }

    public void continueGame(int chessBoardId) throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
        PieceDAO pieceDAO = new PieceDAO();

        this.chessBoard = chessBoardDAO.findById(chessBoardId);

        this.currentTeam = currentTeamDAO.findCurrentTeam(this.chessBoard);

        updateOriginalPieces(pieceDAO);
        Map<String, String> pieceOnBoards = this.originalPieces.getPieceOnBoards().stream()
                .collect(Collectors.toMap(entry -> entry.getPosition(),
                        entry -> entry.getPieceImageUrl(),
                        (e1, e2) -> e1, HashMap::new));
        this.chessRunner = new ChessRunner(pieceOnBoards, this.currentTeam.getCurrentTeam());
    }

    private void updateOriginalPieces(PieceDAO pieceDAO) throws Exception {
        List<PieceOnBoard> pieces = pieceDAO.findPiece(this.chessBoard);
        this.originalPieces = PieceOnBoards.create(pieces);
    }

    public MoveResultDTO move(final String source, final String target) throws Exception {
        try {
            this.chessRunner.update(source, target);
            updateChessBoard(source, target);
            String moveResult = moveResult(this.chessRunner, source, target);
            return new MoveResultDTO(moveResult, MESSAGE_STYLE_BLACK);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new MoveResultDTO(e.getMessage(), MESSAGE_STYLE_RED);
        }
    }

    private void updateChessBoard(final String source, final String target) throws Exception {
        PieceOnBoard deletedPiece = null;
        PieceDAO pieceDAO = new PieceDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();

        Optional<PieceOnBoard> targetPiece = this.originalPieces.find(target);
        if (targetPiece.isPresent()) {
            deletedPiece = targetPiece.get();
            pieceDAO.deletePiece(deletedPiece);
        }
        Optional<PieceOnBoard> sourcePiece = this.originalPieces.find(source);
        pieceDAO.updatePiece(sourcePiece.get(), target);
        this.currentTeam = new CurrentTeam(this.chessRunner.getCurrentTeam());
        currentTeamDAO.updateCurrentTeam(this.chessBoard, this.currentTeam);
        updateOriginalPieces(pieceDAO);
    }

    private String moveResult(final ChessRunner chessRunner, final String source, final String target) {
        if (!this.isEndGame()) {
            return source + ARROW + target;
        }
        return chessRunner.getWinner() + WINNER;
    }

    public boolean isEndGame() {
        return this.chessRunner.isEndChess();
    }

    public void deleteChessGame() throws Exception {
        ChessBoardDAO chessBoardDAO = new ChessBoardDAO();
        PieceDAO pieceDAO = new PieceDAO();
        CurrentTeamDAO currentTeamDAO = new CurrentTeamDAO();
        PlayerDAO playerDAO = new PlayerDAO();

        currentTeamDAO.deleteCurrentTeam(this.chessBoard);
        for (PieceOnBoard pieceOnBoard : this.originalPieces.getPieceOnBoards()) {
            pieceDAO.deletePiece(pieceOnBoard);
        }
        playerDAO.deletePlayer(this.chessBoard);
        chessBoardDAO.deleteChessBoard(this.chessBoard);
    }

    public TeamDTO getCurrentTeam() {
        return new TeamDTO(this.chessRunner.getCurrentTeam());
    }

    public List<TileDTO> getTiles() {
        return this.chessRunner.entireTileDtos();
    }

    public String getScores() {
        return this.chessRunner.calculateScores().stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));
    }
}
