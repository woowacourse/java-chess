package chess.web.service;

import chess.domain.board.ChessBoardGenerator;
import chess.domain.dto.GameStatus;
import chess.domain.piece.property.Team;
import chess.domain.position.Position;
import chess.web.dao.ChessGame;
import chess.web.dao.ChessGameDAO;
import chess.web.dao.Movement;
import chess.web.dao.MovementDAO;
import chess.web.view.Render;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();
    private static final MovementDAO MOVEMENT_DAO = new MovementDAO();

    public String addChessGame(String gameName) throws SQLException {
        ChessGame chessGame = new ChessGame(new ChessBoardGenerator());
        chessGame.setName(gameName);

        return CHESS_GAME_DAO.addGame(chessGame);
    }

    public ChessGame replayedChessGame(String gameId) throws SQLException {
        List<Movement> movementByGameId = MOVEMENT_DAO.findMovementByGameId(gameId);
        ChessGame chessGame = ChessGame.initChessGame();
        for (Movement movement : movementByGameId) {
            chessGame.execute(movement);
        }
        chessGame.setId(gameId);
        return chessGame;
    }

    public Map<String, Object> movePiece(String gameId, String source, String target, Team team) throws SQLException {

        ChessGame chessGame = replayedChessGame(gameId);
        validateCurrentTurn(chessGame, team);

        move(chessGame, new Movement(Position.of(source), Position.of(target)), team);

        Map<String, Object> model = Render.renderBoard(chessGame);

        if (chessGame.isGameSet()) {
            CHESS_GAME_DAO.updateGameEnd(chessGame.getId());
            model.put("isGameSet", Boolean.TRUE);
            model.put("gameResult", result(chessGame));
        }
        return model;
    }

    private void validateCurrentTurn(ChessGame chessGame, Team team) {
        if (!chessGame.getChessBoard().getCurrentTurn().equals(team)) {
            throw new IllegalArgumentException("플레이어의 턴이 아닙니다");
        }
    }

    private void move(ChessGame chessGame, Movement movement, Team team) throws SQLException {
        chessGame.execute(movement);

        movement.setGameId(chessGame.getId());
        movement.setTeam(team);
        int insertedRowCount = MOVEMENT_DAO.addMoveCommand(movement);
        if (insertedRowCount == 0) {
            throw new IllegalArgumentException("플레이어의 턴이 아닙니다");
        }
    }

    private Map<String, Object> result(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();

        model.put("winner", chessGame.getChessBoard().calculateWhoWinner().toString());

        double blackScore = GameStatus.calculateTeamScore(chessGame.getChessBoard().getBoard(), Team.BLACK);
        double whiteScore = GameStatus.calculateTeamScore(chessGame.getChessBoard().getBoard(), Team.WHITE);
        model.put("blackScore", blackScore);
        model.put("whiteScore", whiteScore);

        return model;
    }
}
