package chess.db.domain.player;

import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;

import chess.db.dao.PiecePositionEntities;
import chess.db.dao.PlayerDAO;
import chess.db.domain.board.PlayerPiecesPositionsForDB;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePositionEntity;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayersForDB {
    private final PlayerDAO playerDAO;
    private final PlayerPiecesPositionsForDB playerPiecesPositionsForDB;
    private final List<PlayerEntity> playerEntities = new ArrayList<>();

    public PlayersForDB() {
        playerDAO = new PlayerDAO();
        playerPiecesPositionsForDB = new PlayerPiecesPositionsForDB();
    }

    public void createNewPlayers(ChessGameEntity chessGameEntity) throws SQLException {
        PlayerEntity whitePlayerEntity = playerDAO.save(new PlayerEntity(WHITE, chessGameEntity));
        PlayerEntity blackPlayerEntity = playerDAO.save(new PlayerEntity(BLACK, chessGameEntity));
        playerEntities.add(whitePlayerEntity);
        playerEntities.add(blackPlayerEntity);
    }

    public void addForNewPlayers(PieceEntity pieceEntity, PositionEntity positionEntity)
        throws SQLException {

        addPieceToPlayerTeamColorOf(
            pieceEntity.getTeamColor(), new PiecePositionEntities(pieceEntity, positionEntity));
    }

    private void addPieceToPlayerTeamColorOf(TeamColor teamColor,
        PiecePositionEntities piecePositionEntities) throws SQLException {

        PlayerEntity playerEntity = findPlayerByTeamColor(teamColor);
        playerPiecesPositionsForDB.save(
            playerEntity, piecePositionEntities);
    }

    public void update(PieceEntity pieceEntity, PositionEntity positionEntity) throws SQLException {
        PlayerEntity playerEntity = findPlayerByTeamColor(pieceEntity.getTeamColor());
        playerPiecesPositionsForDB
            .update(playerEntity, new PiecePositionEntities(pieceEntity, positionEntity));
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

    public void remove(PieceEntity deadPiece, PositionEntity positionEntity) throws SQLException {
        PlayerEntity playerEntity = findPlayerByTeamColor(deadPiece.getTeamColor());
        playerPiecesPositionsForDB.remove(
            new PlayerPiecePositionEntity(playerEntity,
                new PiecePositionEntities(deadPiece, positionEntity)));
    }

    public List<PlayerPiecePositionEntity> getAllPiecesPositionsOfChessGame() throws SQLException {

        List<PlayerPiecePositionEntity> allPiecesPositionsEntities = new ArrayList<>();
        for (PlayerEntity playerEntity : playerEntities) {
            allPiecesPositionsEntities.addAll(
                playerPiecesPositionsForDB.getAllPiecesPositionsOfPlayer(playerEntity));
        }
        return allPiecesPositionsEntities;
    }

    public PlayerEntity getPlayerColorOf(TeamColor teamColor) {
        return findPlayerByTeamColor(teamColor);
    }
}
