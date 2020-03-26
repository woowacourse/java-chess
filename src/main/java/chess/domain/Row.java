package chess.domain;

import chess.domain.chesspiece.ChessPiece;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Row {
    private List<ChessPiece> chessPieces;

    public Row(List<ChessPiece> chessPieces) {
        this.chessPieces = chessPieces;
    }

    public ChessPiece get(int x) {
        return chessPieces.get(x);
    }

    public void modifyRow(int index, ChessPiece chessPiece) {
        chessPieces.set(index, chessPiece);
    }

    public List<ChessPiece> getChessPieces() {
        return chessPieces;
    }

    public Stream<ChessPiece> getStream() {
        return chessPieces.stream();
    }

    public List<ChessPiece> findByTeam(Team team) {
        return chessPieces.stream()
            .filter(chessPiece -> chessPiece.isMatchTeam(team))
            .collect(Collectors.toList());
    }
}
