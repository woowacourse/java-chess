package chess.domain.board.service;

import chess.dao.BoardDao;
import chess.domain.board.Board;
import chess.domain.board.service.dto.BoardSearchResponse;
import chess.domain.board.service.mapper.BoardMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class BoardQueryService {

    private final BoardDao boardDao;
    private final BoardMapper boardMapper;

    public BoardQueryService(final BoardDao boardDao, final BoardMapper boardMapper) {
        this.boardDao = boardDao;
        this.boardMapper = boardMapper;
    }

    public Board searchBoard(final Long boardId) {


        final BoardSearchResponse boardSearchResponse = boardDao.findById(boardId)
                                                                .orElseThrow(NoSuchElementException::new);

        return boardMapper.mapToBoardSearchResponseFrom(boardSearchResponse.position(),
                                                        boardSearchResponse.turn());
    }

    public List<Long> searchAllBoards() {

        final List<BoardSearchResponse> boardSearchResponses = boardDao.findAll();

        return boardSearchResponses.stream()
                                   .map(it -> it.id())
                                   .collect(Collectors.toList());
    }

}
