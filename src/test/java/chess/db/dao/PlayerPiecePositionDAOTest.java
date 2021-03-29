package chess.db.dao;

import static chess.TestFixture.TEST_TITLE;
import static chess.beforedb.domain.piece.type.PieceType.BISHOP;
import static chess.beforedb.domain.piece.type.PieceType.KNIGHT;
import static chess.beforedb.domain.piece.type.PieceType.PAWN;
import static chess.beforedb.domain.piece.type.PieceType.ROOK;
import static chess.beforedb.domain.player.type.TeamColor.BLACK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;
import static chess.beforedb.domain.position.type.File.A;
import static chess.beforedb.domain.position.type.File.C;
import static chess.beforedb.domain.position.type.File.D;
import static chess.beforedb.domain.position.type.File.H;
import static chess.beforedb.domain.position.type.Rank.EIGHT;
import static chess.beforedb.domain.position.type.Rank.FIVE;
import static chess.beforedb.domain.position.type.Rank.ONE;
import static chess.beforedb.domain.position.type.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.DBCleaner;
import chess.db.domain.board.PiecePositionFromDB;
import chess.db.domain.board.PiecePositionNew;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerPiecePositionDAOTest {
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final PlayerPiecePositionDAO playerPiecePositionDAO = new PlayerPiecePositionDAO();
    private final PieceEntity whitePieceOfGame1 = PieceEntity.of(BISHOP, WHITE);
    private final PieceEntity blackPieceOfGame1 = PieceEntity.of(PAWN, BLACK);
    private final PositionEntity whitePositionOfGame1 = PositionEntity.of(H, ONE);
    private final PositionEntity blackPositionOfGame1 = PositionEntity.of(A, EIGHT);
    private final PieceEntity whitePieceOfGame2 = PieceEntity.of(KNIGHT, WHITE);
    private final PieceEntity blackPieceOfGame2 = PieceEntity.of(ROOK, BLACK);
    private final PositionEntity whitePositionOfGame2 = PositionEntity.of(D, FIVE);
    private final PositionEntity blackPositionOfGame2 = PositionEntity.of(C, TWO);
    private ChessGameEntity chessGame1;
    private ChessGameEntity chessGame2;
    private Long blackPlayerIdOfGame1;
    private Long whitePlayerIdOfGame1;
    private Long whitePlayerIdOfGame2;
    private Long blackPlayerIdOfGame2;

    @BeforeEach
    void setUp() throws SQLException {
        chessGame1 = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));

        playerDAO.save(WHITE, chessGame1.getId());
        whitePlayerIdOfGame1 = playerDAO.findIdByGameIdAndTeamColor(chessGame1.getId(), WHITE);

        playerDAO.save(BLACK, chessGame1.getId());
        blackPlayerIdOfGame1 = playerDAO.findIdByGameIdAndTeamColor(chessGame1.getId(), BLACK);

        PiecePositionNew whitePiecePositionOfGame1
            = new PiecePositionNew(whitePieceOfGame1, whitePositionOfGame1);
        PiecePositionNew blackPiecePositionOfGame1
            = new PiecePositionNew(blackPieceOfGame1, blackPositionOfGame1);

        playerPiecePositionDAO.save(whitePlayerIdOfGame1, whitePiecePositionOfGame1);
        playerPiecePositionDAO.save(blackPlayerIdOfGame1, blackPiecePositionOfGame1);

        chessGame2 = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
        playerDAO.save(WHITE, chessGame2.getId());
        whitePlayerIdOfGame2 = playerDAO.findIdByGameIdAndTeamColor(chessGame2.getId(), WHITE);
        playerDAO.save(BLACK, chessGame2.getId());
        blackPlayerIdOfGame2 = playerDAO.findIdByGameIdAndTeamColor(chessGame2.getId(), BLACK);

        PiecePositionNew whitePiecePositionOfGame2
            = new PiecePositionNew(whitePieceOfGame2, whitePositionOfGame2);
        PiecePositionNew blackPiecePositionOfGame2
            = new PiecePositionNew(blackPieceOfGame2, blackPositionOfGame2);

        playerPiecePositionDAO.save(whitePlayerIdOfGame2, whitePiecePositionOfGame2);
        playerPiecePositionDAO.save(blackPlayerIdOfGame2, blackPiecePositionOfGame2);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("특정 게임의 모든 기물과 해당 위치 조회")
    @Test
    void findAllByGameId() throws SQLException {
        Map<PositionEntity, PieceEntity> foundAllByGame1 = playerPiecePositionDAO
            .findAllByGameId(chessGame1.getId());

        Map<PositionEntity, PieceEntity> expectedOfGame1 = new HashMap<>();
        expectedOfGame1.put(blackPositionOfGame1, blackPieceOfGame1);
        expectedOfGame1.put(whitePositionOfGame1, whitePieceOfGame1);

        assertThat(foundAllByGame1).containsExactlyInAnyOrderEntriesOf(expectedOfGame1);

        Map<PositionEntity, PieceEntity> foundAllByGame2 = playerPiecePositionDAO
            .findAllByGameId(chessGame2.getId());

        Map<PositionEntity, PieceEntity> expectedOfGame2 = new HashMap<>();
        expectedOfGame2.put(blackPositionOfGame2, blackPieceOfGame2);
        expectedOfGame2.put(whitePositionOfGame2, whitePieceOfGame2);

        assertThat(foundAllByGame2).containsExactlyInAnyOrderEntriesOf(expectedOfGame2);
    }

    @DisplayName("특정 플레이어의 모든 기물과 해당 위치 조회")
    @Test
    void findAllByPlayerId() throws SQLException {
        PiecePositionNew blackPiecePositionOfGame2
            = new PiecePositionNew(blackPieceOfGame2, blackPositionOfGame2);
        playerPiecePositionDAO.save(blackPlayerIdOfGame1, blackPiecePositionOfGame2);

        List<PiecePositionFromDB> piecesPositionsFromDB
            = playerPiecePositionDAO.findAllByPlayerId(blackPlayerIdOfGame1);

        Map<PositionEntity, PieceEntity> actualPiecesPositions = new HashMap<>();
        for (PiecePositionFromDB piecePositionFromDB : piecesPositionsFromDB) {
            actualPiecesPositions.put(
                PositionEntity.of(piecePositionFromDB.getFile(), piecePositionFromDB.getRank()),
                PieceEntity.of(piecePositionFromDB.getPieceType(),
                    piecePositionFromDB.getTeamColor())
            );
        }

        Map<PositionEntity, PieceEntity> expectedOfBlackPlayerOfGame1 = new HashMap<>();
        expectedOfBlackPlayerOfGame1.put(blackPositionOfGame1, blackPieceOfGame1);
        expectedOfBlackPlayerOfGame1.put(blackPositionOfGame2, blackPieceOfGame2);

        assertThat(actualPiecesPositions)
            .containsExactlyInAnyOrderEntriesOf(expectedOfBlackPlayerOfGame1);
    }

    @DisplayName("특정 게임, 특정 위치의 기물 id 조회")
    @Test
    void findGamePiecePositionByGameIdAndPositionId() throws SQLException {
        GamePiecePosition gamePiecePosition = playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(
                chessGame1.getId(), blackPositionOfGame1.getId());

        assertThat(gamePiecePosition.getPositionId()).isEqualTo(blackPositionOfGame1.getId());
    }

    @DisplayName("플레이어의 기물 위치 업데이트")
    @Test
    void updatePiecePosition() throws SQLException {
        GamePiecePosition gamePiecePositionBeforeUpdate = playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(
                chessGame1.getId(), blackPositionOfGame1.getId());

        gamePiecePositionBeforeUpdate.setPositionId(whitePositionOfGame2.getId());
        playerPiecePositionDAO.updatePiecePosition(gamePiecePositionBeforeUpdate);

        GamePiecePosition blackPositionOfGame1AfterUpdate = playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(
                chessGame1.getId(), blackPositionOfGame1.getId());

        assertThat(blackPositionOfGame1AfterUpdate).isNull();

        GamePiecePosition whitePositionOfGame2AfterUpdate = playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(
                chessGame1.getId(), whitePositionOfGame2.getId());

        assertThat(whitePositionOfGame2AfterUpdate.getPositionId())
            .isEqualTo(whitePositionOfGame2.getId());
        assertThat(whitePositionOfGame2AfterUpdate.getPlayerPiecePositionId())
            .isEqualTo(gamePiecePositionBeforeUpdate.getPlayerPiecePositionId());
    }

    @DisplayName("특정 게임에서 특정 위치의 기물 삭제")
    @Test
    void removePiecePositionOfGame() throws SQLException {
        GamePiecePosition gamePiecePositionToRemove = playerPiecePositionDAO
            .findGamePiecePositionByGameIdAndPositionId(
                chessGame1.getId(), whitePositionOfGame1.getId());

        playerPiecePositionDAO.removePiecePositionOfGame(gamePiecePositionToRemove);

        Map<PositionEntity, PieceEntity> foundAllByGame1 = playerPiecePositionDAO
            .findAllByGameId(chessGame1.getId());

        assertThat(foundAllByGame1).doesNotContainEntry(whitePositionOfGame1, whitePieceOfGame1);

        Map<PositionEntity, PieceEntity> foundAllByGame2 = playerPiecePositionDAO
            .findAllByGameId(chessGame2.getId());

        Map<PositionEntity, PieceEntity> expectedOfGame2 = new HashMap<>();
        expectedOfGame2.put(blackPositionOfGame2, blackPieceOfGame2);
        expectedOfGame2.put(whitePositionOfGame2, whitePieceOfGame2);

        assertThat(foundAllByGame2).containsExactlyInAnyOrderEntriesOf(expectedOfGame2);
    }

    @DisplayName("특정 플레이어의 모든 기물 삭제")
    @Test
    void removeAllByPlayer() throws SQLException {
        PiecePositionNew blackPiecePositionOfGame2
            = new PiecePositionNew(blackPieceOfGame2, blackPositionOfGame2);
        playerPiecePositionDAO.save(whitePlayerIdOfGame2, blackPiecePositionOfGame2);

        playerPiecePositionDAO.removeAllByPlayer(whitePlayerIdOfGame2);

        assertThat(playerPiecePositionDAO.findAllByPlayerId(whitePlayerIdOfGame1)).hasSize(1);
        assertThat(playerPiecePositionDAO.findAllByPlayerId(blackPlayerIdOfGame1)).hasSize(1);
        assertThat(playerPiecePositionDAO.findAllByPlayerId(whitePlayerIdOfGame2)).isEmpty();
        assertThat(playerPiecePositionDAO.findAllByPlayerId(blackPlayerIdOfGame2)).hasSize(1);
    }

    @DisplayName("모든 게임의 모든 기물 위치 삭제")
    @Test
    void removeAll() throws SQLException {
        PiecePositionNew blackPiecePositionOfGame2
            = new PiecePositionNew(blackPieceOfGame2, blackPositionOfGame2);
        playerPiecePositionDAO.save(whitePlayerIdOfGame2, blackPiecePositionOfGame2);

        playerPiecePositionDAO.removeAll();

        assertThat(playerPiecePositionDAO.findAllByPlayerId(whitePlayerIdOfGame1)).isEmpty();
        assertThat(playerPiecePositionDAO.findAllByPlayerId(blackPlayerIdOfGame1)).isEmpty();
        assertThat(playerPiecePositionDAO.findAllByPlayerId(whitePlayerIdOfGame2)).isEmpty();
        assertThat(playerPiecePositionDAO.findAllByPlayerId(blackPlayerIdOfGame2)).isEmpty();
    }
}