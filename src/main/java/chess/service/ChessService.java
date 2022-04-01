package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Team;
import chess.dto.BoardDto;
import chess.dto.MoveDto;
import chess.dto.StatusDto;

public class ChessService {

    private final BoardDao boardDao;
    private final TurnDao turnDao;

    public ChessService() {
        boardDao = new BoardDao();
        turnDao = new TurnDao();
    }

    public BoardDto initializeGame() {
        Board board = BoardFactory.createBoard(boardDao.getBoard());
        Team team = Team.of(turnDao.getCurrentTurn());
        ChessGame chessGame = new ChessGame(board, new Turn(team));
        return BoardDto.of(board);
    }

    public BoardDto endGame() {
        // dao에서 기존 보드 상태를 모두 최초의 상태로 업데이트
        return BoardDto.of(false, new Board(BoardFactory.initialize()));
    }

    public StatusDto createStatus() {
        Board board = BoardFactory.createBoard(boardDao.getBoard());
        Score score = new Score(board.getBoard());
        return StatusDto.of(score);
    }

    public BoardDto move(final MoveDto moveDto) {
        // dao에서 현재 보드를 가져와야함
        ChessGame chessGame = new ChessGame(new Board(BoardFactory.initialize()));
        chessGame.move(moveDto.getSource(), moveDto.getTarget());

        // Boarddao에 포지션 업데이트 (왕이 죽으면 chessGame은 종료됨)
        // Turndao 턴 업데이트

        return BoardDto.of(chessGame.isOn(),chessGame.getBoard());
    }
}
