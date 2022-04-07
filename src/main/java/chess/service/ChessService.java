package chess.service;

import chess.domain.dao.BoardDao;
import chess.domain.dao.GameDao;
import chess.domain.dto.GameDto;
import chess.domain.dto.PieceDto;
import chess.domain.dto.ResponseDto;
import chess.domain.game.Color;
import chess.domain.game.Status;
import chess.domain.game.board.ChessBoard;
import chess.domain.game.board.ChessBoardFactory;
import chess.domain.game.status.End;
import chess.domain.game.status.Playing;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.Type;
import chess.domain.position.Position;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private static final int EMPTY_RESULT = 0;
    private final GameDao gameDao = new GameDao();
    private final BoardDao boardDao = new BoardDao();

    private ChessBoard chessBoard = null;

    public void start() {
        if (isNotSaved()) {
            chessBoard = ChessBoardFactory.initBoard();
            chessBoard.changeStatus(new Playing());
            return;
        }
        loadLastGame();
    }

    private boolean isNotSaved() {
        return gameDao.findLastGameId() == EMPTY;
    }

    public void save() {
        int gameId = gameDao.save(chessBoard);
        for (Map.Entry<String, ChessPiece> entry : chessBoard.convertToMap().entrySet()) {
            boardDao.save(
                    gameId,
                    getPosition(entry),
                    getPiece(entry),
                    getColor(entry));
        }
    }

    private String getPosition(Map.Entry<String, ChessPiece> entry) {
        return entry.getKey();
    }

    private String getPiece(Map.Entry<String, ChessPiece> entry) {
        return entry.getValue().getName();
    }

    private String getColor(Map.Entry<String, ChessPiece> entry) {
        return entry.getValue().getColor().name();
    }

    private void loadLastGame() {
        HashMap<Position, ChessPiece> board = new HashMap<>();
        int lastGameId = gameDao.findLastGameId();
        for (PieceDto pieceDto : boardDao.findByGameId(lastGameId)) {
            ChessPiece piece = makePiece(pieceDto);
            board.put(new Position(pieceDto.getPosition()), piece);
        }
        GameDto game = gameDao.findById(lastGameId);
        chessBoard = new ChessBoard(board, new Playing(), game.getTurn());
    }

    private ChessPiece makePiece(PieceDto pieceDto) {
        return Type.from(pieceDto.getPiece()).createPiece(getPieceColor(pieceDto));
    }

    private Color getPieceColor(PieceDto pieceDto) {
        return Color.from(pieceDto.getColor());
    }

    public ResponseDto move(String source, String target) {
        try {
            if (chessBoard.compareStatus(Status.PLAYING)) {
                chessBoard.move(new Position(source), new Position(target));
            }
        } catch (IllegalArgumentException e) {
            return new ResponseDto(500, e.getMessage());
        }
        return new ResponseDto(200, null);
    }

    public Map<String, Double> status() {
        return chessBoard.calculateScore().entrySet().stream()
                .collect(Collectors.toMap(m -> m.getKey().name(), Map.Entry::getValue));
    }

    public void end() throws SQLException {
        chessBoard.changeStatus(new End());
        boardDao.delete(gameDao.findLastGameId());
        gameDao.delete();
    }

    public String findWinner() {
        return chessBoard.decideWinner().name();
    }

    public Map<String, String> currentBoardForUI() {
        return chessBoard.convertToImageName();
    }

    public boolean checkStatus(Status status) {
        return chessBoard.compareStatus(status);
    }
}
