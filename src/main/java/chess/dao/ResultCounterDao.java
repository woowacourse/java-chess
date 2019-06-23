package chess.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.AbstractPiece;
import chess.domain.Count;
import chess.domain.ResultCounter;
import chess.dto.ResultCounterDto;
import chess.utils.DataProcessor;
import chess.utils.WebUtils;

public class ResultCounterDao {
    public static void add(ResultCounterDto resultCounterDTO) throws SQLException {
        String query = "INSERT result(name, count) VALUES(?, ?)";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);

        ResultCounter resultCounter = resultCounterDTO.getResultCounter();
        for (AbstractPiece abstractPiece : resultCounter.keySet()) {
            pstmt.setString(1, WebUtils.caseChanger(abstractPiece));
            pstmt.setInt(2, resultCounter.get(abstractPiece).getCount());
            pstmt.executeUpdate();
        }
    }

    public static ResultCounterDto selectAll() throws SQLException {
        String query = "SELECT name, count FROM result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        Map<AbstractPiece, Count> queryResult = loadData(pstmt);
        return dataLoadedDTO(queryResult);
    }

    private static Map<AbstractPiece, Count> loadData(PreparedStatement pstmt) throws SQLException {
        Map<AbstractPiece, Count> queryResult = new HashMap<>();
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            queryResult.put(
                    DataProcessor.piece(rs.getString(1)),
                    DataProcessor.count(rs.getInt(2))
            );
        }
        return queryResult;
    }

    private static ResultCounterDto dataLoadedDTO(Map<AbstractPiece, Count> queryResult) {
        ResultCounterDto resultCounterDTO = new ResultCounterDto();
        resultCounterDTO.setResultCounter(new ResultCounter(queryResult));
        return resultCounterDTO;
    }

    public static void deleteAll() throws SQLException {
        String query = "TRUNCATE result";
        PreparedStatement pstmt = JDBCConnection.start().prepareStatement(query);
        pstmt.execute();
    }

    public static void afterMove(ResultCounterDto resultCounterDTO) throws SQLException {
        deleteAll();
        add(resultCounterDTO);
    }
}
