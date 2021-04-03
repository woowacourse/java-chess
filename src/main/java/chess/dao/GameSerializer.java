package chess.dao;

import chess.domain.board.Board;
import chess.domain.game.Game;
import chess.domain.game.state.BlackTurnState;
import chess.domain.game.state.EndState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.InitialState;
import chess.domain.game.state.WhiteTurnState;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.GameStateDto;
import com.google.gson.Gson;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameSerializer {

    public static Game deserialize(String gameInfo) {
        Gson gson = new Gson();
        GameStateDto gameStateDto = gson.fromJson(gameInfo, GameStateDto.class);
        Board currentBoard = createBoard(gameStateDto.board());
        GameState currentState = createState(gameStateDto.currentState(), currentBoard);
        return new Game(currentState);
    }

    private static Board createBoard(BoardDto board) {
        Map<Position, Piece> coordinates = convert(board);
        return new Board(coordinates);
    }

    private static Map<Position, Piece> convert(BoardDto board) {
        return board.coordinates()
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        entry -> Position.ofName(entry.getKey()),
                        entry -> PieceType.createPiece(entry.getValue())
                ))
                ;
    }

    private static GameState createState(String currentState, Board currentBoard) {
        if ("not started".equals(currentState)) {
            return new InitialState();
        }
        if ("white".equals(currentState)) {
            return new WhiteTurnState(currentBoard);
        }
        if ("black".equals(currentState)) {
            return new BlackTurnState(currentBoard);
        }
        return new EndState(currentBoard);
    }
}
