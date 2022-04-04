package chess.service;

import chess.domain.ChessWebGame;
import chess.domain.Position;
import chess.dto.MoveDto;
import chess.view.ChessMap;

public class ChessService {

    public ChessMap move(final ChessWebGame chessWebGame, final MoveDto moveDto) {
        final Position currentPosition = Position.of(moveDto.getCurrentPosition());
        final Position destinationPosition = Position.of(moveDto.getDestinationPosition());

        chessWebGame.move(currentPosition, destinationPosition);

        return chessWebGame.createMap();
    }

    public ChessMap initializeGame(final ChessWebGame chessWebGame) {
        return chessWebGame.initializeChessGame();
    }
}
