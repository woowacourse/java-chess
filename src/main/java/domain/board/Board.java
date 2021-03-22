package domain.board;

import domain.chessgame.Score;
import domain.piece.Bishop;
import domain.piece.EmptyPiece;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board() {
        board = new HashMap<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.put(new Position(i, j), new EmptyPiece());
            }
        }
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Piece piece(Position position) {
        return board.get(position);
    }

    public void move(Position source, Position target) {
        Piece piece = board.getOrDefault(source, new EmptyPiece());
        if (piece.isNotEmpty() && piece.canMove(this, source, target)) {
            put(source, new EmptyPiece());
            put(target, piece);
        }
    }

    public void put(Position position, Piece piece) {
        board.put(position, piece);
    }

    public void initChessPieces() {
        board.putAll(King.createInitialKing());
        board.putAll(Queen.createInitialQueen());
        board.putAll(Bishop.createInitialBishop());
        board.putAll(Knight.createInitialKnight());
        board.putAll(Rook.createInitialRook());
        board.putAll(Pawn.createInitialPawn());
    }

    public Score whitePiecesScore(){
        Score score = new Score();
        for(Position key : board.keySet()){
            Piece piece = board.get(key);
            if(piece.isNotEmpty() && piece.isBlack()){
                score = score.sum(piece.getScore());
            }
        }
        return score;
    }

    public Score blackPiecesScore(){
        Score score = new Score();
        for(Position key : board.keySet()){
            Piece piece = board.get(key);
            if(piece.isNotEmpty() && !piece.isBlack()){
                score = score.sum(piece.getScore());
            }
        }
        return score;
    }

}
