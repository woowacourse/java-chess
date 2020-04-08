package chess.controller;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.domain.board.Board;
import chess.domain.gamestate.GameState;
import chess.domain.gamestate.NothingHappened;
import chess.dto.PieceDTO;
import utils.Assembler;

import java.util.List;

public class ChessGame {
    private GameState gameState;

    public ChessGame() {
        this.gameState = new NothingHappened();
    }

    public List<PieceDTO> run(String input) {
        Command command = CommandReader.from(input);
        gameState = command.execute(gameState);
        Board board = gameState.getBoard();
        return Assembler.convertMapToDTO(board.getPositionToPiece());
    }

    public List<PieceDTO> stay() {
        Board board = gameState.getBoard();
        return Assembler.convertMapToDTO(board.getPositionToPiece());
    }
}
