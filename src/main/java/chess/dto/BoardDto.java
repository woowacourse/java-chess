package chess.dto;

import static chess.domain.position.Row.RANK_1;
import static chess.domain.position.Row.RANK_8;

import chess.domain.ChessGame;
import chess.domain.piece.AbstractPiece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardDto {

    private static final int FIRST_RANK = RANK_8.getRank();
    private static final int LAST_RANK = RANK_1.getRank();
    private static final String FILE_NAMES = Arrays.stream(Column.values())
            .map(Column::getName)
            .collect(Collectors.joining());

    private final Map<Integer, String> piecesByRow;

    public BoardDto(ChessGame game) {
        this.piecesByRow = makePiecesByRow(game.getPieces());
    }

    public Map<Integer, String> makePiecesByRow(Map<Position, AbstractPiece> pieces) {
        return Arrays.stream(Row.values())
                .collect(Collectors.toMap(Row::getRank, row -> makePiecesOf(pieces, row)));
    }

    private String makePiecesOf(Map<Position, AbstractPiece> pieces, Row row) {
        return Arrays.stream(Column.values())
                .map(column -> finSignature(pieces, Position.of(column, row)))
                .collect(Collectors.joining());
    }

    private String finSignature(Map<Position, AbstractPiece> pieces, Position position) {
        if (pieces.containsKey(position)) {
            return pieces.get(position).signature();
        }
        return ".";
    }

    public Map<Integer, String> getPiecesByRow() {
        return piecesByRow;
    }

    public int getFirstRank() {
        return FIRST_RANK;
    }

    public int getLastRank() {
        return LAST_RANK;
    }

    public String getFileNames() {
        return FILE_NAMES;
    }
}
