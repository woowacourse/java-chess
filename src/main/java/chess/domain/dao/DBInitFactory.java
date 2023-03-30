package chess.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 우가
 * @version 1.0.0
 * @since by 우가 on 2023/03/30
 */
public class DBInitFactory {

    public static void initDB() {
        initPieceTable();
        initTurnTable();

        if (checkNotExistTurnRecord()) {
            initTurnRecord();
        }
    }

    private static boolean checkNotExistTurnRecord() {
        String sql = "SELECT * FROM turn";

        DBConnection dbConnection = DBConnection.getInstance();
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            return !rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void initTurnRecord() {
        String insertTurn = "INSERT INTO turn(current_color) VALUES('WHITE')";

        executeQuery(insertTurn);
    }

    private static void initTurnTable() {
        String createTurnTable = "CREATE TABLE IF NOT EXISTS turn ( "
                + "id bigint auto_increment primary key, "
                + "current_color char(16) default 'white' null)";

        executeQuery(createTurnTable);
    }

    private static void initPieceTable() {
        String createPieceTable = "CREATE TABLE IF NOT EXISTS piece ( "
                + "id bigint auto_increment primary key, "
                + "`rank` bigint   null, "
                + "file   char(2)  null, "
                + "color  char(16) null, "
                + "shape  char(16) null"
                + ")";
        executeQuery(createPieceTable);

    }

    private static void executeQuery(final String sql) {
        DBConnection dbConnection = DBConnection.getInstance();
        try (var connection = dbConnection.getConnection();
             var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
