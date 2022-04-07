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
    private Board board;

    public void startNewGame() {
        board = new Board(new RegularRuleSetup());
        pieceDao.deleteAll();
        pieceDao.saveAll(board.getPieces());
        boardDao.save(getGameStateDto());
    }

    public void move(CommendDto commendDto) {
        String source = commendDto.getSource();
        String target = commendDto.getTarget();

        board.move(source, target);

        boardDao.update(getGameStateDto());

        Piece pickedPiece = board.findPiece(Position.of(target)).get();
        PieceDto pickedPieceDto = PieceDto.from(Position.of(target), pickedPiece);

        pieceDao.deleteOne(source);
        Optional<PieceDto> targetPieceDto = pieceDao.findOne(target);

        if (!targetPieceDto.isEmpty()) {
            pieceDao.updateOne(target, pickedPieceDto);
            return;
        }

        pieceDao.saveOne(pickedPieceDto);
    }

    public void loadBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        pieceDao.findAll().stream()
                .forEach(pieceDto -> pieces.put(Position.of(pieceDto.getPosition()), PieceFactory.build(pieceDto)));
        board = new Board(() -> pieces);
        Color color = boardDao.find();
        board.loadTurn(color);
    }

    public ResultDto getFinalResultDto() {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        Map<Result, Color> winner = new HashMap<>();
        winner.put(Result.WIN, board.winnersColor());
        return new ResultDto(whiteScore, blackScore, winner);
    }

    public ResultDto getResultDto() {
        int whiteScore = (int) board.calculateScore(Color.WHITE);
        int blackScore = (int) board.calculateScore(Color.BLACK);
        return new ResultDto(whiteScore, blackScore, board.gameResult());
    }

    public GameStateDto getGameStateDto() {
        return GameStateDto.from(board);
    }

    public List<PieceDto> getPieceDtos() {
        Map<Position, Piece> pieces = board.getPieces();
        return pieces.keySet().stream()
                .map(position -> PieceDto.from(position, pieces.get(position)))
                .collect(Collectors.toList());
    }
}
