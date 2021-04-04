package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.location.Position;

import java.util.Collections;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> pieceByPosition;
    private boolean isKingDead = false;

    public Board(Map<Position, Piece> maps) {
        this.pieceByPosition = maps;
    }

    public void action(Color color, Position from, Position to) {
        Piece fromPiece = pieceByPosition.get(from);
        if (!fromPiece.isSame(color)) {
            throw new IllegalArgumentException("from 잘못 입력");
        }
        Piece toPiece = pieceByPosition.get(to);
        if (toPiece.isSame(color)) {
            throw new IllegalArgumentException("같은 색깔의 말을 잡을 수 없습니다.");
        }
        move(from, to);
    }

    public void move(Position from, Position to) {
        Piece fromPiece = pieceByPosition.get(from);
        Piece toPiece = pieceByPosition.get(to);

        if (fromPiece.movablePositions(from, Collections.unmodifiableMap(pieceByPosition)).contains(to)){
            pieceByPosition.put(to, fromPiece);
            pieceByPosition.put(from, new Empty());
            if (toPiece.isKing()) {
                isKingDead = true;
            }
        }
    }

    public boolean isNotEnd() {
        return !isKingDead();
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public Piece at(Position position) {
        return pieceByPosition.get(position);
    }


    public Map<Position, Piece> allPieces() {
        return Collections.unmodifiableMap(pieceByPosition);
    }
}