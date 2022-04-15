package chess.dao;

import chess.domain.ChessGame;
import chess.domain.piece.Piece;
import chess.domain.player.Player;
import chess.domain.player.Team;
import chess.domain.position.Position;
import chess.dto.ChessGameUpdateDto;
import chess.dto.PieceDto;
import chess.utils.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChessGameDao {

    private final Connection connection;

    public ChessGameDao() {
        this.connection = SQLConnection.getConnection();
    }

    public Optional<Integer> findChessGameIdByName(final String gameName) {
        final String sql = "select id from chess_game where name = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gameName);
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void createNewChessGame(final ChessGame chessGame, final String gameName) {
        saveChessGame(gameName, chessGame.getTurn());
        final int chessGameId = findChessGameIdByName(gameName)
                .orElseThrow(() -> new IllegalArgumentException("해당 이름의 게임이 존재하지 않습니다."));
        savePieces(chessGame.getCurrentPlayer(), chessGameId);
        savePieces(chessGame.getOpponentPlayer(), chessGameId);
    }

    private void saveChessGame(final String gameName, final Team turn) {
        final String sql = "insert into chess_game (name, turn) values (?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, gameName);
            preparedStatement.setString(2, turn.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("게임을 저장할 수 없습니다.");
        }
    }

    public void savePieces(final Player player, final int chessGameId) {
        final String sql = "insert into piece (position, name, team, chess_game_id) values (?, ?, ?, ?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            final List<Piece> pieces = player.findAll();
            saveEachPiece(player, preparedStatement, pieces, chessGameId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void saveEachPiece(final Player player, final PreparedStatement preparedStatement, final List<Piece> pieces,
            int chessGameId)
            throws SQLException {
        for (Piece piece : pieces) {
            preparedStatement.setString(1, toPositionString(piece.getPosition()));
            preparedStatement.setString(2, String.valueOf(piece.getName()));
            preparedStatement.setString(3, player.getTeamName());
            preparedStatement.setInt(4, chessGameId);
            preparedStatement.executeUpdate();
        }
    }

    private String toPositionString(final Position position) {
        final char file = position.getFile().getValue();
        final int rank = position.getRank().getValue();
        return String.valueOf(file) + rank;
    }

    public ChessGameUpdateDto findChessGame(final int chessGameId) {
        final String turn = findCurrentTurn(chessGameId)
                .orElseThrow(() -> new IllegalArgumentException("현재 턴을 확인할 수 없습니다."));
        final List<PieceDto> whitePieces = findAllPieceByIdAndTeam(chessGameId, Team.WHITE.getName());
        final List<PieceDto> blackPieces = findAllPieceByIdAndTeam(chessGameId, Team.BLACK.getName());
        return new ChessGameUpdateDto(turn, whitePieces, blackPieces);
    }

    public List<PieceDto> findAllPieceByIdAndTeam(final int chessGameId, final String team) {
        final String sql = "select * from piece where chess_game_id = (?) and team = (?)";
        final List<PieceDto> pieces = new ArrayList<>();
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chessGameId);
            preparedStatement.setString(2, team);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                final String position = resultSet.getString("position");
                final String name = resultSet.getString("name");
                pieces.add(new PieceDto(position, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pieces;
    }

    private Optional<String> findCurrentTurn(final int chessGameId) {
        final String sql = "select turn from chess_game where id = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chessGameId);
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return Optional.ofNullable(resultSet.getString("turn"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void deletePieces(final int chessGameId) {
        final String sql = "delete from piece where chess_game_id = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chessGameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("체스말을 삭제할 수 없습니다.");
        }
    }

    public void deleteChessGame(final int chessGameId) {
        final String sql = "delete from chess_game where id = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, chessGameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("게임을 삭제할 수 없습니다.");
        }
    }

    public void updateGameTurn(final int gameId, final Team nextTurn) {
        final String sql = "update chess_game set turn = (?) where id = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nextTurn.getName());
            preparedStatement.setInt(2, gameId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePiece(final int gameId, final String current, final String destination,
            final String currentTeam, final String opponentTeam) {
        deletePieceByGameIdAndPositionAndTeam(gameId, destination, opponentTeam);
        updatePiecePositionByGameId(gameId, current, destination, currentTeam);
    }

    private void deletePieceByGameIdAndPositionAndTeam(final int gameId, final String position, final String team) {
        final String sql = "delete from piece where chess_game_id = (?) and position = (?) and team = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, gameId);
            preparedStatement.setString(2, position);
            preparedStatement.setString(3, team);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("해당 위치의 체스말을 삭제할 수 없습니다.");
        }
    }

    private void updatePiecePositionByGameId(final int gameId, final String current, final String destination,
            final String team) {
        final String sql = "update piece set position = (?) where chess_game_id = (?) and position = (?) and team = (?)";
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, destination);
            preparedStatement.setInt(2, gameId);
            preparedStatement.setString(3, current);
            preparedStatement.setString(4, team);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
