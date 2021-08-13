/* PixelVertex.java
   CSC 225 - Summer 2019


   B. Bird - 04/28/2019
   (Add your name/studentID/date here)
*/
import java.awt.Color;

public class PixelVertex{

	int x;
    int y;
	boolean visited;
    PixelVertex[] neighbor;
    
    public PixelVertex(int x, int y){
        
        this.x = x;
        this.y = y;
		visited = false;
        neighbor = new PixelVertex[0];
    }
	
	/* getX()
	   Return the x-coordinate of the pixel corresponding to this vertex.
	*/
	public int getX(){
		return x;
	}
	
	/* getY()
	   Return the y-coordinate of the pixel corresponding to this vertex.
	*/
	public int getY(){
		return y;
	}
	
	/* getNeighbours()
	   Return an array containing references to all neighbours of this vertex.
	   The size of the array must be equal to the degree of this vertex (and
	   the array may therefore contain no duplicates).
	*/
	public PixelVertex[] getNeighbours(){
		return neighbor;
	}
	
	/* addNeighbour(newNeighbour)
	   Add the provided vertex as a neighbour of this vertex.
	*/
	public void addNeighbour(PixelVertex newNeighbour){
		PixelVertex[] newArray = new PixelVertex[neighbor.length + 1];
        for(int i = 0; i < neighbor.length; i++){
            newArray[i] = neighbor[i];
        }
        newArray[neighbor.length] = newNeighbour;
        neighbor = newArray;           
	}
	
	/* removeNeighbour(neighbour)
	   If the provided vertex object is a neighbour of this vertex,
	   remove it from the list of neighbours.
	*/
	public void removeNeighbour(PixelVertex neighbour){
		boolean adjacent = false;
		int index = 0;
		for(int i = 0; i < neighbor.length; i++){
			if(neighbour.x == neighbor[i].x && neighbour.y == neighbor[i].y){
				adjacent = true;
				index = i;
				break;
			}
		}
		if(adjacent){
			PixelVertex[] newArray = new PixelVertex[neighbor.length - 1];
			for(int i = 0; i < neighbor.length - 1; i++){
				if(i < index)
					newArray[i] = neighbor[i];
				else
					newArray[i] = neighbor[i + 1];
			}
			neighbor = newArray;
		}
	}
	
	/* getDegree()
	   Return the degree of this vertex. Since the graph is simple, 
	   the degree is equal to the number of distinct neighbours of this vertex.
	*/
	public int getDegree(){
		return neighbor.length;
	}
	
	/* isNeighbour(otherVertex)
	   Return true if the provided PixelVertex object is a neighbour of this
	   vertex and false otherwise.
	*/
	public boolean isNeighbour(PixelVertex otherVertex){
		for(int i = 0; i < neighbor.length; i++){
			if(otherVertex.x == neighbor[i].x && otherVertex.y == neighbor[i].y)
				return true;
		}
		return false;
	}

}