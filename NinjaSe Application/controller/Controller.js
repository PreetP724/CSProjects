import { Up } from "../model/Model";
import { Left } from "../model/Model";
//have both other config info here to fill model with whole new values then redrawCanvas in useEffect hook in App()
//check guards




export function moveNinjaVertical(model, direction){
    //can Ninja be moved in the direction
    //has game been won

    //stores location where 
    let originalRow = model.board.ninja.row + direction.deltar;
    let originalCol = model.board.ninja.column + direction.deltac;

    var squaresCovered = [];// all squares affected in here
    let loopcount = 0;
// loop goes through and gets all squares affected by move and changes ninja back to position it needed to be moved to once it lands on no more squares 
    for(let i = 0; i < 4; i++){
        //check if ninja can even move in direction for first time or again
        if(model.board.canEvenNinjaMove(direction)){
            model.board.ninja.moveNinja(direction);
        }
        else{
            if(direction === Up){ 
            model.board.ninja.row += (-direction.deltar) * (model.board.numRows - 1);
            //console.log(model.board.ninja.row);
            }
            else{
                console.log("ninja cannnot move down");
                model.board.ninja.row += (-direction.deltar) * (model.board.numRows -1);
            }
        }
        console.log(model.board.ninja.row);
      //  console.log(direction === Up);
      //get new row of ninja
       let ninjaRow = model.board.ninja.rowsOccupied(direction);
       
    //   console.log(model.board.ninja.row);
    //   console.log(ninjaRows);
        var count = 0;
       // get SquaresCovered (only Squares in here)
       //console.log(model.board.squares[ninjaRow][ model.board.ninja.column]);
        var exists = false;
       if(direction === Up){
        let isSquare = model.board.getSquareAt(ninjaRow, model.board.ninja.column);
        if(isSquare !== 0){
            console.log("here");
            console.log(isSquare);
            //start
            
            if(squaresCovered.length >= 1){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                    console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.col === squaresCovered[y].col) && (((isSquare.row + 1) === squaresCovered[y].row) || (isSquare.row - (model.board.numRows - 1) === squaresCovered[y].row))){
                        exists = true;
                    }
                }
                if(!exists){
                    console.log("here out");
                    continue;
                }
            }
            
            model.board.squares[isSquare.row][isSquare.col] = 0;
            squaresCovered.push(isSquare);
            count += 1;
        }
        exists = false;
        isSquare = model.board.getSquareAt(ninjaRow, model.board.ninja.column + 1);
        if(isSquare !== 0){
            //start
            
            if(squaresCovered.length > 1 || loopcount >0){
                console.log("in checj")
                for(let y = 0; y < squaresCovered.length; y++){
                    if((isSquare.col === squaresCovered[y].col) &&  (((isSquare.row + 1) === squaresCovered[y].row) || (isSquare.row - (model.board.numRows - 1) === squaresCovered[y].row))){
                        console.log(isSquare.col === squaresCovered[y].col);
                        exists = true;
                    }
                }
                if(!exists){
                    console.log("here out");
                    continue;
                }
            }
            console.log("adding");
            model.board.squares[isSquare.row][isSquare.col] = 0;
            squaresCovered.push(isSquare);
            count += 1;
        }
        loopcount ++;
    }
    else{
        console.log("in here");
        console.log(ninjaRow);
        let isSquare = model.board.getSquareAt(ninjaRow + 2, model.board.ninja.column);
        if(isSquare !== 0){
            //start down
            console.log(squaresCovered.length);
            if(squaresCovered.length >= 1){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                   // console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.col === squaresCovered[y].col) && (((isSquare.row - 1) === squaresCovered[y].row) || (isSquare.row + (model.board.numRows - 1) === squaresCovered[y].row))){
                        
                        exists = true;
                    }
                }
                if(!exists){
                    console.log("here out");
                    continue;
                }
            }
            console.log("adding to array");
            model.board.squares[isSquare.row][isSquare.col] = 0;
            squaresCovered.push(isSquare);
            count += 1;
        }
        exists = false;
        isSquare = model.board.getSquareAt(ninjaRow + 2, model.board.ninja.column + 1);
        if(isSquare !== 0){
            //start down 
            if(squaresCovered.length > 1 || loopcount > 0){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                  //  console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.col === squaresCovered[y].col) && (((isSquare.row - 1) === squaresCovered[y].row) || (isSquare.row + (model.board.numRows - 1) === squaresCovered[y].row))){
                        
                        exists = true;
                    }
                }
                if(!exists){
                    console.log("here out");
                    continue;
                }
            }
            console.log("adding to array");
            model.board.squares[isSquare.row][isSquare.col] = 0;
            squaresCovered.push(isSquare);
            count += 1;
        }
        loopcount++;
    }

      //if no squares covered set ninja to where it should be and break out of loop
       if(count === 0){
           console.log("breaking from loop");
            break;
       }

       // update score based on affected squares
       
       
    }
    // find where to clear squares in 2D array 
    // change all square coordinates (look for board overflow and correct ) and update 2D array with new square coordinates
   console.log(squaresCovered);
   model.scoreCount += squaresCovered.length;
    for(let p = 0; p < squaresCovered.length; p++){
            let square = squaresCovered[p];
            console.log(square);
            if(square.canEvenSquareMove(direction, model.board.numRows, model.board.numColumns)){
                console.log("square can move");
                square.changeSquareCoord(direction);
            }
            else{
                if(direction === Up){
                   console.log("square cannot move(up)");
                    square.row += (-direction.deltar) * (model.board.numRows - 1);
                }
                else{
                    console.log("square cannot move(down)");
                    square.row += (-direction.deltar) * (model.board.numRows - 1);
                }
            }
            //square.changeSquareCoord(direction);
            console.log(square.row);
            model.board.squares[square.row][square.col] = square;
    }

    model.board.ninja.row = originalRow;
    model.board.ninja.column = originalCol;
    
    model.moveCount += 1;

}



