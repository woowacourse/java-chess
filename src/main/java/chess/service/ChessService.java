package chess.service;

import chess.controller.dto.request.MoveRequest;
import chess.controller.dto.response.ChessGameResponse;
import chess.controller.dto.response.ErrorResponse;
import chess.controller.dto.response.StatusResponse;
import chess.controller.dto.response.SuccessResponse;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.board.strategy.CreateCompleteBoardStrategy;

public class ChessService {

    private static final int ROW_INDEX = 0;
    private static final int COLUMN_INDEX = 1;

    private ChessGame chessGame;

    public void createGame() {
        this.chessGame = new ChessGame(new Board(new CreateCompleteBoardStrategy()));
    }

    public ChessGameResponse startGame() {
        try {
            chessGame.start();
            return new SuccessResponse(chessGame);
        } catch (IllegalArgumentException e) {
            return handleError(e);
        }
    }

    public ChessGameResponse move(final MoveRequest moveRequest) {
        try {
            Position start = parseStringToPosition(moveRequest.getStart());
            Position target = parseStringToPosition(moveRequest.getTarget());
            chessGame.move(start, target);
            System.out.println(chessGame.getGameState());
            return new SuccessResponse(chessGame);
        } catch (IllegalArgumentException e) {
            return handleError(e);
        }
    }

    public ChessGameResponse status() {
        try {
            return new StatusResponse(chessGame.createStatus());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return handleError(e);
        }
    }

    public ChessGameResponse end() {
        try {
            chessGame.end();
            return new SuccessResponse(chessGame);
        } catch (IllegalArgumentException e) {
            return handleError(e);
        }
    }

    private ChessGameResponse handleError(Exception e) {
        return new ErrorResponse(e.getMessage());
    }

    private Position parseStringToPosition(final String rawPosition) {
        final String[] separatedPosition = rawPosition.split("");
        final Column column = Column.from(separatedPosition[ROW_INDEX]);
        final Row row = Row.from(separatedPosition[COLUMN_INDEX]);
        return new Position(column, row);
    }
}
