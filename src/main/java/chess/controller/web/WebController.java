package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.manager.ChessGame;
import chess.domain.piece.Piece;
import chess.view.web.OutputView;
import chess.view.web.PieceSymbolMapper;
import org.json.JSONObject;

import static spark.Spark.*;

public class WebController {

    private ChessGame chessGame;

    public void mapping(){
        staticFiles.location("/public");
        serviceRoot();
        click();
    }

    private void serviceRoot(){
        get("/", (req, res) -> {
            chessGame = new ChessGame();
            chessGame.initNewGame();

            BoardDto boardDto = new BoardDto();
            boardDto.setBoard(boardMapping(chessGame.board()));

            return OutputView.printBoard("board", boardDto);
        });
    }

    private void click(){
        post("/click", (req, res) -> {
            JSONObject jsonData = new JSONObject(req.body());
            String position = jsonData.getString("position");
            return position;
        });
    }

    // XXX :: Service로 분리하기
    private String[][] boardMapping(final Board board){
        String[][] uniCodeBoard = new String[8][8];
        for(Vertical v : Vertical.values()){
            for(Horizontal h : Horizontal.values()){
                Piece piece = board.of(new Position(v,h));
                String uniCode = PieceSymbolMapper.parse(piece.owner(), piece.symbol());
                uniCodeBoard[v.getIndex()-1][h.getIndex()-1] = uniCode;
            }
        }
        return uniCodeBoard;
    }
}
