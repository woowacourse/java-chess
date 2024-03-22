package chess.dto;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BoardDto {

    private final Map<Position, PieceDto> boardDto;

    private BoardDto(Map<Position, PieceDto> boardDto) {
        this.boardDto = boardDto;
    }

    public static BoardDto of(Board board) {
        List<Position> positions = Position.ALL_POSITIONS;
        Map<Position, PieceDto> boardDto = new HashMap<>();
        positions.forEach(position -> addPiece(board, position, boardDto));
        return new BoardDto(boardDto);
    }

    private static void addPiece(Board board, Position position, Map<Position, PieceDto> boardDto) {
        Optional<Piece> optionalPiece = board.find(position);

        if (optionalPiece.isEmpty()) {
            return;
        }

        Piece piece = optionalPiece.get();
        PieceDto pieceDto = PieceDto.from(piece);
        boardDto.put(position, pieceDto);
    }

    public Optional<PieceDto> find(Position position) {
        return Optional.ofNullable(boardDto.get(position));
    }
}
