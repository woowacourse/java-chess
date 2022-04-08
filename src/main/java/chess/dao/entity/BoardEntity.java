package chess.dao.entity;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardEntity {

    private final String name;
    private final String positionColumnValue;
    private final int positionRowValue;
    private final String pieceName;
    private final String pieceTeamValue;

    public BoardEntity(String name,
                       String positionColumnValue,
                       int positionRowValue,
                       String pieceName,
                       String pieceTeamValue) {
        this.name = name;
        this.positionColumnValue = positionColumnValue;
        this.positionRowValue = positionRowValue;
        this.pieceName = pieceName;
        this.pieceTeamValue = pieceTeamValue;
    }

    public static List<BoardEntity> generateBoardEntities(final String name, final Map<Position, Piece> board) {
        List<BoardEntity> boardEntities = new ArrayList<>();
        for (Position position : board.keySet()) {
            Piece piece = board.get(position);
            BoardEntity boardEntity = new BoardEntity(name,
                    String.valueOf(position.getColumn().getValue()),
                    position.getRow().getValue(),
                    piece.getName(),
                    piece.getTeam().getValue()
            );
            boardEntities.add(boardEntity);
        }
        return boardEntities;
    }

    public String getName() {
        return name;
    }

    public String getPositionColumnValue() {
        return positionColumnValue;
    }

    public int getPositionRowValue() {
        return positionRowValue;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getPieceTeamValue() {
        return pieceTeamValue;
    }
}
