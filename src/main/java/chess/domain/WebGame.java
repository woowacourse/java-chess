package chess.domain;

import chess.domain.board.Position;
import chess.domain.board.XPosition;
import chess.domain.board.YPosition;
import chess.domain.command.Command;
import chess.domain.command.End;
import chess.domain.command.Move;
import chess.domain.dao.ChessDao;
import chess.domain.dto.PieceDTO;
import chess.domain.piece.Piece;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class WebGame {

    private final Game game;

    public WebGame() {
        this.game = new Game();
    }

    public Map<Position, Piece> startedBoard() {
        game.init();
        return getBoard();
    }

    public Map<Position, Piece> getBoard() {
        return game.getBoard()
            .recentBoard();
    }

    public void loadGame(ChessDao chessDAO) throws SQLException {
        game.loadGame(loadBoard(chessDAO), chessDAO.loadTurnDTO(1));
    }

    public Map<Position, PieceDTO> loadBoard(ChessDao chessDAO) throws SQLException {
        Map<Position, PieceDTO> loadBoard = new HashMap<>();
        for (XPosition xPosition : XPosition.values()) {
            putPieceOnXPosition(chessDAO, loadBoard, xPosition);
        }
        return loadBoard;
    }

    private void putPieceOnXPosition(ChessDao chessDAO, Map<Position, PieceDTO> loadBoard,
        XPosition xPosition) throws SQLException {
        for (YPosition yPosition : YPosition.values()) {
            Position position = Position.of(xPosition, yPosition);
            PieceDTO pieceDTO = chessDAO.pieceOnLocation(position.symbol(), 1);
            loadBoard.put(position, pieceDTO);
        }
    }

    public String getTurn() {
        return this.game.turn();
    }

    public String move(String rawCommand) {
        Move move = new Move();
        try {
            Command command = move.run(game, rawCommand);
            return "true";
        } catch (RuntimeException runtimeException) {
            return runtimeException.getMessage();
        }
    }

    public void end() {
        String rawCommand = "";
        End end = new End();
        end.run(game, rawCommand);
    }

    public boolean isEnd() {
        return game.isEnd();
    }

    public String winnerColor() {
        return game.winnerColor()
            .getName();
    }

    public String whiteScore() {
        double rawWhiteScore = game.computeWhitePoint();
        return String.valueOf(rawWhiteScore);
    }

    public String blackScore() {
        double rawBlackScore = game.computeBlackPoint();
        return String.valueOf(rawBlackScore);
    }
}
