package chess.domain.board.service;

import chess.dao.BoardModifyDao;
import chess.dao.BoardRegisterDao;
import chess.domain.board.Board;
import chess.domain.board.repository.BoardRepository;
import chess.domain.board.service.dto.BoardModifyRequest;
import chess.domain.board.service.dto.BoardRegisterRequest;
import chess.domain.board.service.mapper.BoardMapper;

public class BoardCommandService {

    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    public BoardCommandService(final BoardRepository boardRepository, final BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public Long registerBoard(BoardRegisterRequest boardRegisterRequest) {

        final Board board = boardRegisterRequest.board();
        final String position = boardMapper.mapToBoardPositionFrom(board);

        final BoardRegisterDao boardRegisterDao =
                new BoardRegisterDao(position,
                                     boardRegisterRequest.turn().name()
                );

        return boardRepository.save(boardRegisterDao);
    }

    public void modifyBoard(final BoardModifyRequest boardModifyRequest) {

        final Board board = boardModifyRequest.board();
        final String position = boardMapper.mapToBoardPositionFrom(board);
        final String turn = boardModifyRequest.turn().name();

        final BoardModifyDao boardModifyDao = new BoardModifyDao(boardModifyRequest.id(), position, turn);

        boardRepository.modifyById(boardModifyDao);
    }

    public void deleteBoard(final Long boardId) {
        boardRepository.deleteById(boardId);
    }
}
