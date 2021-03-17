package chess.domain.piece;

import java.util.Objects;

public class Piece {

    public static final String OVER_DISTANCE_MESSAGE = "해당 말의 이동 가능한 거리를 초과했습니다.";
    public static final String OUT_OF_BOUND_MESSAGE = "움직일려는 목표 좌표가 보드판을 넘어갑니다.";
    public static final String UNABLE_CROSS_MESSAGE = "해당 말은 뛰어넘기가 불가합니다.";
    public static final String SAME_TEAM_MESSAGE = "같은 팀의 말입니다.";

    private PieceKind pieceKind;
    private PieceColor pieceColor;

    public Piece(PieceKind pieceKind, PieceColor pieceColor) {
        this.pieceKind = pieceKind;
        this.pieceColor = pieceColor;
    }

    public boolean isSameColor(Piece piece) {
        return this.pieceColor == piece.getPieceColor();
    }

    public String getSymbol() {
        return pieceKind.getName(pieceColor);
    }

    public PieceColor getPieceColor() {
        return this.pieceColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceKind == piece.pieceKind && pieceColor == piece.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceKind, pieceColor);
    }
}
