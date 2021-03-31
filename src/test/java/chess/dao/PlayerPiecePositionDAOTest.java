package chess.dao;

import static chess.domain.piece.type.PieceType.BISHOP;
import static chess.domain.piece.type.PieceType.KNIGHT;
import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.ROOK;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.domain.position.type.File.A;
import static chess.domain.position.type.File.C;
import static chess.domain.position.type.File.D;
import static chess.domain.position.type.File.H;
import static chess.domain.position.type.Rank.EIGHT;
import static chess.domain.position.type.Rank.FIVE;
import static chess.domain.position.type.Rank.ONE;
import static chess.domain.position.type.Rank.TWO;
import static chess.utils.TestFixture.TEST_TITLE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.game.ChessGameDAO;
import chess.dao.game.ChessGameRepository;
import chess.dao.entity.ChessGameEntity;
import chess.dao.entity.GamePiecePositionEntity;
import chess.dao.entity.PiecePositionEntity;
import chess.dao.player.PlayerDAO;
import chess.dao.player.PlayerRepository;
import chess.dao.playerpieceposition.PlayerPiecePositionDAO;
import chess.dao.playerpieceposition.PlayerPiecePositionRepository;
import chess.domain.piece.Piece;
import chess.domain.player.type.TeamColor;
import chess.domain.position.PiecePosition;
import chess.domain.position.Position;
import chess.utils.DBCleaner;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerPiecePositionDAOTest {
    private final ChessGameRepository chessGameRepository = new ChessGameDAO();
    private final PlayerRepository playerRepository = new PlayerDAO();
    private final PlayerPiecePositionRepository playerPiecePositionRepository = new PlayerPiecePositionDAO();
    private final Piece whitePieceOfGame1 = Piece.of(BISHOP, WHITE);
    private final Piece blackPieceOfGame1 = Piece.of(PAWN, BLACK);
    private final Position whitePositionOfGame1 = Position.of(H, ONE);
    private final Position blackPositionOfGame1 = Position.of(A, EIGHT);
    private final Piece whitePieceOfGame2 = Piece.of(KNIGHT, WHITE);
    private final Piece blackPieceOfGame2 = Piece.of(ROOK, BLACK);
    private final Position whitePositionOfGame2 = Position.of(D, FIVE);
    private final Position blackPositionOfGame2 = Position.of(C, TWO);
    private ChessGameEntity chessGame1;
    private ChessGameEntity chessGame2;
    private Long blackPlayerIdOfGame1;
    private Long whitePlayerIdOfGame1;
    private Long whitePlayerIdOfGame2;
    private Long blackPlayerIdOfGame2;

    @BeforeEach
    void setUp() throws SQLException {
        chessGame1 = chessGameRepository.save(new ChessGameEntity(TEST_TITLE));

        playerRepository.save(TeamColor.values(), chessGame1.getId());
        whitePlayerIdOfGame1 = playerRepository.findIdByGameIdAndTeamColor(chessGame1.getId(), WHITE);
        blackPlayerIdOfGame1 = playerRepository.findIdByGameIdAndTeamColor(chessGame1.getId(), BLACK);

        PiecePosition whitePiecePositionOfGame1 = new PiecePosition(whitePieceOfGame1, whitePositionOfGame1);
        PiecePosition blackPiecePositionOfGame1 = new PiecePosition(blackPieceOfGame1, blackPositionOfGame1);

        playerPiecePositionRepository.save(whitePlayerIdOfGame1, whitePiecePositionOfGame1);
        playerPiecePositionRepository.save(blackPlayerIdOfGame1, blackPiecePositionOfGame1);

        chessGame2 = chessGameRepository.save(new ChessGameEntity(TEST_TITLE));

        playerRepository.save(TeamColor.values(), chessGame2.getId());
        whitePlayerIdOfGame2 = playerRepository.findIdByGameIdAndTeamColor(chessGame2.getId(), WHITE);
        blackPlayerIdOfGame2 = playerRepository.findIdByGameIdAndTeamColor(chessGame2.getId(), BLACK);

        PiecePosition whitePiecePositionOfGame2 = new PiecePosition(whitePieceOfGame2, whitePositionOfGame2);
        PiecePosition blackPiecePositionOfGame2 = new PiecePosition(blackPieceOfGame2, blackPositionOfGame2);

        playerPiecePositionRepository.save(whitePlayerIdOfGame2, whitePiecePositionOfGame2);
        playerPiecePositionRepository.save(blackPlayerIdOfGame2, blackPiecePositionOfGame2);
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBCleaner.removeAll();
    }

    @DisplayName("특정 게임의 모든 기물과 해당 위치 조회")
    @Test
    void findAllByGameId() throws SQLException {
        Map<Position, Piece> foundAllByGame1 = playerPiecePositionRepository.findAllByGameId(chessGame1.getId());

        Map<Position, Piece> expectedOfGame1 = new HashMap<>();
        expectedOfGame1.put(blackPositionOfGame1, blackPieceOfGame1);
        expectedOfGame1.put(whitePositionOfGame1, whitePieceOfGame1);

        assertThat(foundAllByGame1).containsExactlyInAnyOrderEntriesOf(expectedOfGame1);

        Map<Position, Piece> foundAllByGame2 = playerPiecePositionRepository.findAllByGameId(chessGame2.getId());

        Map<Position, Piece> expectedOfGame2 = new HashMap<>();
        expectedOfGame2.put(blackPositionOfGame2, blackPieceOfGame2);
        expectedOfGame2.put(whitePositionOfGame2, whitePieceOfGame2);

        assertThat(foundAllByGame2).containsExactlyInAnyOrderEntriesOf(expectedOfGame2);
    }

    @DisplayName("특정 플레이어의 모든 기물과 해당 위치 조회")
    @Test
    void findAllByPlayerId() throws SQLException {
        PiecePosition blackPiecePositionOfGame2 = new PiecePosition(blackPieceOfGame2, blackPositionOfGame2);
        playerPiecePositionRepository.save(blackPlayerIdOfGame1, blackPiecePositionOfGame2);

        List<PiecePositionEntity> piecesPositionsFromDB = playerPiecePositionRepository.findAllByPlayerId(blackPlayerIdOfGame1);

        Map<Position, Piece> actualPiecesPositions = new HashMap<>();
        for (PiecePositionEntity piecePositionEntity : piecesPositionsFromDB) {
            actualPiecesPositions.put(
                Position.of(piecePositionEntity.getFile(), piecePositionEntity.getRank()),
                Piece.of(piecePositionEntity.getPieceType(), piecePositionEntity.getTeamColor())
            );
        }

        Map<Position, Piece> expectedOfBlackPlayerOfGame1 = new HashMap<>();
        expectedOfBlackPlayerOfGame1.put(blackPositionOfGame1, blackPieceOfGame1);
        expectedOfBlackPlayerOfGame1.put(blackPositionOfGame2, blackPieceOfGame2);

        assertThat(actualPiecesPositions).containsExactlyInAnyOrderEntriesOf(expectedOfBlackPlayerOfGame1);
    }

    @DisplayName("특정 게임, 특정 위치의 기물 id 조회")
    @Test
    void findGamePiecePositionByGameIdAndPositionId() throws SQLException {
        GamePiecePositionEntity gamePiecePositionEntity = playerPiecePositionRepository
            .findGamePiecePositionByGameIdAndPositionId(chessGame1.getId(), blackPositionOfGame1.getId());

        assertThat(gamePiecePositionEntity.getPositionId()).isEqualTo(blackPositionOfGame1.getId());
    }

    @DisplayName("플레이어의 기물 위치 업데이트")
    @Test
    void updatePiecePosition() throws SQLException {
        GamePiecePositionEntity gamePiecePositionEntityBeforeUpdate = playerPiecePositionRepository
            .findGamePiecePositionByGameIdAndPositionId(chessGame1.getId(), blackPositionOfGame1.getId());

        gamePiecePositionEntityBeforeUpdate.setPositionId(whitePositionOfGame2.getId());
        playerPiecePositionRepository.updatePiecePosition(gamePiecePositionEntityBeforeUpdate);

        GamePiecePositionEntity blackPositionOfGame1AfterUpdate = playerPiecePositionRepository
            .findGamePiecePositionByGameIdAndPositionId(chessGame1.getId(), blackPositionOfGame1.getId());

        assertThat(blackPositionOfGame1AfterUpdate).isNull();

        GamePiecePositionEntity whitePositionOfGame2AfterUpdate = playerPiecePositionRepository
            .findGamePiecePositionByGameIdAndPositionId(chessGame1.getId(), whitePositionOfGame2.getId());

        assertThat(whitePositionOfGame2AfterUpdate.getPositionId())
            .isEqualTo(whitePositionOfGame2.getId());
        assertThat(whitePositionOfGame2AfterUpdate.getPlayerPiecePositionId())
            .isEqualTo(gamePiecePositionEntityBeforeUpdate.getPlayerPiecePositionId());
    }

    @DisplayName("특정 게임에서 특정 위치의 기물 삭제")
    @Test
    void removePiecePositionOfGame() throws SQLException {
        GamePiecePositionEntity gamePiecePositionEntityToRemove = playerPiecePositionRepository
            .findGamePiecePositionByGameIdAndPositionId(chessGame1.getId(), whitePositionOfGame1.getId());

        playerPiecePositionRepository.removePiecePositionOfGame(gamePiecePositionEntityToRemove);

        Map<Position, Piece> foundAllByGame1 = playerPiecePositionRepository.findAllByGameId(chessGame1.getId());

        assertThat(foundAllByGame1).doesNotContainEntry(whitePositionOfGame1, whitePieceOfGame1);

        Map<Position, Piece> foundAllByGame2 = playerPiecePositionRepository.findAllByGameId(chessGame2.getId());

        Map<Position, Piece> expectedOfGame2 = new HashMap<>();
        expectedOfGame2.put(blackPositionOfGame2, blackPieceOfGame2);
        expectedOfGame2.put(whitePositionOfGame2, whitePieceOfGame2);

        assertThat(foundAllByGame2).containsExactlyInAnyOrderEntriesOf(expectedOfGame2);
    }

    @DisplayName("특정 플레이어의 모든 기물 삭제")
    @Test
    void removeAllByPlayer() throws SQLException {
        PiecePosition blackPiecePositionOfGame2 = new PiecePosition(blackPieceOfGame2, blackPositionOfGame2);
        playerPiecePositionRepository.save(whitePlayerIdOfGame2, blackPiecePositionOfGame2);

        playerPiecePositionRepository.removeAllByPlayer(whitePlayerIdOfGame2);

        assertThat(playerPiecePositionRepository.findAllByPlayerId(whitePlayerIdOfGame1)).hasSize(1);
        assertThat(playerPiecePositionRepository.findAllByPlayerId(blackPlayerIdOfGame1)).hasSize(1);
        assertThat(playerPiecePositionRepository.findAllByPlayerId(whitePlayerIdOfGame2)).isEmpty();
        assertThat(playerPiecePositionRepository.findAllByPlayerId(blackPlayerIdOfGame2)).hasSize(1);
    }

    @DisplayName("모든 게임의 모든 기물 위치 삭제")
    @Test
    void removeAll() throws SQLException {
        PiecePosition blackPiecePositionOfGame2 = new PiecePosition(blackPieceOfGame2, blackPositionOfGame2);
        playerPiecePositionRepository.save(whitePlayerIdOfGame2, blackPiecePositionOfGame2);

        playerPiecePositionRepository.removeAll();

        assertThat(playerPiecePositionRepository.findAllByPlayerId(whitePlayerIdOfGame1)).isEmpty();
        assertThat(playerPiecePositionRepository.findAllByPlayerId(blackPlayerIdOfGame1)).isEmpty();
        assertThat(playerPiecePositionRepository.findAllByPlayerId(whitePlayerIdOfGame2)).isEmpty();
        assertThat(playerPiecePositionRepository.findAllByPlayerId(blackPlayerIdOfGame2)).isEmpty();
    }
}