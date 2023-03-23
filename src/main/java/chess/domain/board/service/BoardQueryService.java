package chess.domain.board.service;

import chess.dao.BoardSearchDao;
import chess.domain.board.Board;
import chess.domain.board.repository.BoardRepository;
import chess.domain.board.service.dto.BoardSearchResponse;
import chess.domain.board.service.mapper.BoardMapper;

import java.util.NoSuchElementException;

public class BoardQueryService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardQueryService(final BoardRepository boardRepository, final BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public BoardSearchResponse searchBoard(final Long boardId) {
        final BoardSearchDao boardSearchDao = boardRepository.findById(boardId)
                                                             .orElseThrow(NoSuchElementException::new);

        final String position = boardSearchDao.position();

        final Board board = boardMapper.mapToBoardMapFrom(position);

        return new BoardSearchResponse(board.chessBoard(), boardSearchDao.turn());
    }
}
