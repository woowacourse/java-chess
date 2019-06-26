package chess.service;

import chess.domain.chess.ChessBoard;
import chess.domain.chess.Team;
import chess.domain.chess.dao.ChessBoardDAO;
import chess.domain.chess.exception.IllegalChessBoardException;
import chess.domain.chess.initializer.ChessBoardInitializer;
import chess.domain.geometric.Position;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ChessBoardService {
    private ChessBoardDAO chessBoardDAO;

    public ChessBoardService(Connection connection) {
        this.chessBoardDAO = new ChessBoardDAO(connection);
    }

    public void move(ChessBoard chessBoard, String[] sourcePosition, String[] targetPosition) {
        Position source = Position.create(Integer.parseInt(sourcePosition[0]), Integer.parseInt(sourcePosition[1]));
        Position target = Position.create(Integer.parseInt(targetPosition[0]), Integer.parseInt(targetPosition[1]));
        chessBoard.move(source, target);
        chessBoard.changeTeam();
    }

    public ChessBoard selectRecentGame() throws SQLException {
        return Optional.ofNullable(chessBoardDAO.selectRecentGame())
                .orElseThrow(() -> new IllegalChessBoardException("진행 중인 게임이 없습니다."));
    }

    public void createGame() throws SQLException {
        ChessBoard chessBoard = new ChessBoard(new ChessBoardInitializer());
        chessBoardDAO.add(chessBoard, Team.WHITE);
    }

    public void update(ChessBoard chessBoard, Team team) throws SQLException {
        chessBoardDAO.update(chessBoard, team);
    }
}
