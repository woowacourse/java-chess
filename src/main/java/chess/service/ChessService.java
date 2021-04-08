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
    private final ChessGameDAO chessGameDAO;

    public ChessService() {
        this.chessGameDAO = new ChessGameDAO();
    }

    public synchronized ChessGameDTO startNewGame() throws SQLException {
        chessGameDAO.deleteChessGame();
        final ChessGame chessGame = new ChessGame(Team.blackTeam(), Team.whiteTeam());
        chessGameDAO.createChessGame(chessGame, currentTurnTeamToString(chessGame));
        return generateChessGameDTO(chessGame);
    }

    public synchronized ChessGameDTO loadPreviousGame() throws SQLException {
        final ChessGame chessGame = chessGameDAO.readChessGame();
        return generateChessGameDTO(chessGame);
    }

    public synchronized ChessGameDTO move(final String start, final String destination) throws SQLException {
        final ChessGame chessGame = chessGameDAO.readChessGame();
        chessGame.move(Position.of(start), Position.of(destination));
        chessGameDAO.updateChessGame(chessGame, currentTurnTeamToString(chessGame));
        return generateChessGameDTO(chessGame);
    }

    private synchronized ChessGameDTO generateChessGameDTO(final ChessGame chessGame) {
        final Map<String, Map<String, String>> piecePositionToString = generatePiecePositionToString(chessGame);
        final String currentTurnTeam = currentTurnTeamToString(chessGame);
        final double whiteTeamScore = chessGame.calculateWhiteTeamScore();
        final double blackTeamScore = chessGame.calculateBlackTeamScore();
        final boolean isPlaying = chessGame.isPlaying();
        return new ChessGameDTO(piecePositionToString, currentTurnTeam, whiteTeamScore, blackTeamScore, isPlaying);
    }

    private Map<String, Map<String, String>> generatePiecePositionToString(final ChessGame chessGame) {
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

    private synchronized String currentTurnTeamToString(final ChessGame chessGame) {
        if (chessGame.isWhiteTeamTurn()) {
            return WHITE_TEAM.asDAOFormat();
        }
        return BLACK_TEAM.asDAOFormat();
    }
}
