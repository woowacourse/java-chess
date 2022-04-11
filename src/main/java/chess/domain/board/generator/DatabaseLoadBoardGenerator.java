package chess.domain.board.generator;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseLoadBoardGenerator implements BoardGenerator {

    private final List<PieceDto> pieceDtoList;
    private final Team turn;

    public DatabaseLoadBoardGenerator(List<PieceDto> pieceDtoList, Team turn) {
        this.pieceDtoList = pieceDtoList;
        this.turn = turn;
    }

    @Override
    public Board create() {
        Map<Position, Piece> board = new HashMap<>();
        for (PieceDto pieceDto : pieceDtoList) {
            board.put(Position.of(pieceDto.getPosition()), pieceDto.toPiece());
        }
        return new Board(board, turn);
    }
}
