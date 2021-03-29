package chess.db.domain.board;

import chess.beforedb.domain.board.setting.BoardDefaultSetting;
import chess.db.dao.ChessGameDAO;
import chess.db.dao.PlayerDAO;
import chess.db.dao.PlayerPiecePositionDAO;
import chess.db.domain.game.ChessGameForDB;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

class BoardForDBTest {
    private final PlayerPiecePositionDAO playerPiecePositionDAO = new PlayerPiecePositionDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private ChessGameForDB chessGameForDB;

    @AfterEach
    void tearDown() throws SQLException {
        playerPiecePositionDAO.removeAll();
        playerDAO.removeAll();
        chessGameDAO.removeAll();
    }

    @BeforeEach
    void setUp() throws SQLException {
        chessGameForDB = new ChessGameForDB();
        chessGameForDB.createNew(new BoardDefaultSetting(), "testTitle");
    }

//    @DisplayName("DB에서 체스 판 정보 가져오기")
//    @Test
//    void BoardDefaultSetting() throws SQLException {
//        List<PlayerPiecePosition> allPiecesPositionsOfAllPlayers
//            = chessGameForDB.getAllPiecesPositionsOfPlayers();
//
//        List<PiecePosition> allPiecePositionEntities = new ArrayList<>();
//        for (PlayerPiecePosition playerPiecePositionEntity : allPiecesPositionsOfAllPlayers) {
//            allPiecePositionEntities.add(playerPiecePositionEntity.getPiecePositionEntities());
//        }
//
//        assertThat(allPiecePositionEntities).containsExactlyInAnyOrder(
//            new PiecePosition(PieceEntity.of(ROOK, BLACK), PositionEntity.of(A, EIGHT)),
//            new PiecePosition(PieceEntity.of(KNIGHT, BLACK), PositionEntity.of(B, EIGHT)),
//            new PiecePosition(PieceEntity.of(BISHOP, BLACK), PositionEntity.of(C, EIGHT)),
//            new PiecePosition(PieceEntity.of(QUEEN, BLACK), PositionEntity.of(D, EIGHT)),
//            new PiecePosition(PieceEntity.of(KING, BLACK), PositionEntity.of(E, EIGHT)),
//            new PiecePosition(PieceEntity.of(BISHOP, BLACK), PositionEntity.of(F, EIGHT)),
//            new PiecePosition(PieceEntity.of(KNIGHT, BLACK), PositionEntity.of(G, EIGHT)),
//            new PiecePosition(PieceEntity.of(ROOK, BLACK), PositionEntity.of(H, EIGHT)),
//
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(A, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(B, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(C, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(D, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(E, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(F, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(G, SEVEN)),
//            new PiecePosition(PieceEntity.of(PAWN, BLACK), PositionEntity.of(H, SEVEN)),
//
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(A, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(B, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(C, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(D, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(E, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(F, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(G, TWO)),
//            new PiecePosition(PieceEntity.of(PAWN, WHITE), PositionEntity.of(H, TWO)),
//
//            new PiecePosition(PieceEntity.of(ROOK, WHITE), PositionEntity.of(A, ONE)),
//            new PiecePosition(PieceEntity.of(KNIGHT, WHITE), PositionEntity.of(B, ONE)),
//            new PiecePosition(PieceEntity.of(BISHOP, WHITE), PositionEntity.of(C, ONE)),
//            new PiecePosition(PieceEntity.of(QUEEN, WHITE), PositionEntity.of(D, ONE)),
//            new PiecePosition(PieceEntity.of(KING, WHITE), PositionEntity.of(E, ONE)),
//            new PiecePosition(PieceEntity.of(BISHOP, WHITE), PositionEntity.of(F, ONE)),
//            new PiecePosition(PieceEntity.of(KNIGHT, WHITE), PositionEntity.of(G, ONE)),
//            new PiecePosition(PieceEntity.of(ROOK, WHITE), PositionEntity.of(H, ONE))
//        );
//    }
}
