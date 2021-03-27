//package chess.db.dao;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import chess.db.entity.ChessGameEntity;
//import chess.db.entity.PlayerEntity;
//import java.sql.SQLException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//class PlayerDAOTest {
//    private static final String TEST_TITLE = "testTitle";
//
//    private final PlayerDAO playerDAO = new PlayerDAO();
//    private final ChessGameDAO chessRoomDAO = new ChessGameDAO();
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        playerDAO.deleteAll();
//        chessRoomDAO.deleteAll();
//    }
//
//    @Test
//    void add() throws SQLException {
//        ChessGameEntity chessRoomEntityToBeAdded = new ChessGameEntity(TEST_TITLE);
//        ChessGameEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
//        PlayerEntity playerEntity = new PlayerEntity(chessRoomEntity);
//
//        playerDAO.add(playerEntity);
//    }
//
//    @Test
//    void findById() throws SQLException {
//        ChessGameEntity chessRoomEntity = new ChessGameEntity(TEST_TITLE);
//        ChessGameEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);
//        PlayerEntity playerEntityToBeAdded = new PlayerEntity(addedChessRoom);
//        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
//
//        PlayerEntity foundPlayerEntity = playerDAO.findById(playerEntity.getId());
//
//        assertThat(foundPlayerEntity).isEqualTo(playerEntity);
//    }
//
//    @Test
//    void delete() throws SQLException {
//        ChessGameEntity chessRoomEntity = new ChessGameEntity(TEST_TITLE);
//        ChessGameEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);
//        PlayerEntity playerEntity = new PlayerEntity(addedChessRoom);
//        playerDAO.add(playerEntity);
//
//        playerDAO.delete(playerEntity);
//
//        PlayerEntity deletedPlayerEntity = playerDAO.findById(playerEntity.getId());
//
//        assertThat(deletedPlayerEntity).isNull();
//    }
//}