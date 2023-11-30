package common.logic;

import common.models.Board;
import common.models.Coordinate;
import common.models.Piece;
import common.results.MoveResult;

public interface WinCondition {
    MoveResult<Board,Boolean> checkWin(Board board, Piece piece, MoveResult<Board,Boolean> move, Coordinate toSquare);
}