export function moveNinjaHorizontal(model, direction){
    //can Ninja be moved in the direction
    //has game been won

    //stores location where 
    let originalRow = model.board.ninja.row + direction.deltar;
    let originalCol = model.board.ninja.column + direction.deltac;

    var squaresCovered = [];// all squares affected in here
    let loopcount = 0;
// loop goes through and gets all squares affected by move and changes ninja back to position it needed to be moved to once it lands on no more squares 
    for(let i = 0; i < 4; i++){
        //check if ninja can even move in direction for first time or again
        if(model.board.canEvenNinjaMove(direction)){
            model.board.ninja.moveNinja(direction);
        }
        else{
            if(direction === Left){
                console.log("ninja cannot move left");
            model.board.ninja.column += (-direction.deltac) * (model.board.numColumns - 1);
            //console.log(model.board.ninja.row);
            }
            else{
                console.log("ninja cannnot move right");
                model.board.ninja.column += (-direction.deltac) * (model.board.numColumns -1);
            }
        }
        console.log(model.board.ninja.column);
      //  console.log(direction === Up);
      //get new row of ninja
       let ninjaCol = model.board.ninja.columnsOccupied(direction);
       console.log(ninjaCol);
    //   console.log(model.board.ninja.row);
    //   console.log(ninjaRows);
        var count = 0;
        console.log(squaresCovered);
        var exists = false;
       // get SquaresCovered (only Squares in here)
       //console.log(model.board.squares[ninjaRow][ model.board.ninja.column]);
       if(direction === Left){
        let isSquare = model.board.getSquareAt(model.board.ninja.row, ninjaCol);
        //console.log(isSquare);
        if(isSquare !== 0){
            //start left
            if(squaresCovered.length >= 1){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                    console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.row === squaresCovered[y].row) && (((isSquare.col + 1) === squaresCovered[y].col) || (isSquare.col - (model.board.numColumns - 1) === squaresCovered[y].col))){
                        exists = true;
                    }
                }
                if(!exists){
                    console.log("here out");
                    continue;
                }
            }
            model.board.squares[isSquare.row][isSquare.col] = 0;
            squaresCovered.push(isSquare);
            count += 1;
        }
        exists = false;
        isSquare = model.board.getSquareAt(model.board.ninja.row +1, ninjaCol);
        console.log(isSquare);
        if(isSquare !== 0){
//start left
            if(squaresCovered.length > 1 || loopcount > 0){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                    console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.row === squaresCovered[y].row) && (((isSquare.col + 1) === squaresCovered[y].col) || (isSquare.col - (model.board.numColumns - 1) === squaresCovered[y].col))){
                        exists = true;
                    }
                }
                if(!exists){
                    console.log("here out");
                    continue;
                }
            }
            model.board.squares[isSquare.row][isSquare.col] = 0;
            //console.log(isSquare.row);
            squaresCovered.push(isSquare);
          //  console.log(isSquare.row);
            count += 1;
        }
        loopcount ++;
    }
    else{
        console.log("in here");
        console.log(ninjaCol);
        let isSquare = model.board.getSquareAt(model.board.ninja.row, ninjaCol + 2);
       console.log(isSquare);
        console.log(isSquare.col);
        if(isSquare !== 0){
            //start right
            if(squaresCovered.length >= 1){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                    console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.row === squaresCovered[y].row) && (((isSquare.col - 1) === squaresCovered[y].col) || (isSquare.col + (model.board.numColumns - 1) === squaresCovered[y].col))){
                        exists = true;
                    }
                }
                if(!exists){
                    continue;
                }
                
            }
            model.board.squares[isSquare.row][isSquare.col] = 0;
                    squaresCovered.push(isSquare);
                    count += 1;
            //ordering of if and this next block
            
        }
        exists = false;
        isSquare = model.board.getSquareAt( model.board.ninja.row + 1, ninjaCol + 2);
        console.log(isSquare);
        if(isSquare !== 0){
            //start right
            if(squaresCovered.length > 1 || loopcount > 0){
                for(let y = 0; y < squaresCovered.length; y++){
                    console.log("inside loop");
                    console.log(isSquare.row);
                    console.log(squaresCovered[y].row);
                    console.log(isSquare.row - (model.board.numRows + 1) === squaresCovered[y].row);
                    if((isSquare.row === squaresCovered[y].row) && (((isSquare.col - 1) === squaresCovered[y].col) || (isSquare.col + (model.board.numColumns - 1) === squaresCovered[y].col))){
                        exists = true;
                    }
                }
                if(!exists){
                   continue;
                }
            }
            model.board.squares[isSquare.row][isSquare.col] = 0;
            squaresCovered.push(isSquare);
            count += 1;
        }
        loopcount++;
    }
    console.log(squaresCovered);
    console.log(count);
      //if no squares covered set ninja to where it should be and break out of loop
       if(count === 0){
           console.log("breaking from loop");
            break;
       }

       // update score based on affected squares
       
       
    }
    // find where to clear squares in 2D array 
    // change all square coordinates (look for board overflow and correct ) and update 2D array with new square coordinates
   console.log(squaresCovered);
   model.scoreCount += squaresCovered.length;
    for(let p = 0; p < squaresCovered.length; p++){
            let square = squaresCovered[p];
            console.log(square);
            console.log(square.col - 1);
            if(square.canEvenSquareMove(direction, model.board.numRows, model.board.numColumns)){
                console.log("square can move");
                square.changeSquareCoord(direction);
            }
            else{
                if(direction === Left){
                   console.log("square cannot move(left)");
                    console.log(square.col);
                    square.col += (-direction.deltac) * (model.board.numColumns -1 );

                }
                else{
                    console.log("square cannot move(right)");
                    square.col += (-direction.deltac) * (model.board.numColumns - 1);
                }
            }
            //square.changeSquareCoord(direction);
            console.log(square.col);
            model.board.squares[square.row][square.col] = square;
    }

    model.board.ninja.row = originalRow;
    model.board.ninja.column = originalCol;
    
    model.moveCount += 1;


}


