package chess.config;

import chess.domain.board.repository.BoardRepository;
import chess.domain.board.service.BoardCommandService;
import chess.domain.board.service.BoardQueryService;
import chess.domain.board.service.mapper.BoardMapper;

public class ChessConfigure {

    public BoardRepository boardRepository() {
        return new BoardRepository();
    }

    public BoardMapper boardMapper() {
        return new BoardMapper();
    }

    public BoardQueryService boardQueryService() {
        return new BoardQueryService(boardRepository(), boardMapper());
    }

    public BoardCommandService boardCommandService() {
        return new BoardCommandService(boardRepository(), boardMapper());
    }
}
