package chess.logic.capablancaGame;

import common.logic.WinCondition;
import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.results.MoveResults;

import java.util.Objects;

public class FirstToEat implements WinCondition {

    @Override
    public MoveResults<Board, Boolean> checkWin(Board board, Piece piece, MoveResults<Board, Boolean> move, Coordinate toSquare) {
        if (!Objects.equals(board.getSquare(toSquare).getPiece().getName(), "null")) {
            return new MoveResults<>(move.successfulResult(), true, "CheckMate");
        }
        else return move;
    }
}