export function removeColorGroup(model){
    let rowcol = model.board.hasGroup();
    if(rowcol.length !== 0){
        let row = rowcol[0];
        let col = rowcol[1];
        console.log(row);
        model.board.squares[row][col] = 0;
         model.board.squares[row][col+1] = 0;
         model.board.squares[row+1][col]= 0;
        model.board.squares[row+1][col+1] =0;
        model.board.numColors = model.board.numColors - 4;
        model.moveCount += 1;
        model.scoreCount = model.scoreCount + 4;
        console.log(model.board.numColors);
            model.isVictorious();
    }
    
}
/*
resetPuzzle(model){


}


changeConfig(model){


}

*/



// check if there is a square along edge of square depending on which direction
// if yes, get all adjacent squares and put them all in an array
// change the position of all the squares in the array 
// reinsert them into the 2D array




//1. move ninja
// 



//find all indexes ninja is covering
// get squares from those indexes, place in an affectedSquares array, set those Squares to zero in array
//change square coordinates 
// repeat

//if no squares found, move ninja back to position, by storing column or row from after first ninja move, based on vertical or horizontal movement
// change squares in 2D array by looping through affectedSquares array


/*
if(squaresCovered.length !== 0){
                for(let y = 0; y < squaresCovered.length; y++){
                    if(isSquare.col === squaresCovered[y].col){
                        exists = true;
                    }
                }
            }
            if(!exists){
                break;
            }

*/