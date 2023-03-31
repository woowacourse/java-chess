package chess.config;

import chess.dao.BoardDao;
import chess.domain.board.service.BoardCommandService;
import chess.domain.board.service.BoardQueryService;
import chess.domain.board.service.mapper.BoardMapper;

public class ChessConfigure {

    public BoardDao boardDao() {
        return new BoardDao();
    }

    public BoardMapper boardMapper() {
        return new BoardMapper();
    }

    public BoardQueryService boardQueryService() {
        return new BoardQueryService(boardDao(), boardMapper());
    }

    public BoardCommandService boardCommandService() {
        return new BoardCommandService(boardDao(), boardMapper());
    }
}
