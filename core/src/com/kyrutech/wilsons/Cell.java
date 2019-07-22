package com.kyrutech.wilsons;

/**
 *
 * @author kylerudy
 */
public class Cell {
    int x;
    int y;
    
    boolean north,south,west,east;
   
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        north = false;
        south = false;
        west = false;
        east = false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.x;
        hash = 53 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cell other = (Cell) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        return true;
    }
    
    
}
