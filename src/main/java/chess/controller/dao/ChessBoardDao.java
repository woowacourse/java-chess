package chess.controller.dao;

import chess.controller.dto.ResponseDto;
import chess.controller.dto.Tile;
import chess.domain.chesspiece.ChessPieceFactory;
import chess.domain.chesspiece.Piece;
import chess.domain.game.ChessBoard;
import chess.domain.game.Player;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.status.Result;
import chess.domain.status.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoardDao {
    private Connection connection = new ConnectionDao().getConnection();

    // 초기 게임 만드는 메서드
    public void createInitGame(ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO game(turn, white_score, black_score, state) VALUES (?,?,?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.setString(4, "playing");
        pstmt.executeUpdate();

        saveChessBoard(connection, responseDto);
    }

    public void saveChessBoard(Connection connection, ResponseDto responseDto) throws SQLException {
        String query = "INSERT INTO chessboard VALUES ((SELECT MAX(game_id) FROM game),?,?)";
        PreparedStatement pstmt = connection.prepareStatement(query);

        for (Tile tile : responseDto.getChessBoardDto().getTiles()) {
            pstmt.setString(1, tile.getPosition());
            pstmt.setString(2, tile.getPiece());
            pstmt.executeUpdate();
        }
    }

    public void pieceMove(ResponseDto responseDto) throws SQLException {
        updateGameWithMove(responseDto);
        deleteChessBoardWithMove();
        saveChessBoard(connection, responseDto);
    }

    private void deleteChessBoardWithMove() throws SQLException {
        String query = "DELETE FROM chessboard WHERE room = (SELECT MAX(game_id) FROM game )";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.executeUpdate();
    }

    private void updateGameWithMove(ResponseDto responseDto) throws SQLException {
        String query = "UPDATE game SET turn = ?, white_score = ?, black_score = ? WHERE game_id = ( select MAX(game_id) from (select game_id from game) as t)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, responseDto.getTurn().name());
        pstmt.setDouble(2, responseDto.getResult().getStatuses().get(0).getScore());
        pstmt.setDouble(3, responseDto.getResult().getStatuses().get(1).getScore());
        pstmt.executeUpdate();
    }

    public ResponseDto loadGamePlaying() throws SQLException {
        String query = "SELECT a.game_id, a.black_score, a.turn, a.white_score, b.position, b.piece FROM game a INNER JOIN chessboard b ON b.room = (SELECT MAX(game_id) FROM game) AND a.game_id = (SELECT MAX(game_id) FROM game)";

        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        Map<Position, Piece> chessBoard = new HashMap<>();

        Result result = null;
        Player turn = null;

        while (rs.next()) {
            if (turn == null & result == null) {
                turn = Player.valueOf(rs.getString("turn"));
                result = getResult(rs);
            }
            chessBoard.put(Positions.of(rs.getString("position")),
                    ChessPieceFactory.of(rs.getString("piece")));
        }

        ResponseDto responseDto = new ResponseDto(new ChessBoard(chessBoard), result, turn);

        return responseDto;
    }

    private Result getResult(ResultSet rs) throws SQLException {
        Status whiteStatus = new Status(Player.WHITE, rs.getDouble("white_score"));
        Status blackStatus = new Status(Player.BLACK, rs.getDouble("black_score"));
        List<Status> statuses = new ArrayList<>();
        statuses.add(whiteStatus);
        statuses.add(blackStatus);
        return new Result(statuses);
    }

    public String loadState() throws SQLException {
        String query = "SELECT state FROM game WHERE game_id=(SELECT MAX(game_id) FROM game)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        if (!rs.next()) return null;

        return rs.getString("state");
    }

    public void updateEndState() throws SQLException {
        String query = "UPDATE game SET state = ? WHERE game_id = (select MAX(game_id) from (select game_id from game) as t)";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, "end");
        pstmt.executeUpdate();
    }
}
