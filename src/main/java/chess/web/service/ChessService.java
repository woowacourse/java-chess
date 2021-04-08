package chess.web.service;

import chess.domain.command.Command;
import chess.domain.command.Move;
import chess.domain.command.MoveCommandDAO;
import chess.domain.game.ChessGame;
import chess.domain.game.ChessGameDAO;
import chess.domain.game.Score;
import chess.domain.game.Side;
import chess.exception.ChessException;
import chess.web.view.RenderView;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessService {

    private static final ChessGameDAO CHESS_GAME_DAO = new ChessGameDAO();
    private static final MoveCommandDAO MOVE_COMMAND_DAO = new MoveCommandDAO();

    public String addChessGame(String gameName) throws SQLException {
        ChessGame chessGame = ChessGame.initChessGame();
        chessGame.setName(gameName);

        return CHESS_GAME_DAO.addGame(chessGame);
    }

    public ChessGame replayedChessGame(String gameId) throws SQLException {
        List<Command> commands = MOVE_COMMAND_DAO.findCommandsByGameId(gameId);

        ChessGame chessGame = ChessGame.initChessGame();
        for (Command command : commands) {
            chessGame.execute(command);
        }
        chessGame.setId(gameId);
        return chessGame;
    }

    public Map<String, Object> movePiece(String gameId, String source, String target,
            Side turn) throws SQLException {
        ChessGame chessGame = replayedChessGame(gameId);
        validateCurrentTurn(chessGame, turn);

        move(chessGame, new Move(source, target), turn);

        Map<String, Object> model = RenderView.renderBoard(chessGame);

        if (chessGame.isGameSet()) {
            CHESS_GAME_DAO.updateGameEnd(chessGame.getId());
            model.put("isGameSet", Boolean.TRUE);
            model.put("gameResult", result(chessGame));
        }
        return model;
    }

    private void move(ChessGame chessGame, Move command, Side side) throws SQLException {
        chessGame.execute(command);

        command.setGameId(chessGame.getId());
        command.setSide(side);
        int insertedRowCount = MOVE_COMMAND_DAO.addMoveCommand(command);
        if (insertedRowCount == 0) {
            throw new ChessException("플레이어의 턴이 아닙니다");
        }
    }

    private Map<String, Object> result(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();

        model.put("winner", chessGame.winner().toString());

        Score score = chessGame.score();
        model.put("blackScore", score.blackScore());
        model.put("whiteScore", score.whiteScore());

        return model;
    }

    private void validateCurrentTurn(ChessGame chessGame, Side playerSide) {
        if (!chessGame.currentTurn().equals(playerSide)) {
            throw new ChessException("플레이어의 턴이 아닙니다");
        }
    }
}
