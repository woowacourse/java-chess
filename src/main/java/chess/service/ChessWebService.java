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

    private final BoardDao boardDao = new BoardDao();
    private final TurnDao turnDao = new TurnDao();
    private State state;

    public ChessWebService() {
        Color color = turnDao.find();
        if (color == null) {
            this.state = Ready.start(new Board(new HashMap<>()));
            return;
        }
        this.state = Ready.continueOf(color, boardDao.findAll());
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public ApiResult getStatus() {
        Map<Color, Score> score = state.getScore();
        return ApiResult.succeed(new StatusResult(score));
    }

    public void initializeState() {
        state = Ready.start(BasicChessBoardGenerator.generator());

        deleteAllPiece();
        deleteTurn();

        Map<Position, Piece> value = state.getBoard().getValue();
        value.forEach(this::savePiece);
        saveTurn(FIRST_TURN);
    }

    public ApiResult movePiece(MoveCommand command) {
        Position source = Position.valueOf(command.getSource());
        Position destination = Position.valueOf(command.getDestination());
        try {
            state = state.movePiece(source, destination);
            updateDbPiece(source, destination);
            updateDbTurn(state.getTurn());
            return ApiResult.succeed(new MoveResponseDto(command.getSource(), command.getDestination(), isFinished()));
        } catch (RuntimeException e) {
            return ApiResult.failed(e.getMessage());
        }
    }

    private boolean isFinished() {
        return state.isFinished();
    }

    private void savePiece(Position position, Piece piece) {
        boardDao.save(position, piece);
    }

    private void saveTurn(Color color) {
        turnDao.save(color);
    }

    private void updateDbPiece(Position source, Position destination) {
        boardDao.updateByPosition(destination, source);
        boardDao.delete(source);
    }

    private void updateDbTurn(Color color) {
        turnDao.update(color);
    }

    private void deleteAllPiece() {
        boardDao.deleteAll();
    }

    private void deleteTurn() {
        turnDao.delete();
    }
}
