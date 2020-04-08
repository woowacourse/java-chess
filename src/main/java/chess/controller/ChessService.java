package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.domain.board.Board;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.NothingHappened;
import chess.dto.PieceDto;
import utils.Assembler;

import java.util.List;

public class ChessService {
    private GameState gameState;

    public ChessService() {
        this.gameState = new NothingHappened();
    }

    public List<PieceDto> run(String input) {
        Command command = CommandReader.from(input);
        gameState = command.execute(gameState);
        Board board = gameState.getBoard();

        return Assembler.convertMapToDTO(board.getPositionToPiece());
    }

    public List<PieceDto> stay() {
        Board board = gameState.getBoard();
        return Assembler.convertMapToDTO(board.getPositionToPiece());
    }
}
