package chess.service;

import chess.domain.board.Board;
import chess.domain.board.RegularRuleSetup;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.GameStateDto;
import chess.dto.PieceDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameService {

    private Board board;

    public void startNewGame() {
        board = new Board(new RegularRuleSetup());
    }

    public GameStateDto getGameStateDto() {
        return GameStateDto.from(board);
    }

    public List<PieceDto> getPieceDtos() {
        Map<Position, Piece> pieces = board.getPieces();
        return pieces.keySet().stream()
                .map(position -> PieceDto.from(position, pieces.get(position)))
                .collect(Collectors.toList());
    }

}
