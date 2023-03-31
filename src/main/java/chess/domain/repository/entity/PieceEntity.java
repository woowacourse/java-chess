package chess.domain.repository.entity;

import chess.domain.piece.Camp;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.repository.mapper.PieceDtoMapper;
import chess.domain.repository.mapper.PositionValueConverter;

public class PieceEntity {
    private final String position;
    private final String pieceType;
    private final String camp;
    private final Long boardId;

    public PieceEntity(String position, String pieceType, String camp, Long boardId) {
        this.position = position;
        this.pieceType = pieceType;
        this.camp = camp;
        this.boardId = boardId;
    }

    public static PieceEntity of(Position position, Piece piece, Long boardId) {
        String pieceType = PieceDtoMapper.convertToPieceValue(piece);
        String camp = getCampName(piece);
        String positionValue = PositionValueConverter.convertToValue(position);

        return new PieceEntity(positionValue, pieceType, camp, boardId);
    }

    private static String getCampName(Piece piece) {
        if (piece.isBlack()) {
            return Camp.BLACK.name();
        }

        return Camp.WHITE.name();
    }

    public String getPosition() {
        return position;
    }

    public String getPieceType() {
        return pieceType;
    }

    public String getCamp() {
        return camp;
    }

    public Long getBoardId() {
        return boardId;
    }
}
