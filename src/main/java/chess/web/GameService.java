package chess.web;

import chess.domain.Color;
import chess.domain.Result;
import chess.domain.board.Board;
import chess.domain.board.RegularRuleSetup;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
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

    public void startNewGame() {
        board = new Board(new RegularRuleSetup());
        deletePreviousGame();
        saveNewGame();
    }

    public void move(CommendDto commendDto) {
        String source = commendDto.getSource();
        String target = commendDto.getTarget();
        board.move(source, target);

        updateGameState();
        deleteMovedPieceFromSource(source);
        updateMovedPieceToTarget(target);
    }

    public void loadGame() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieceDao.findAll().stream()
                .forEach(pieceDto -> pieces.put(Position.of(pieceDto.getPosition()), PieceFactory.build(pieceDto)));
        board = new Board(() -> pieces);
        board.loadTurn(boardDao.find());
    }

    private void updateMovedPieceToTarget(String target) {
        Piece pickedPiece = board.findPiece(Position.of(target)).get();
        PieceDto pickedPieceDto = PieceDto.from(Position.of(target), pickedPiece);

        Optional<PieceDto> targetPieceDto = pieceDao.findOne(target);
        if (!targetPieceDto.isEmpty()) {
            pieceDao.updateOne(target, pickedPieceDto);
            return;
        }
        pieceDao.saveOne(pickedPieceDto);
    }

    private void deleteMovedPieceFromSource(String source) {
        pieceDao.deleteOne(source);
    }

    private void updateGameState() {
        boardDao.update(getGameStateDto());
    }

    public Map<String, Object> gameStateAndPieces() {
        Map<String, Object> data = new HashMap<>();
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

    private void saveNewGame() {
        pieceDao.saveAll(board.getPieces());
        boardDao.save(getGameStateDto());
    }

    private void deletePreviousGame() {
        pieceDao.deleteAll();
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
