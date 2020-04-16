package chess.service;

import chess.dao.*;
import chess.domain.ChessRunner;
import chess.domain.piece.Team;
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

    public List<Player> players() {
        return Collections.unmodifiableList(this.playerDAO.findAllPlayer());
    }

    public void newGame(Player player) {
        this.chessRunner = new ChessRunner();

        this.chessBoardDAO.addChessBoard();
        ChessBoard chessBoard = this.chessBoardDAO.findRecentChessBoard();

        CurrentTeam currentTeam = new CurrentTeam(Team.valueOf(this.chessRunner.getCurrentTeam()));
        this.currentTeamDAO.addCurrentTeam(chessBoard, currentTeam);

        List<PieceOnBoard> pieces = this.chessRunner.getPieceOnBoards(chessBoard.getChessBoardId());
        this.pieceOnBoardDAO.addPiece(chessBoard, pieces);

        this.playerDAO.addPlayer(chessBoard, player);
    }

    public void continueGame(int chessBoardId) {
        ChessBoard chessBoard = this.chessBoardDAO.findById(chessBoardId);
        CurrentTeam currentTeam = this.currentTeamDAO.findCurrentTeam(chessBoard);

        List<PieceOnBoard> pieces = this.pieceOnBoardDAO.findPiece(chessBoard);
        Map<String, String> pieceOnBoards = pieces.stream()
                .collect(Collectors.toMap(p -> p.getPosition(), p -> p.getPieceType() + p.getTeam(),
                        (e1, e2) -> e1, HashMap::new));
        this.chessRunner = new ChessRunner(pieceOnBoards, currentTeam.getCurrentTeam());
    }

    public MoveResultDTO move(int chessBoardId, String source, String target) {
        try {
            this.chessRunner.update(source, target);
            updateChessBoard(chessBoardId, source, target);
            String moveResult = moveResult(this.chessRunner, source, target);
            return new MoveResultDTO(moveResult, MESSAGE_STYLE_BLACK);
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {
            return new MoveResultDTO(e.getMessage(), MESSAGE_STYLE_RED);
        }
    }

    private void updateChessBoard(int chessBoardId, String source, String target) {
        PieceOnBoard piece = null;
        ChessBoard chessBoard = this.chessBoardDAO.findById(chessBoardId);

        piece = getPieceOnBoard(chessBoard, target, piece);
        this.pieceOnBoardDAO.deletePiece(piece);
        piece = getPieceOnBoard(chessBoard, source, piece);
        this.pieceOnBoardDAO.updatePiece(piece, target);
        CurrentTeam currentTeam = new CurrentTeam(Team.valueOf(this.chessRunner.getCurrentTeam()));
        this.currentTeamDAO.updateCurrentTeam(chessBoard, currentTeam);
    }

    private PieceOnBoard getPieceOnBoard(ChessBoard chessBoard, String position, PieceOnBoard piece) {
        List<PieceOnBoard> pieces = this.pieceOnBoardDAO.findPiece(chessBoard);
        PieceOnBoards originalPieces = PieceOnBoards.create(pieces);
        Optional<PieceOnBoard> pieceOnBoard = originalPieces.find(position);
        if (pieceOnBoard.isPresent()) {
            piece = pieceOnBoard.get();
        }
        return piece;
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

    public void deleteChessGame(int chessBoardId) {
        ChessBoard chessBoard = this.chessBoardDAO.findById(chessBoardId);
        List<PieceOnBoard> pieces = this.pieceOnBoardDAO.findPiece(chessBoard);

        this.currentTeamDAO.deleteCurrentTeam(chessBoard);
        for (PieceOnBoard pieceOnBoard : pieces) {
            this.pieceOnBoardDAO.deletePiece(pieceOnBoard);
        }
        this.playerDAO.deletePlayer(chessBoard);
        this.chessBoardDAO.deleteChessBoard(chessBoard);
    }

    public String getScores() {
        return this.chessRunner.calculateScores().stream()
                .map(dto -> dto.getTeam() + DELIMITER + dto.getBoardScore())
                .collect(Collectors.joining(NEW_LINE));
    }

    public List<TileDTO> getTiles() {
        return this.chessRunner.entireTileDtos();
    }

    public TeamDTO getCurrentTeam() {
        return new TeamDTO(this.chessRunner.getCurrentTeam());
    }

    public Player getPlayer(int chessBoardId) {
        ChessBoard chessBoard = this.chessBoardDAO.findById(chessBoardId);
        return this.playerDAO.findPlayer(chessBoard);
    }

    public ChessBoard getRecentChessBoard() {
        return this.chessBoardDAO.findRecentChessBoard();
    }
}
