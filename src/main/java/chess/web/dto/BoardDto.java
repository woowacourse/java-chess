package chess.web.dto;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.StateType;
import java.util.ArrayList;
import java.util.List;

public class BoardDto {

    private final StateType stateType;

    public BoardDto(StateType stateType) {
        this.stateType = stateType;
    }

    public static List<PieceDto> newInstance(Board board) {
        List<PieceDto> pieces = new ArrayList<>();

        for (int rankIndex = 0; rankIndex < 8; rankIndex++) {
            for (int fileIndex = 0; fileIndex < 8; fileIndex++) {
                Position position = new Position(fileIndex, rankIndex);
                Piece piece = board.findPiece(position);
                PieceDto pieceDto = new PieceDto(piece, position);
                pieces.add(pieceDto);
            }
        }

        return pieces;
    }

    public StateType getStateType() {
        return stateType;
    }
}
