package chess.service;

import chess.dao.*;
import chess.domain.ChessRunner;
import chess.dto.MoveResultDTO;
import chess.dto.TeamDTO;
import chess.dto.TileDTO;

import java.util.*;
import java.util.stream.Collectors;

public class ChessService {
    private static final String MESSAGE_STYLE_BLACK = "color:black;";
    private static final String MESSAGE_STYLE_RED = "color:red;";
    private static final String ARROW = " -> ";
    private static final String WINNER = " 가 이겼습니다.";
    private static final String DELIMITER = ": ";
    private static final String NEW_LINE = "\n";

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

    public void continueGame(int chessBoardId) {
        this.chessBoard = chessBoardDAO.findById(chessBoardId);

        this.currentTeam = currentTeamDAO.findCurrentTeam(this.chessBoard);

        updateOriginalPieces(pieceOnBoardDAO);
        Map<String, String> pieceOnBoards = this.originalPieces.getPieceOnBoards().stream()
                .collect(Collectors.toMap(entry -> entry.getPosition(),
                        entry -> entry.getPieceType() + entry.getTeam(),
                        (e1, e2) -> e1, HashMap::new));
        this.chessRunner = new ChessRunner(pieceOnBoards, this.currentTeam.getCurrentTeam());
    }

    public List<TileDTO> getTiles() {
        return this.chessRunner.entireTileDtos();
    }

    public TeamDTO getCurrentTeam() {
        return new TeamDTO(this.chessRunner.getCurrentTeam());
    }

    public Player getPlayer() {
        return this.playerDAO.findPlayer(this.chessBoard);
    }

    public MoveResultDTO move(String source, String target) {
        try {
            this.chessRunner.update(source, target);
            updateChessBoard(source, target);
            String moveResult = moveResult(this.chessRunner, source, target);
            return new MoveResultDTO(moveResult, MESSAGE_STYLE_BLACK);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new MoveResultDTO(e.getMessage(), MESSAGE_STYLE_RED);
        }
    }

    private void updateChessBoard(String source, String target) {
        PieceOnBoard deletedPiece = null;

        Optional<PieceOnBoard> targetPiece = this.originalPieces.find(target);
        if (targetPiece.isPresent()) {
            deletedPiece = targetPiece.get();
            this.pieceOnBoardDAO.deletePiece(deletedPiece);
        }
        Optional<PieceOnBoard> sourcePiece = this.originalPieces.find(source);
        this.pieceOnBoardDAO.updatePiece(sourcePiece.get(), target);
        this.currentTeam = new CurrentTeam(this.chessRunner.getCurrentTeam());
        this.currentTeamDAO.updateCurrentTeam(this.chessBoard, this.currentTeam);
        updateOriginalPieces(this.pieceOnBoardDAO);
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

    public void deleteChessGame() {
        this.currentTeamDAO.deleteCurrentTeam(this.chessBoard);
        for (PieceOnBoard pieceOnBoard : this.originalPieces.getPieceOnBoards()) {
            this.pieceOnBoardDAO.deletePiece(pieceOnBoard);
        }
        this.playerDAO.deletePlayer(this.chessBoard);
        this.chessBoardDAO.deleteChessBoard(this.chessBoard);
    }

    public String getScores() {
        return this.chessRunner.calculateScores().stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));
    }
}
