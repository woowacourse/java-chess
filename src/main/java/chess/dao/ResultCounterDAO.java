package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.AbstractPiece;
import chess.domain.Count;
import chess.domain.ResultCounter;
import chess.dto.ResultCounterDTO;
import chess.utils.DataParser;
import chess.utils.WebUtils;

public class ResultCounterDAO {
    public static void add(ResultCounterDTO resultCounterDTO) throws SQLException {
        String query = "INSERT result(name, count) VALUES(?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        ResultCounter resultCounter = resultCounterDTO.getResultCounter();
        for (AbstractPiece abstractPiece : resultCounter.keySet()) {
            pstmt.setString(1, WebUtils.caseChanger(abstractPiece));
            pstmt.setInt(2, resultCounter.get(abstractPiece).getCount());
            pstmt.executeUpdate();
        }
    }


    public static ResultCounterDTO selectAll() throws SQLException {
        String query = "SELECT name, count FROM result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        Map<AbstractPiece, Count> queryResult = new HashMap<>();
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()) {
            queryResult.put(
                    DataParser.piece(rs.getString(1)),
                    DataParser.count(rs.getInt(2))
            );
        }
        ResultCounterDTO resultCounterDTO = new ResultCounterDTO();
        resultCounterDTO.setResultCounter(new ResultCounter(queryResult));
        return resultCounterDTO;
    }

    public static void deleteAll() throws SQLException {
        String query = "TRUNCATE result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.execute();
    }

    public static void afterMove(ResultCounterDTO resultCounterDTO) throws SQLException {
        deleteAll();
        add(resultCounterDTO);
    }
}
