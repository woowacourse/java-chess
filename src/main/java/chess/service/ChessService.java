package chess.service;

import chess.dao.BoardDao;
import chess.dao.RoomDao;
import chess.domain.Score;
import chess.domain.Team;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.position.Position;
import chess.domain.state.*;

import java.util.HashMap;
import java.util.Map;

public class ChessService {
    private GameState gameState;
    BoardDao boardDao = new BoardDao();
    RoomDao roomDao = new RoomDao();

    public Map<String, String> getInitialBoard(String roomId) {
        gameState = getGameState(roomId);
        return getBoardPieces();
    }

    private GameState getGameState(String roomId) {
        Map<String, String> room = roomDao.findById(roomId);
        if (room.isEmpty()) {
            return createGameState(roomId);
        }
        return getGameState(room);
    }

    private WhiteTurn createGameState(String roomId) {
        roomDao.save(roomId, "White");
        Map<Position, Piece> board = BoardInitialize.create();
        boardDao.saveAll(board);
        return new WhiteTurn(board);
    }

    private Playing getGameState(Map<String, String> room) {
        Map<String, String> boardDaos = boardDao.findAll(room.get("id"));
        Map<Position, Piece> board = new HashMap<>();
        for (String position : boardDaos.keySet()) {
            Piece piece = PieceFactory.create(boardDaos.get(position));
            board.put(Position.from(position), piece);
        }
        if (room.get("status").equals("White")) {
            return new WhiteTurn(board);
        }
        return new BlackTurn(board);
    }

    public String move(String source, String destination, String roomId) {
        Map<Position, Piece> board = gameState.getBoard();
        Piece sourcePiece = board.get(Position.from(source));
        gameState = gameState.move(source, destination);

        boardDao.updatePosition(source, Blank.SYMBOL);
        boardDao.updatePosition(destination, sourcePiece.getSymbol());
        roomDao.updateStatus(gameState.getTeam().getValue(), roomId);

        return getGameStateResult(roomId);
    }

    private String getGameStateResult(String roomId) {
        if (gameState.isFinished()) {
            roomDao.updateStatus(Team.WHITE.getValue(), roomId);
            return "게임 종료" + gameState.getTeam() + "팀 승";
        }
        return "";
    }

    public Map<String, Object> getStatus() {
        Map<String, Object> model = new HashMap<>();
        Score score = new Score(gameState.getBoard());
        model.put("whiteScore", score.getTotalScoreWhiteTeam());
        model.put("blackScore", score.getTotalScoreBlackTeam());
        return model;
    }

    public Map<String, String> resetBoard(String roomId) {
        gameState = createGameState(roomId);
        return getBoardPieces();
    }

    private Map<String, String> getBoardPieces() {
        Map<Position, Piece> chessBoard = gameState.getBoard();
        Map<String, String> board = new HashMap<>();
        for (Position position : chessBoard.keySet()) {
            Piece piece = chessBoard.get(position);
            board.put(position.getPositionToString(), piece.getSymbol());
        }
        return board;
    }
}
