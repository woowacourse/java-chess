package chess.db.domain.player;

import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;

import chess.db.dao.PiecePosition;
import chess.db.dao.PlayerDAO;
import chess.db.domain.board.PiecesPositionsForDB;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePosition;
import chess.beforedb.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayersForDB {
    private final PlayerDAO playerDAO;
    private final PiecesPositionsForDB piecesPositionsForDB;
    private final List<PlayerEntity> playerEntities = new ArrayList<>();

    public PlayersForDB() {
        playerDAO = new PlayerDAO();
        piecesPositionsForDB = new PiecesPositionsForDB();
    }

    public void createNewPlayers(ChessGameEntity chessGameEntity) throws SQLException {
        playerDAO.save(new PlayerEntity(WHITE, chessGameEntity));
        playerDAO.save(new PlayerEntity(BLACK, chessGameEntity));
    }

    public void loadPlayers(ChessGameEntity chessGameEntity) throws SQLException {
        playerEntities.clear();
        playerEntities.addAll(playerDAO.findAllByChessGame(chessGameEntity));
    }

    public void saveInitialPiecePositions(PiecePosition piecePositionEntities)
        throws SQLException {

        addPieceToPlayerTeamColorOf(
            piecePositionEntities.getPieceTeamColor(), piecePositionEntities
        );
    }

    private void addPieceToPlayerTeamColorOf(TeamColor teamColor,
        PiecePosition piecePositionEntities) throws SQLException {

        PlayerEntity playerEntity = findPlayerByTeamColor(teamColor);
        piecesPositionsForDB.save(playerEntity, piecePositionEntities);
    }

    public void updatePiecePosition(PieceEntity pieceEntity, PositionEntity positionEntity) throws SQLException {
        PlayerEntity playerEntity = findPlayerByTeamColor(pieceEntity.getTeamColor());
        piecesPositionsForDB.updatePiecePosition(
            playerEntity, new PiecePosition(pieceEntity, positionEntity)
        );
    }

    private PlayerEntity findPlayerByTeamColor(TeamColor teamColor) {
        return playerEntities.stream()
            .filter(playerEntity -> playerEntity.getTeamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 색깔 입니다."));
    }

    public double getPlayerScoreTeamColorOf(TeamColor teamColor) {
        PlayerEntity playerEntity = findPlayerByTeamColor(teamColor);
        return playerEntity.getScore();
    }

    public void removePiece(PieceEntity deadPiece, PositionEntity positionEntity) throws SQLException {
        PlayerEntity playerEntity = findPlayerByTeamColor(deadPiece.getTeamColor());
        piecesPositionsForDB.removePiece(new PlayerPiecePosition(
            playerEntity, new PiecePosition(deadPiece, positionEntity)));
    }

    public List<PlayerPiecePosition> getAllPiecesPositionsOfChessGame() throws SQLException {

        List<PlayerPiecePosition> allPiecesPositionsEntities = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntities) {
            allPiecesPositionsEntities.addAll(
                piecesPositionsForDB.getAllPiecesPositionsOfPlayer(playerEntity));
        }
        return allPiecesPositionsEntities;
    }

    public PlayerEntity getPlayerColorOf(TeamColor teamColor) {
        return findPlayerByTeamColor(teamColor);
    }

    public void removePlayersOfChessGame(ChessGameEntity chessGameEntity) throws SQLException {
        for (PlayerEntity playerEntity : playerEntities) {
            piecesPositionsForDB.removePiecesPositionsOfPlayer(playerEntity);
        }
        playerDAO.removeAllByChessGame(chessGameEntity);
    }
}
