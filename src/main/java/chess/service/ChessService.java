package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.piece.Team;
import chess.dto.ChessDto;
import chess.dto.MoveDto;
import chess.dto.StatusDto;
import java.util.Map;
import java.util.Map.Entry;

public class ChessService {

    private final BoardDao boardDao;
    private final TurnDao turnDao;

    public ChessService() {
        boardDao = new BoardDao();
        turnDao = new TurnDao();
    }

    public ChessDto initializeGame() {
        Board board = BoardFactory.createBoard(boardDao.getBoard());
        Team team = Team.of(turnDao.getCurrentTurn());
        return ChessDto.of(board, team.getValue());
    }

    public ChessDto endGame() {
        Board board = new Board(BoardFactory.initialize());
        ChessDto chessDto = ChessDto.of(board);

        Map<String, String> convertedBoard = chessDto.getBoard();
        for (final Entry<String, String> boardEntry : convertedBoard.entrySet()) {
            boardDao.updatePosition(boardEntry.getKey(), boardEntry.getValue());
        }
        return ChessDto.of(true, board, "white");
    }

    public StatusDto createStatus() {
        Board board = BoardFactory.createBoard(boardDao.getBoard());
        Score score = new Score(board.getBoard());
        return StatusDto.of(score);
    }

    public ChessDto move(final MoveDto moveDto) {
        ChessGame chessGame = new ChessGame(BoardFactory.createBoard(boardDao.getBoard()));
        String sourcePosition = moveDto.getSource();
        String targetPosition = moveDto.getTarget();
        chessGame.move(sourcePosition, targetPosition);

        boardDao.updatePosition(sourcePosition, chessGame.getPieceName(sourcePosition));
        boardDao.updatePosition(targetPosition, chessGame.getPieceName(targetPosition));
        Team turn = chessGame.getCurrentTurn();
        Team previous = turn.oppositeTeam();

        String currentTurn = turn.getValue();
        turnDao.updateTurn(currentTurn, previous.getValue());

        return ChessDto.of(chessGame.isOn(),chessGame.getBoard(), currentTurn);
    }

}
