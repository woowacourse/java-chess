package chess.web.service;

import chess.domain.Color;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.RegularRuleSetup;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.web.PieceFactory;
import chess.web.dao.BoardDao;
import chess.web.dao.PieceDao;
import chess.web.dto.CommendDto;
import chess.web.dto.GameStateDto;
import chess.web.dto.PieceDto;
import chess.web.dto.ResultDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameService {

    private final PieceDao pieceDao = new PieceDao();
    private final BoardDao boardDao = new BoardDao();

    public Map<String, Object> startNewGame(int userId) {
        Board board = new Board(new RegularRuleSetup());
        boardDao.save(userId, getGameStateDto(board));
        int boardId = boardDao.getBoardIdByPlayer(userId);
        deletePreviousPieces(boardId);
        saveNewPieces(board, boardId);
        return gameStateAndPieces(boardId);
    }

    public void move(int boardId, CommendDto commendDto) {
        String source = commendDto.getSource();
        String target = commendDto.getTarget();
        Board board = loadBoard(boardId);
        board.move(source, target);

        Optional<PieceDto> pickedPiece = pieceDao.findOne(boardId, source);
        deleteMovedPieceFromSource(boardId, source);
        updateMovedPieceToTarget(boardId, target, pickedPiece.get());
        updateGameState(board, boardId);
    }

    public Map<String, Object> loadGame(int playerId) {
        int boardId = boardDao.getBoardIdByPlayer(playerId);
        Board board = loadBoard(boardId);
        board.loadTurn(boardDao.getTurn(boardId));
        return gameStateAndPieces(boardId);
    }

    public Map<String, Object> gameStateAndPieces(int boardId) {
        Board board = loadBoard(boardId);
        Map<String, Object> data = new HashMap<>();
        data.put("boardId", boardId);
        data.put("state", getGameStateDto(board));
        data.put("pieces", getPieceDtos(board));
        return data;
    }

    public Map<String, Object> gameResult(int boardId) {
        Board board = loadBoard(boardId);
        Map<String, Object> data = new HashMap<>();
        data.put("result", getResultDto(board));
        return data;
    }

    public Map<String, Object> gameFinalResult(int boardId) {
        Board board = loadBoard(boardId);
        Map<String, Object> data = new HashMap<>();
        data.put("result", getFinalResultDto(board));
        return data;
    }

    private void updateMovedPieceToTarget(int boardId, String target, PieceDto pickedPieceDto) {
        Optional<PieceDto> targetPieceDto = pieceDao.findOne(boardId, target);
        if (targetPieceDto.isPresent()) {
            pieceDao.updateOne(boardId, target, pickedPieceDto);
            return;
        }
        pieceDao.save(boardId, target, pickedPieceDto);
    }

    private void deleteMovedPieceFromSource(int boardId, String source) {
        pieceDao.deleteOne(boardId, source);
    }

    private void updateGameState(Board board, int boardId) {
        boardDao.update(boardId, getGameStateDto(board));
    }

    private Board loadBoard(int boardId) {
        Map<Position, Piece> pieces = new HashMap<>();
        for (PieceDto pieceDto : pieceDao.findAll(boardId)) {
            pieces.put(Position.of(pieceDto.getPosition()), PieceFactory.build(pieceDto));
        }
        Board board = new Board(() -> pieces);
        board.loadTurn(boardDao.getTurn(boardId));
        return board;
    }

    private void saveNewPieces(Board board, int boardId) {
        pieceDao.saveAll(boardId, board.getPieces());
    }

    private void deletePreviousPieces(int boardId) {
        Optional<PieceDto> piece = pieceDao.findOne(boardId, "a1");
        if (piece.isPresent()) {
            pieceDao.deleteAll(boardId);
        }
    }

    private GameStateDto getGameStateDto(Board board) {
        return GameStateDto.from(board);
    }

    private List<PieceDto> getPieceDtos(Board board) {
        Map<Position, Piece> pieces = board.getPieces();
        return pieces.keySet().stream()
                .map(position -> PieceDto.from(position, pieces.get(position)))
                .collect(Collectors.toList());
    }

    private ResultDto getResultDto(Board board) {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        return new ResultDto(whiteScore, blackScore, board.gameResult());
    }

    private ResultDto getFinalResultDto(Board board) {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        Map<Result, Color> winner = new HashMap<>();
        winner.put(Result.WIN, board.winnersColor());
        return new ResultDto(whiteScore, blackScore, winner);
    }

}
