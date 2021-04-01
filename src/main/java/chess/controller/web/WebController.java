package chess.controller.web;

import chess.controller.dto.BoardDto;
import chess.controller.dto.PositionsDto;
import chess.domain.board.Board;
import chess.domain.board.position.Horizontal;
import chess.domain.board.position.Position;
import chess.domain.board.position.Vertical;
import chess.domain.manager.ChessGame;
import chess.domain.piece.Piece;
import chess.view.web.OutputView;
import chess.view.web.PieceSymbolMapper;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class WebController {

    private ChessGame chessGame;

    public void mapping(){
        staticFiles.location("/public");
        serviceRoot();
        show();
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

    private void show(){
        post("/show", (req, res) -> {
            JSONObject jsonData = new JSONObject(req.body());
            String position = jsonData.getString("position");
            return reachablePositions(new Position(position));
        });
    }

    // XXX :: Service로 분리하기
    private List<String> reachablePositions(Position source){
        List<String> positions = new ArrayList<>();
        for(Position position : chessGame.reachablePositions(source)){
            positions.add(parsePositionAsString(position));
        }
        return positions;
    }

    // XXX :: Service로 분리하기
    private String parsePositionAsString(Position position){
        return position.vertical().name() + position.horizontal().getIndex();
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
