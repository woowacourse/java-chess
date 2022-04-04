package chess.web;

import chess.controller.dto.PieceDTO;
import chess.controller.dto.StatusDTO;
import chess.dao.BoardDao;
import chess.dao.RoomDao;
import chess.database.factory.BoardFactory;
import chess.database.factory.RoomFactory;
import chess.domain.Score;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.state.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessController {
    private GameState gameState;

    public Map<String, Object> getInitialBoard() {
        Map<String, Object> model = new HashMap<>();
        gameState = getInitialBoard("1");
        Map<Position, Piece> board = gameState.getBoard();
        List<PieceDTO> pieces = new ArrayList<>();
        for (Position position : board.keySet()) {
            Position piecePosition = board.get(position).getPosition();
            pieces.add(new PieceDTO(piecePosition.getPositionToString(), board.get(position).getSymbol()));
        }
        model.put("chessPiece", pieces);
        return model;
    }

    public Map<String, Object> getBoard() {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> board = gameState.getBoard();
        List<PieceDTO> pieces = new ArrayList<>();
        for (Position position : board.keySet()) {
            Position piecePosition = board.get(position).getPosition();
            pieces.add(new PieceDTO(piecePosition.getPositionToString(), board.get(position).getSymbol()));
        }
        model.put("chessPiece", pieces);
        return model;
    }

    private GameState getInitialBoard(String roomId) {
        RoomDao roomDao = RoomFactory.findById(roomId);
        if (roomDao == null) {
            return createGameState(roomId);
        }
        return getGameState(roomDao);
    }

    private WhiteTurn createGameState(String roomId) {
        RoomFactory.save(roomId, "White");
        Map<Position, Piece> board = BoardInitialize.create();
        BoardFactory.saveAll(board);
        return new WhiteTurn(board);
    }

    private Playing getGameState(RoomDao roomDao) {
        List<BoardDao> boardDaos = BoardFactory.findAll(roomDao.getId());
        Map<Position, Piece> board = new HashMap<>();
        for (BoardDao boardDao : boardDaos) {
            Piece piece = PieceFactory.create(boardDao.getSymbol(), Position.from(boardDao.getPosition()));
            board.put(Position.from(boardDao.getPosition()), piece);
        }
        if (roomDao.isWhiteTurn()) {
            return new WhiteTurn(board);
        }
        return new BlackTurn(board);
    }

    public Map<String, Object> move(String source, String destination) {
        Map<String, Object> model = new HashMap<>();
        try {
            Map<Position, Piece> board = gameState.getBoard();
            Piece sourcePiece = board.get(Position.from(source));
            gameState = gameState.move(source, destination);

            BoardFactory.updatePosition(source, Blank.SYMBOL);
            BoardFactory.updatePosition(destination, sourcePiece.getSymbol());
            RoomFactory.updateStatus(gameState.getTeam().getValue(), "1");

            model.put("status", "무브 성공");
            return model;
        } catch (IllegalArgumentException exception) {
            model.put("status", exception.getMessage());
            return model;
        }
    }

    public Map<String, Object> getStatus() {
        Map<String, Object> model = new HashMap<>();
        Score score = new Score(gameState.getBoard());
        model.put("whiteScore", score.getTotalScoreWhiteTeam());
        model.put("blackScore", score.getTotalScoreBlackTeam());
        return model;
    }
}
