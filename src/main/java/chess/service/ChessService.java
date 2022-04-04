package chess.service;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.state.Ended;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.piece.BishopPiece;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.ChessResponseDto;
import chess.dto.GameDto;
import chess.dto.MoveRequestDto;
import chess.dto.PieceDto;
import java.util.Objects;

public class ChessService {

    private final GameDao gameDao;
    private final BoardDao boardDao;

    public ChessService() {
        this.gameDao = new GameDao();
        this.boardDao = new BoardDao();
    }

    public void load(final ChessGame chessGame) {
        final GameDto gameDto = gameDao.findByMaxId();
        final BoardDto boardDto = boardDao.findByGameId(gameDto.getId());

        final Board board = boardDto.toBoard();
        final State state = createState(gameDto.getState(), gameDto.getTurn(), board);

        chessGame.load(state, board);
    }

    private State createState(final String state, final String turn, final Board board) {
        if (Objects.equals(state, "Ready")) {
            return new Ready(board);
        }
        if (Objects.equals(state, "Started")) {
            return new Started(Color.from(turn), board);
        }
        return new Ended(board);
    }

    public void move(final ChessGame chessGame, final MoveRequestDto moveDto) {
        try {
            final Position from = Position.create(moveDto.getSource());
            final Position to = Position.create(moveDto.getTarget());
            chessGame.move(from, to);

            final Integer id = gameDao.findMaxId();
            boardDao.updateOnePosition(id, from.getName(), new PieceDto(chessGame.getBoard().get(from)));
            boardDao.updateOnePosition(id, to.getName(), new PieceDto(chessGame.getBoard().get(to)));

            gameDao.updateById(GameDto.of(id, chessGame.getState(), chessGame.getState().getTurn()));
        } catch (final Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private ChessResponseDto move1(final ChessGame chessGame, final MoveRequestDto moveDto) {
        try {
            chessGame.move(Position.create(moveDto.getSource()), Position.create(moveDto.getTarget()));
            return new ChessResponseDto(chessGame);
        } catch (final Exception e) {
            return new ChessResponseDto("error", e.getMessage(), chessGame);
        }
    }
}
