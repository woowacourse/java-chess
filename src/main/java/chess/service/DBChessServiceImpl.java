package chess.service;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.MoveResult;
import chess.dto.MoveRequestDto;
import chess.dto.MoveResultDto;
import chess.dto.PositionDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBChessServiceImpl implements ChessService {
    private final ChessDao chessDao;

    public DBChessServiceImpl(ChessDao chessDao) {
        this.chessDao = chessDao;
    }

    @Override
    public List<PositionDto> getBoardByGameId(String gameId) {
        return chessDao.getBoardByGameId(gameId)
                .entrySet()
                .stream()
                .map(entry -> PositionDto.of(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public MoveResultDto move(MoveRequestDto moveRequestDto) {
        final Board board = findBoardByGameId(moveRequestDto.getGameId());
        MoveResult moveResult = move(board, moveRequestDto);
        if (moveResult != MoveResult.FAIL) {
            chessDao.move(moveRequestDto);
        }
        return new MoveResultDto(moveRequestDto.getPiece(), moveRequestDto.getFrom(), moveRequestDto.getTo(),
                moveResult);
    }

    private Board findBoardByGameId(String gameId) {
        final Map<String, String> boardByGameId = chessDao.getBoardByGameId(gameId);
        final Color color = Color.fromInt(chessDao.getTurnByGameId(gameId));
        return BoardFactory.newInstance(boardByGameId, color);
    }

    private MoveResult move(Board board, MoveRequestDto moveRequestDto) {
        return board.move(Position.from(moveRequestDto.getFrom()), Position.from(moveRequestDto.getTo()));
    }

    @Override
    public boolean isFinished(String gameId) {
        final Map<String, String> boardByGameId = chessDao.getBoardByGameId(gameId);
        final Board board = BoardFactory.newInstance(boardByGameId, Color.WHITE);
        return board.isFinished();
    }

    @Override
    public Map<Color, Double> getScore(String gameId) {
        final Board board = findBoardByGameId(gameId);
        return board.getScore();
    }
}
