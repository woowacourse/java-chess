package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.PieceFactory;
import chess.domain.board.Position;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.game.Turn;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.dto.ChessDto;
import chess.dto.MoveDto;
import chess.dto.StatusDto;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessService {

    private final BoardDao boardDao;
    private final TurnDao turnDao;

    public ChessService(final BoardDao boardDao, final TurnDao turnDao) {
        this.boardDao = boardDao;
        this.turnDao = turnDao;
    }

    public ChessDto initializeGame() {
        Board board = toBoard(boardDao.getBoard());
        Team team = Team.of(turnDao.getCurrentTurn());
        return ChessDto.of(board, team.getValue());
    }

    public ChessDto endGame() {
        Board board = new Board(BoardFactory.initialize());
        ChessDto chessDto = ChessDto.of(board);

        Map<String, String> convertedBoard = chessDto.getBoard();
        boardDao.updateBatchPositions(convertedBoard);
        if (Team.of(turnDao.getCurrentTurn()) == Team.BLACK) {
            turnDao.updateTurn(Team.WHITE.getValue(), Team.BLACK.getValue());
        }
        return chessDto;
    }

    public StatusDto createStatus() {
        Board board = toBoard(boardDao.getBoard());
        Score score = new Score(board.getBoard());
        return StatusDto.of(score);
    }

    public ChessDto move(final MoveDto moveDto) {
        String turnBeforeMove = turnDao.getCurrentTurn();
        ChessGame chessGame = new ChessGame(toBoard(boardDao.getBoard()), new Turn(Team.of(turnBeforeMove)));

        String sourcePosition = moveDto.getSource();
        String targetPosition = moveDto.getTarget();
        chessGame.move(sourcePosition, targetPosition);

        boardDao.updatePosition(sourcePosition, chessGame.getPieceName(sourcePosition));
        boardDao.updatePosition(targetPosition, chessGame.getPieceName(targetPosition));

        String turnAfterMove = chessGame.getCurrentTurn().getValue();
        turnDao.updateTurn(turnAfterMove, turnBeforeMove);
        return ChessDto.of(chessGame.isOn(), chessGame.getBoard(), turnAfterMove);
    }

    private Board toBoard(final Map<String, String> findBoard) {
        Map<Position, Piece> boards = findBoard.keySet()
                .stream()
                .collect(Collectors.toMap(Position::valueOf,
                        key -> PieceFactory.createPiece(findBoard.get(key))));
        return new Board(boards);
    }

}
