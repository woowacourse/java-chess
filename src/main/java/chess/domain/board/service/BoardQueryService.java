package chess.domain.board.service;

import chess.dao.BoardDao;
import chess.domain.board.Board;
import chess.domain.board.service.dto.AllBoardSearchResponse;
import chess.domain.board.service.mapper.BoardMapper;
import chess.domain.board.service.newDto.BoardSearchResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    public AllBoardSearchResponse searchAllBoards() {

        final List<BoardSearchResponse> boardSearchResponses = boardDao.findAll();
        final List<Long> ids = new ArrayList<>();

        for (final BoardSearchResponse boardSearchResponse : boardSearchResponses) {
            ids.add(boardSearchResponse.id());
        }

        return new AllBoardSearchResponse(ids);
    }

}
