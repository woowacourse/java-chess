package chess.dto;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Side;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PieceDto {

    private final File file;
    private final Rank rank;
    private final Side side;
    private final Type type;

    private PieceDto(final File file, final Rank rank, final Side side, final Type type) {
        this.file = file;
        this.rank = rank;
        this.side = side;
        this.type = type;
    }

    public static PieceDto of(final String fileIndex, final String rankIndex, final String side, final String type) {
        final File file = File.findByIndex(Integer.parseInt(fileIndex));
        final Rank rank = Rank.findByIndex(Integer.parseInt(rankIndex));

        return new PieceDto(file, rank, Side.valueOf(side), Type.valueOf(type));
    }

    public static List<PieceDto> from(final Board board) {
        List<PieceDto> pieceDtos = new ArrayList<>();
        final Map<Position, Piece> boardMap = board.getBoard();

        boardMap.forEach(((position, piece) -> {
            final File file = File.findByIndex(position.fileIndex());
            final Rank rank = Rank.findByIndex(position.rankIndex());
            final Side side = piece.side();
            final Type type = piece.type();

            pieceDtos.add(new PieceDto(file, rank, side, type));
        }));

        return pieceDtos;
    }

    public File getFile() {
        return file;
    }

    public Rank getRank() {
        return rank;
    }

    public Side getSide() {
        return side;
    }

    public Type getType() {
        return type;
    }
}
