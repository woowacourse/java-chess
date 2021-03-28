//package chess.db.domain.board;
//
//import static chess.domain.piece.type.PieceType.*;
//import static chess.domain.player.type.TeamColor.BLACK;
//import static chess.domain.player.type.TeamColor.WHITE;
//import static org.assertj.core.api.Assertions.assertThat;
//
//import chess.db.dao.ChessGameDAO;
//import chess.db.dao.PlayerDAO;
//import chess.db.dao.PlayerPiecePositionDAO;
//import chess.db.domain.game.ChessGameForDB;
//import chess.db.domain.piece.PieceEntity;
//import chess.db.domain.player.PlayersForDB;
//import chess.db.entity.PlayerEntity;
//import chess.db.entity.PlayerPiecePositionEntity;
//import chess.domain.board.setting.BoardDefaultSetting;
//import chess.domain.piece.type.PieceType;
//import chess.domain.player.type.TeamColor;
//import chess.domain.position.type.File;
//import chess.utils.PositionConverter;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//class BoardForDBDefaultSettingTest {
//    private final PlayerPiecePositionDAO playerPiecePositionDAO = new PlayerPiecePositionDAO();
//    private final PlayerDAO playerDAO = new PlayerDAO();
//    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
//    private ChessGameForDB chessGameForDB;
//    private List<String> cellsStatus;
//    private List<String> blackPiecesExceptPawns;
//    private List<String> whitePiecesExceptPawns;
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        playerPiecePositionDAO.deleteAll();
//        playerDAO.deleteAll();
//        chessGameDAO.deleteAll();
//    }
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        chessGameForDB = new ChessGameForDB();
//        chessGameForDB.createNew(new BoardDefaultSetting(), "testTitle");
//
//
//        cellsStatus = chessGameForDB.boardCellsStatus();
//        blackPiecesExceptPawns = Arrays.asList("R", "N", "B", "Q", "K", "B", "N", "R");
//        whitePiecesExceptPawns = Arrays.asList("r", "n", "b", "q", "k", "b", "n", "r");
//    }
//
//    @DisplayName("보드 기본 세팅")
//    @Test
//    void BoardDefaultSetting() {
//        for (int i = 0; i < 8; i++) {
//            String fileValue = File.values()[i].value();
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "8"))
//            ).isEqualTo(blackPiecesExceptPawns.get(i));
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "7"))
//            ).isEqualTo("P");
//
//            assertEmptyCellsByFile(fileValue);
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "2"))
//            ).isEqualTo("p");
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "1"))
//            ).isEqualTo(whitePiecesExceptPawns.get(i));
//        }
//    }
//
//    private void assertEmptyCellsByFile(String fileValue) {
//        for (int rankOrder = 3; rankOrder < 7; rankOrder++) {
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + rankOrder))
//            ).isEqualTo(".");
//        }
//    }
//
//
//    @DisplayName("보드 기본 세팅")
//    @Test
//    void BoardDefaultSetting() throws SQLException {
//        List<PlayerPiecePositionEntity> allPiecesPositionsOfAllPlayers
//            = chessGameForDB.getAllPiecesPositionsOfAllPlayers();
//
//        PlayerEntity whitePlayerEntity = chessGameForDB.getPlayerColorOf(WHITE);
//        PlayerEntity blackPlayerEntity = chessGameForDB.getPlayerColorOf(BLACK);
//
//        playerPiecePositionDAO.findByPlayerAndPieceAndPosition(whitePlayerEntity, PieceEntity.of(ROOK, WHITE), );
//
//        for (int i = 0; i < 8; i++) {
//            String fileValue = File.values()[i].value();
//
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "8"))
//            ).isEqualTo(blackPiecesExceptPawns.get(i));
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "7"))
//            ).isEqualTo("P");
//
//            assertEmptyCellsByFile(fileValue);
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "2"))
//            ).isEqualTo("p");
//
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + "1"))
//            ).isEqualTo(whitePiecesExceptPawns.get(i));
//        }
//    }
//
//    private void assertEmptyCellsByFile(String fileValue) {
//        for (int rankOrder = 3; rankOrder < 7; rankOrder++) {
//            assertThat(
//                cellsStatus.get(PositionConverter.convertToCellsStatusIndex(fileValue + rankOrder))
//            ).isEqualTo(".");
//        }
//    }
//}
