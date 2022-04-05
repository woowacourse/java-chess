package chess.service;

import chess.dao.BoardDao;
import chess.dao.TurnDao;
import chess.domain.board.BasicChessBoardGenerator;
import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.board.Score;
import chess.domain.command.StatusResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.ApiResult;
import chess.dto.MoveCommand;
import chess.dto.MoveResponseDto;
import java.util.HashMap;
import java.util.Map;

public class ChessWebService {

    private static final Color FIRST_TURN = Color.WHITE;

    private static final BoardDao BOARD_DAO = new BoardDao();
    private static final TurnDao TURN_DAO = new TurnDao();
    private State state;

    private ChessWebService(State state) {
        this.state = state;
    }

    public static ChessWebService numberOf(int gameNumber) {
        Color color = TURN_DAO.findByGameNumber(gameNumber);
        if (color == null) {
            return new ChessWebService(Ready.start(new Board(new HashMap<>())));
        }
        return new ChessWebService(Ready.continueOf(color, BOARD_DAO.findAllByGameNumber(gameNumber)));
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public ApiResult getStatus() {
        Map<Color, Score> score = state.getScore();
        return ApiResult.succeed(new StatusResult(score));
    }

    public void initializeState(int gameNumber) {
        state = Ready.start(BasicChessBoardGenerator.generator());

        deleteAllPiece(gameNumber);
        deleteTurn(gameNumber);

        Map<Position, Piece> value = state.getBoard().getValue();
        value.forEach((position, piece) -> savePiece(position, piece, gameNumber));
        saveTurn(FIRST_TURN, gameNumber);
    }

    public ApiResult movePiece(MoveCommand command) {
        Position source = Position.valueOf(command.getSource());
        Position destination = Position.valueOf(command.getDestination());
        int gameNumber = command.getGameNumber();
        try {
            state = state.movePiece(source, destination);
            updateDbPiece(source, destination, gameNumber);
            updateDbTurn(state.getTurn(), gameNumber);
            return ApiResult.succeed(new MoveResponseDto(command.getSource(), command.getDestination(), isFinished()));
        } catch (RuntimeException e) {
            return ApiResult.failed(e.getMessage());
        }
    }

    private boolean isFinished() {
        return state.isFinished();
    }

    private void savePiece(Position position, Piece piece, int gameNumber) {
        BOARD_DAO.save(position, piece, gameNumber);
    }

    private void saveTurn(Color color, int gameNumber) {
        TURN_DAO.save(color, gameNumber);
    }

    private void updateDbPiece(Position source, Position destination, int gameNumber) {
        BOARD_DAO.updateByPosition(destination, source, gameNumber);
        BOARD_DAO.delete(source, gameNumber);
    }

    private void updateDbTurn(Color color, int gameNumber) {
        TURN_DAO.update(color, gameNumber);
    }

    private void deleteAllPiece(int gameNumber) {
        BOARD_DAO.deleteAllByGameNumber(gameNumber);
    }

    private void deleteTurn(int gameNumber) {
        TURN_DAO.deleteByGameNumber(gameNumber);
    }
}
