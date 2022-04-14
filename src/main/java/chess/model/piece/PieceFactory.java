package chess.model.piece;

import chess.model.Team;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {
    private static final List<Piece> blackPieces = new ArrayList<>();
    private static final List<Piece> whitePieces = new ArrayList<>();

    static {
        blackPieces.add(new Pawn(Team.BLACK));
        blackPieces.add(new Rook(Team.BLACK));
        blackPieces.add(new Bishop(Team.BLACK));
        blackPieces.add(new Knight(Team.BLACK));
        blackPieces.add(new King(Team.BLACK));
        blackPieces.add(new Queen(Team.BLACK));
        blackPieces.add(new Empty());

        whitePieces.add(new Pawn(Team.WHITE));
        whitePieces.add(new Rook(Team.WHITE));
        whitePieces.add(new Bishop(Team.WHITE));
        whitePieces.add(new Knight(Team.WHITE));
        whitePieces.add(new King(Team.WHITE));
        whitePieces.add(new Queen(Team.WHITE));
        whitePieces.add(new Empty());
    }

    public static Piece create(String name) {
        String[] array = name.split("-");
        String teamName = array[0];
        String pieceName = array[1];

        if (teamName.equals(Team.WHITE.name().toLowerCase())) {
            return whitePieces.stream()
                    .filter(piece -> pieceName.equals(piece.getName().toLowerCase()))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 조건의 기물이 존재하지 않습니다."));
        }
        return blackPieces.stream()
                .filter(piece -> pieceName.equals(piece.getName().toLowerCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 조건의 기물이 존재하지 않습니다."));
    }
}
