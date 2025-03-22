package chess;

import chess.piece.King;
import chess.piece.Pawn;
import chess.piece.Piece;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessGameTest {

    @Test
    void 왕이_죽었는지_확인할_수_있다(){
        List<Piece> pieces = List.of(new King(new Position(Row.FOUR, Column.C),Color.WHITE));
        ChessGame chessGame = new ChessGame(pieces);
        Assertions.assertThat(chessGame.isSomeBodyKingDoesntExist()).isTrue();
    }

    @Test
    void 왕이_없는_팀이_누군지_알수_있다(){
        List<Piece> pieces = List.of(new King(new Position(Row.FOUR, Column.C), Color.WHITE));
        ChessGame chessGame = new ChessGame(pieces);
        Assertions.assertThat(chessGame.getLoseColor()).isEqualTo(Color.BLACK);
    }

    @Test
    void 기물이_같은_위치에있으면_죽는_기물을_제거한다(){
        Piece movePiece = new Pawn(Fixtures.A1,Color.WHITE);
        Piece deadPiece = new Pawn(Fixtures.A1,Color.BLACK);
        List<Piece> pieces = List.of(movePiece,deadPiece);
        ChessGame chessGame = new ChessGame(pieces);
        Optional<Piece> resultDeadPiece = chessGame.killPieceWhenExistSamePositionPiece(movePiece);
        Assertions.assertThat(resultDeadPiece.get()).isEqualTo(deadPiece);
    }

    @Test
    void 주근_기물이_없으면_제거하지_않는다(){
        Piece movePiece = new Pawn(Fixtures.A1,Color.WHITE);
        Piece deadPiece = new Pawn(Fixtures.A2,Color.BLACK);
        List<Piece> pieces = List.of(movePiece,deadPiece);
        ChessGame chessGame = new ChessGame(pieces);
        Optional<Piece> resultDeadPiece = chessGame.killPieceWhenExistSamePositionPiece(movePiece);
        Assertions.assertThat(resultDeadPiece.isEmpty()).isTrue();
    }
}
