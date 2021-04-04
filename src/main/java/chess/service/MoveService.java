package chess.service;

import chess.web.dto.GameDto;
import chess.web.dto.MessageDto;
import chess.domain.board.Board;
import chess.domain.game.ChessGame;
import chess.domain.piece.Position;
import chess.exception.CommandFormatException;
import chess.repository.GameRepository;
import spark.Response;

public class MoveService {
    private static final String POSITION_FORMAT = "[a-h][1-8]";

    public static Object move(String gameId, Response response, String source, String target) {
        ChessGame chessGame = GameRepository.findByGameIdFromCache(gameId);

        validateRightInputs(source, target);
        Position sourcePosition = getPositionFromInput(source);
        Position targetPosition = getPositionFromInput(target);

        return executeMove(sourcePosition, targetPosition, chessGame, response);
    }

    private static Object executeMove(Position sourcePosition, Position targetPosition, ChessGame chessGame, Response response) {
        try {
            chessGame.move(sourcePosition, targetPosition);
        } catch (RuntimeException e) {
            response.status(400);
            return new MessageDto(e.getMessage());
        }

        return new GameDto(chessGame);
    }

    private static void validateRightInputs(String source, String target) {
        if (isRightPositionFormat(source) && isRightPositionFormat(target)) {
            return;
        }

        throw new CommandFormatException();
    }

    private static Position getPositionFromInput(String input) {
        String[] inputs = input.split("");

        int column = inputs[0].charAt(0) - 'a';
        int row = Board.getRow() - Integer.parseInt(inputs[1]);

        return new Position(row, column);
    }

    private static boolean isRightPositionFormat(String inputs) {
        return inputs.matches(POSITION_FORMAT);
    }

}
