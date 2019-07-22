package com.kyrutech.wilsons;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author kylerudy
 */
public class WilsonsMaze extends AbstractMaze {
    List<Cell> unused = new ArrayList<>();

    int width, height;
    
    public WilsonsMaze(int width, int height) {
        this.width = width;
        this.height = height;
        
        //Set initial maze point
        Cell initial = new Cell(0,0);
        maze.add(initial);

        //Initialize the grid
        for(int x = 0;x<width;x++) {
            for(int y = 0;y<height;y++) {
                unused.add(new Cell(x, y));
            }
        }

        //Remove initial maze point
        unused.remove(initial);
    }

    @Override
    public boolean isComplete() {
        return maze.size() == width*height;
    }

    @Override
    public void next() {
        //When empty, choose a new starting point to draw from that's not in the maze
        if(path.isEmpty()) {
            Cell newStart = unused.remove(0);
            path.add(newStart);
        } else {
            //Get the top of the stack
            Cell lastCell = path.peek();
            
            //Pick a random valid direction to travel
            int dir = 0;
            boolean valid = false;
            Cell nextCell = null;
            do {
                dir = (int) (Math.random()*4);
                
                if(dir == 0 && lastCell.y != 0) { //North
                    nextCell = new Cell(lastCell.x, lastCell.y-1);
                    if(!path.contains(nextCell)) { //The next cell is not in the path
                        valid = true;
                        lastCell.north = true;
                        nextCell.south = true;
                        path.push(nextCell);
                    } else {
                        while(!path.peek().equals(nextCell)) {
                            fixPathways();
                            path.pop();
                        }
                        lastCell = path.peek();
                    }
                    
                } else if(dir == 1 && lastCell.y != height-1) { //South
                    nextCell = new Cell(lastCell.x, lastCell.y+1);
                    if(!path.contains(nextCell)) {
                        valid = true;
                        lastCell.south = true;
                        nextCell.north = true;
                        path.push(nextCell);
                    } else {
                        while(!path.peek().equals(nextCell)) {
                            fixPathways();
                            path.pop();
                        }
                        lastCell = path.peek();
                    }
                } else if(dir == 2 && lastCell.x != 0) { //West
                    nextCell = new Cell(lastCell.x-1, lastCell.y);
                    if(!path.contains(nextCell)) {
                        valid = true;
                        lastCell.west = true;
                        nextCell.east = true;
                        path.push(nextCell);
                    } else {
                        while(!path.peek().equals(nextCell)) {
                            fixPathways();
                            path.pop();
                        }
                        lastCell = path.peek();
                    }
                } else if(dir == 3 && lastCell.x != width-1) { //East
                    nextCell = new Cell(lastCell.x+1, lastCell.y);
                    if(!path.contains(nextCell)) {
                        valid = true;
                        lastCell.east = true;
                        nextCell.west = true;
                        path.push(nextCell);
                    } else {
                        while(!path.peek().equals(nextCell)) {
                            fixPathways();
                            path.pop();
                        }
                        lastCell = path.peek();
                    }
                }
            } while(!valid);
            
            if(maze.contains(nextCell)) {
                final Cell finalNextCell = nextCell;
                //Modify that cell in the maze so the connections are valid
                maze.stream()
                        .filter((c) -> c.equals(finalNextCell))
                        .forEach((c) -> {
                            c.east = c.east || finalNextCell.east;
                            c.west = c.west || finalNextCell.west;
                            c.north = c.north || finalNextCell.north;
                            c.south = c.south || finalNextCell.south;
                        });
                path.pop(); //Remove the last cell from the stack
                maze.addAll(path); //Add all the entires in the path to the maze
                unused.removeAll(path);
                path.clear();
            }
            
        }
    }
    
    private void fixPathways() {
        Cell lastCell = path.peek();
        if (path.size() > 1) {
            Cell nextLastCell = path.elementAt(path.size() - 2);
            if (lastCell.north) {
                nextLastCell.south = false;
            }
            if (lastCell.south) {
                nextLastCell.north = false;
            }
            if (lastCell.east) {
                nextLastCell.west = false;
            }
            if (lastCell.west) {
                nextLastCell.east = false;
            }
        }
    }
}
