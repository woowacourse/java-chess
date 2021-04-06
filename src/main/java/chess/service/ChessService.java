package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
import chess.domain.team.Team;
import chess.webdao.ChessGameDAO;
import chess.webdto.ChessGameDTO;
import chess.webdto.PieceDTOFormat;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static chess.service.TeamFormat.BLACK_TEAM;
import static chess.service.TeamFormat.WHITE_TEAM;

public class ChessService {
    private ChessGame chessGame;
    private Team whiteTeam;
    private Team blackTeam;
    private final ChessGameDAO chessGameDAO;

    public ChessService() {
        this.chessGameDAO = new ChessGameDAO();
    }

    public synchronized void startNewGame() throws SQLException {
        chessGameDAO.deleteChessGame();
        whiteTeam = Team.whiteTeam();
        blackTeam = Team.blackTeam();
        chessGame = new ChessGame(blackTeam, whiteTeam);
        chessGameDAO.createChessGame(chessGame, currentTurnTeamToString());
    }

    public synchronized void loadPreviousGame() throws SQLException {
        chessGame = chessGameDAO.readChessGame();
        whiteTeam = chessGame.getWhiteTeam();
        blackTeam = chessGame.getBlackTeam();
    }

    public synchronized void saveGame() throws SQLException {
        chessGameDAO.deleteChessGame();
        chessGameDAO.createChessGame(chessGame, currentTurnTeamToString());
    }

    public synchronized void move(final String start, final String destination) {
        chessGame.move(Position.of(start), Position.of(destination));
    }

    public synchronized ChessGameDTO generateChessGameDTO() {
        final Map<String, Map<String, String>> piecePositionToString = generatePiecePositionToString();
        final String currentTurnTeam = currentTurnTeamToString();
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final boolean isPlaying = chessGame.isPlaying();
        return new ChessGameDTO(piecePositionToString, currentTurnTeam, whiteTeamScore, blackTeamScore, isPlaying);
    }

    private Map<String, Map<String, String>> generatePiecePositionToString() {
        final Map<String, Map<String, String>> piecePosition = new HashMap<>();
        piecePosition.put(WHITE_TEAM.asDTOFormat(), generatePiecePositionByTeamToString(chessGame.currentWhitePiecePosition()));
        piecePosition.put(BLACK_TEAM.asDTOFormat(), generatePiecePositionByTeamToString(chessGame.currentBlackPiecePosition()));
        return piecePosition;
    }

    private Map<String, String> generatePiecePositionByTeamToString(final Map<Position, Piece> piecePosition) {
        final Map<String, String> piecePositionConverted = new HashMap<>();
        for (Position position : piecePosition.keySet()) {
            final String positionInitial = position.getPositionInitial();
            final Piece chosenPiece = piecePosition.get(position);
            final String pieceString = PieceDTOFormat.convert(chosenPiece);
            piecePositionConverted.put(positionInitial, pieceString);
        }
        return piecePositionConverted;
    }

    public synchronized String currentTurnTeamToString() {
        final Team currentTurnTeam = chessGame.getCurrentTurnTeam();
        if (currentTurnTeam.equals(whiteTeam)) {
            return WHITE_TEAM.asDAOFormat();
        }
        return BLACK_TEAM.asDAOFormat();
    }
}
