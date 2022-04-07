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

    private Board board;
    private final PieceDao pieceDao = new PieceDao();
    private final BoardDao boardDao = new BoardDao();

    public Map<String, Object> startNewGame(int userId) {
        board = new Board(new RegularRuleSetup());
        boardDao.save(userId, getGameStateDto());
        int boardId = boardDao.getBoardIdByPlayer(userId);
        deletePreviousGame(boardId);
        saveNewGame(userId, boardId);
        return gameStateAndPieces(boardId);
    }

    public void move(int boardId, CommendDto commendDto) {
        String source = commendDto.getSource();
        String target = commendDto.getTarget();
        board.move(source, target);

        updateGameState(boardId);
        deleteMovedPieceFromSource(boardId, source);
        updateMovedPieceToTarget(boardId, target);
    }

    public Map<String, Object> loadGame(int playerId) {
        Map<Position, Piece> pieces = new HashMap<>();
        int boardId = boardDao.getBoardIdByPlayer(playerId);
        pieceDao.findAll(boardId).stream()
                .forEach(pieceDto -> pieces.put(Position.of(pieceDto.getPosition()), PieceFactory.build(pieceDto)));
        board = new Board(() -> pieces);
        board.loadTurn(boardDao.find());
        return gameStateAndPieces(boardId);
    }

    private void updateMovedPieceToTarget(int boardId, String target) {
        Piece pickedPiece = board.findPiece(Position.of(target)).get();
        PieceDto pickedPieceDto = PieceDto.from(Position.of(target), pickedPiece);

        Optional<PieceDto> targetPieceDto = pieceDao.findOne(boardId, target);
        if (!targetPieceDto.isEmpty()) {
            pieceDao.updateOne(boardId, target, pickedPieceDto);
            return;
        }
        pieceDao.saveOne(boardId, pickedPieceDto);
    }

    private void deleteMovedPieceFromSource(int boardId, String source) {
        pieceDao.deleteOne(boardId, source);
    }

    private void updateGameState(int boardId) {
        boardDao.update(boardId, getGameStateDto());
    }

    public Map<String, Object> gameStateAndPieces(int boardId) {
        Map<String, Object> data = new HashMap<>();
        data.put("boardId", boardId);
        data.put("state", getGameStateDto());
        data.put("pieces", getPieceDtos());
        return data;
    }

    public Map<String, Object> gameResult() {
        Map<String, Object> data = new HashMap<>();
        data.put("result", getResultDto());
        return data;
    }

    public Map<String, Object> gameFinalResult() {
        Map<String, Object> data = new HashMap<>();
        data.put("result", getFinalResultDto());
        return data;
    }

    private void saveNewGame(int userId, int boardId) {
        pieceDao.saveAll(boardId, board.getPieces());
    }

    private void deletePreviousGame(int boardId) {
        Optional<PieceDto> piece = pieceDao.findOne(boardId, "a1");
        if (!piece.isEmpty()) {
            pieceDao.deleteAll(boardId);
        }
    }

    private GameStateDto getGameStateDto() {
        return GameStateDto.from(board);
    }

    private List<PieceDto> getPieceDtos() {
        Map<Position, Piece> pieces = board.getPieces();
        return pieces.keySet().stream()
                .map(position -> PieceDto.from(position, pieces.get(position)))
                .collect(Collectors.toList());
    }

    private ResultDto getResultDto() {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        return new ResultDto(whiteScore, blackScore, board.gameResult());
    }

    private ResultDto getFinalResultDto() {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        Map<Result, Color> winner = new HashMap<>();
        winner.put(Result.WIN, board.winnersColor());
        return new ResultDto(whiteScore, blackScore, winner);
    }


}
