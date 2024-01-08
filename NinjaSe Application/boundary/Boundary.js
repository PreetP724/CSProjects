//use Offset and boxsize variables as scaling constants to find upper left corner of sqaure and widths/heights

// draw by going through 2D array from Board and fillsColor for every square it finds and fills white for null spots
// get Ninja Location from Board then draw image of proper size at coordinate



export function drawBoard(ctx, board, canvasWidth, canvasHeight) {
    
    var colWidth = canvasWidth/board.numColumns;
 //  console.log(colWidth);
    var rowWidth = canvasHeight/board.numRows;
  //  console.log(board.squares[2][1]);
    ctx.beginPath();
    for(var r = colWidth; r < canvasWidth; r += colWidth){
        ctx.moveTo(r, 0);
        ctx.lineTo(r, canvasHeight);
        ctx.stroke();
    }

     for(var x = rowWidth; x < canvasHeight; x += rowWidth){
        ctx.moveTo(0, x);
        ctx.lineTo(canvasWidth, x);
        ctx.stroke();
     }

     for (let i = 0; i < board.numRows; i++){
        for(let k = 0; k < board.squares[i].length; k ++){
            //(board.squares[i][k]);
            
            if(typeof board.squares[i][k] !== 0){
                var color = board.squares[i][k].color;
                ctx.fillStyle = color;
                //console.log(board.squares[i][k]);
               // ctx.fillRect(0,0, 4, 4);
                ctx.fillRect(board.squares[i][k].col * rowWidth, board.squares[i][k].row * colWidth, colWidth, rowWidth);
            }
            
            
        }
     }
     
    // console.log(board.squares[2][3]);
     //console.log(board.squares[1][3]);
     //console.log(board.ninja.row);

    ctx.fillStyle = 'green';
     
   //  console.log(board.ninja.row);
   //  console.log(board.ninja.column);
   
   ctx.fillRect(board.ninja.column * rowWidth, board.ninja.row * colWidth, 2*colWidth, 2*rowWidth);

     let image = document.getElementById('ninjase');

    ctx.drawImage(image, board.ninja.column * rowWidth, board.ninja.row * colWidth, 2*colWidth, 2*rowWidth);
  
    //ctx.fillRect(board.ninja.column * rowWidth, board.ninja.row * colWidth, 2*colWidth, 2*rowWidth);
    // ctx.fillStyle = 'yellow';
     //ctx.fillRect(0,0, 200, 200);
}


export function redrawBoard(model, canvasObj){

    const ctx = canvasObj.getContext('2d');
    ctx.clearRect(0,0,canvasObj.width, canvasObj.height);
    drawBoard(ctx, model.board, canvasObj.width, canvasObj.height);

}