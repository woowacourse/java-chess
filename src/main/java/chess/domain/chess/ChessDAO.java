package chess.domain.chess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import chess.domain.ConnectionUtils;
import chess.domain.board.BoardDTO;
import chess.domain.piece.PieceDTO;
import chess.domain.position.MovePosition;
import chess.domain.position.MovePositionVO;

public class ChessDAO {

    public ChessDTO findChessById(Long chessId) {
        String query = "SELECT c.status, c.turn FROM chess c WHERE c.chess_id = (?)";

        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            ResultSet resultSet = pstmt.executeQuery();
            final String status = resultSet.getString("status");
            final String turn = resultSet.getString("turn");
            final List<PieceDTO> piecesByChessId = findPiecesByChessId(chessId);
            return new ChessDTO(status, turn, piecesByChessId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalStateException("해당 Id에 해당하는 체스 게임이 존재하지 않습니다.");
    }

    private List<PieceDTO> findPiecesByChessId(Long chessId) {
        List<PieceDTO> pieceDTOS = new ArrayList<>();
        String query = "SELECT position, color, name FROM piece WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setLong(1, chessId);
            final ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                final String position = resultSet.getString("position");
                final String color = resultSet.getString("color");
                final String name = resultSet.getString("name");
                pieceDTOS.add(new PieceDTO(position, color, name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pieceDTOS;
    }

    public long insert(BoardDTO boardDTO) {
        final long chessId = insertChess();
        insertPieces(chessId, boardDTO);
        return chessId;
    }

    private long insertChess() {
        String query =
                "INSERT INTO chess (status, turn) VALUES ('RUNNING', 'WHITE'); SELECT LAST_INSERT_ID()";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            final ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("chess_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new IllegalStateException("체스 생성에 실패했습니다.");
    }

    private void insertPieces(Long chessId, BoardDTO boardDTO) {
        String query = "INSERT INTO piece (chess_id, position, color, name) VALUES (?, ?, ?, ?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            for (PieceDTO pieceDTO : boardDTO.getPieceDTOS()) {
                String position = pieceDTO.getPosition();
                String color = pieceDTO.getColor();
                String name = pieceDTO.getName();

                pstmt.setLong(1, chessId);
                pstmt.setString(2, position);
                pstmt.setString(3, color);
                pstmt.setString(4, name);
                pstmt.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void move(Long chessId, MovePosition movePosition) {
        MovePositionVO movePositionVO = MovePositionVO.from(movePosition);
        updateSourcePosition(chessId, movePositionVO);
        updateTargetPosition(movePositionVO);
    }

    private void updateTargetPosition(MovePositionVO movePositionVO) {
        String query = "UPDATE piece SET color = 'BLANK', name = 'BLANK' WHERE position = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionVO.getSource());
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void updateSourcePosition(Long chessId, MovePositionVO movePositionVO) {
        String query =
                "UPDATE piece, (SELECT color, name FROM piece WHERE position = (?)) AS source " +
                        "SET piece.color = source.color, piece.name = source.name " +
                        "WHERE position = (?) AND chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, movePositionVO.getSource());
            pstmt.setString(2, movePositionVO.getTarget());
            pstmt.setLong(3, chessId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateChess(Long chessId, String status, String turn) {
        String query =
                "UPDATE chess SET status = (?), turn = (?) WHERE chess_id = (?)";
        try (Connection connection = ConnectionUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, status);
            pstmt.setString(2, turn);
            pstmt.setLong(3, chessId);
            pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
