package chess.service;

import chess.controller.dto.GameDto;
import chess.controller.dto.MessageDto;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.exception.CommandFormatException;
import chess.repository.GameRepository;
import spark.Response;

public class MoveService {
    private static final String COMMAND = "move";
    private static final String POSITION_FORMAT = "[a-h][1-8]";

    private final String gameId;
    private final Response response;

    public MoveService(String gameId, Response response) {
        this.gameId = gameId;
        this.response = response;
    }

    public Object move(String source, String target) {
        ChessGame chessGame = getChessGameByGameId(gameId);

        validateRightInputs(source, target);
        Position sourcePosition = getPositionFromInput(source);
        Position targetPosition = getPositionFromInput(target);

        return executeMove(sourcePosition, targetPosition, chessGame);
    }

    private Object executeMove(Position sourcePosition, Position targetPosition, ChessGame chessGame) {
        try {
            chessGame.move(sourcePosition, targetPosition);
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    private ChessGame getChessGameByGameId(String gameId) {
        validateChessGameIdExist(gameId);

        return GameRepository.findByGameId(gameId).get();
    }

    private void validateChessGameIdExist(String gameId) {
        if (!GameRepository.findByGameId(gameId).isPresent()) {
            throw new IllegalArgumentException("게임 ID가 존재하지 않습니다.");
        }
    }


    private void validateRightInputs(String source, String target) {
        if (isRightPositionFormat(source) && isRightPositionFormat(target)) {
            return;
        }

        throw new CommandFormatException();
    }

    private Position getPositionFromInput(String input) {
        String[] inputs = input.split("");

        int column = inputs[0].charAt(0) - 'a';
        int row = Board.getRow() - Integer.parseInt(inputs[1]);

        return new Position(row, column);
    }

    private boolean isRightPositionFormat(String inputs) {
        return inputs.matches(POSITION_FORMAT);
    }

}
