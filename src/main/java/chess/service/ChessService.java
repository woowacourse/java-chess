package chess.service;

import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.piece.Piece;
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

    public ChessGameDTO startNewGame(final boolean forceStart) throws SQLException {
        if (!forceStart && chessGameDAO.checkChessGameIsPlaying()) {
            throw new IllegalArgumentException("아직 진행 중인 체스게임이 있습니다");
        }
        chessGameDAO.deleteChessGame();
        ChessGame chessGame = chessGameDAO.createChessGame();
        return generateChessGameDTO(chessGame);
    }

    public ChessGameDTO loadPreviousGame() throws SQLException {
        final ChessGame chessGame = chessGameDAO.readChessGame();
        return generateChessGameDTO(chessGame);
    }

    public ChessGameDTO move(final String start, final String destination) throws SQLException {
        final ChessGame chessGame = chessGameDAO.readChessGame();
        chessGame.move(Position.of(start), Position.of(destination));
        chessGameDAO.updateChessGame(chessGame, currentTurnTeamToString(chessGame));
        return generateChessGameDTO(chessGame);
    }

    private ChessGameDTO generateChessGameDTO(final ChessGame chessGame) {
        final Map<String, Map<String, String>> piecePositionToString = generatePiecePositionToString(chessGame);
        final String currentTurnTeam = currentTurnTeamToString(chessGame);
        final Map<String, Double> teamScore = new HashMap<>();
        teamScore.put(WHITE_TEAM.asDTOFormat(), chessGame.calculateWhiteTeamScore());
        teamScore.put(BLACK_TEAM.asDTOFormat(), chessGame.calculateBlackTeamScore());
        final boolean isPlaying = chessGame.isPlaying();
        return new ChessGameDTO(piecePositionToString, currentTurnTeam, teamScore, isPlaying);
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

    private String currentTurnTeamToString(final ChessGame chessGame) {
        if (chessGame.isWhiteTeamTurn()) {
            return WHITE_TEAM.asDAOFormat();
        }
        return BLACK_TEAM.asDAOFormat();
    }
}
