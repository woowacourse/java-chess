package domain.service;

import dao.GameDao;
import dao.PieceDao;
import domain.game.ChessGame;
import domain.game.Piece;
import domain.game.PieceFactory;
import domain.game.TeamColor;
import domain.position.Position;
import dto.PieceDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBService {
    private final GameDao gameDao = GameDao.getInstance();
    private final PieceDao pieceDao = PieceDao.getInstance();

    public ChessGame loadGame(int gameId) {
        TeamColor savedTurn = findSavedTurn(gameId);
        List<PieceDto> savedPieces = findSavedPieces(gameId);
        return ChessGame.of(savedTurn, separatePositionAndPiece(savedPieces));
    }

    private Map<Position, Piece> separatePositionAndPiece(List<PieceDto> savedPieces) {
        return savedPieces.stream()
                .collect(Collectors.toMap(
                        PieceDto::getPosition,
                        dto -> PieceFactory.create(dto.getPieceType())
                ));
    }

    private TeamColor findSavedTurn(int gameId) {
        return gameDao.findTurn(gameId);
    }

    private List<PieceDto> findSavedPieces(int gameId) {
        return pieceDao.findAllPieces(gameId);
    }

    public int saveGame(ChessGame chessGame) {
        int gameId = addGame();
        saveTurn(gameId, chessGame.currentPlayingTeam());
        saveAllPieces(gameId, collectPositionOfPieces(chessGame));
        return gameId;
    }

    private List<PieceDto> collectPositionOfPieces(ChessGame chessGame) {
        return chessGame.getPositionsOfPieces().entrySet().stream()
                .map(entry -> PieceDto.of(entry.getKey(), entry.getValue()))
                .toList();
    }

    private int addGame() {
        return gameDao.addGame();
    }

    private void saveTurn(int gameId, TeamColor currentTurn) {
        gameDao.updateTurn(gameId, currentTurn);
    }

    private void saveAllPieces(int gameId, List<PieceDto> pieces) {
        pieceDao.addAll(pieces, gameId);
    }
}
