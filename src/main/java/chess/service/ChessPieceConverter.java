package chess.service;

import chess.domain.Team;
import chess.domain.piece.Bishop;
import chess.domain.piece.ChessPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.view.ChessPieceName;
import java.util.Objects;

public class ChessPieceConverter {
    private static final String UNVALID_CHESSPIECE_TYPE_EXCEPTION = "[ERROR] 저장소에 저장된 체스피스 이름이 유효하지 않습니다.";

    public static ChessPiece of(String name) {
        if (Objects.equals(name, "P")) {
            return Pawn.of(Team.BLACK);
        }
        if (Objects.equals(name, "N")) {
            return Knight.of(Team.BLACK);
        }
        if (Objects.equals(name, "B")) {
            return Bishop.of(Team.BLACK);
        }
        if (Objects.equals(name, "R")) {
            return Rook.of(Team.BLACK);
        }
        if (Objects.equals(name, "Q")) {
            return Queen.of(Team.BLACK);
        }
        if (Objects.equals(name, "K")) {
            return King.of(Team.BLACK);
        }

        if (Objects.equals(name, "p")) {
            return Pawn.of(Team.WHITE);
        }
        if (Objects.equals(name, "n")) {
            return Knight.of(Team.WHITE);
        }
        if (Objects.equals(name, "b")) {
            return Bishop.of(Team.WHITE);
        }
        if (Objects.equals(name, "r")) {
            return Rook.of(Team.WHITE);
        }
        if (Objects.equals(name, "q")) {
            return Queen.of(Team.WHITE);
        }
        if (Objects.equals(name, "k")) {
            return King.of(Team.WHITE);
        }
        throw new IllegalArgumentException(UNVALID_CHESSPIECE_TYPE_EXCEPTION);
    }

    public static String of(ChessPiece chessPiece) {
        return ChessPieceName.of(chessPiece);
    }
}
