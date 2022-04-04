package chess.web.service;

import chess.console.domain.board.BasicChessBoardGenerator;
import chess.console.domain.board.Board;
import chess.console.domain.board.Position;
import chess.console.domain.board.Score;
import chess.console.domain.command.StatusResult;
import chess.console.domain.piece.Color;
import chess.console.domain.piece.Piece;
import chess.console.domain.state.Ready;
import chess.console.domain.state.State;
import chess.web.dao.BoardDao;
import chess.web.dao.TurnDao;
import chess.web.dto.ApiResult;
import chess.web.dto.MoveCommand;
import chess.web.dto.MoveResponseDto;
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
        Position source = Position.of(command.getSource());
        Position destination = Position.of(command.getDestination());
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
