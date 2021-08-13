/* PixelGraph.java
   CSC 225 - Summer 2019

   B. Bird - 04/28/2019
   Xuhui Wang/Eric Wang. V00913734. Aug 1, 2019.
*/ 

import java.awt.Color;

public class PixelGraph{
	
	
	PixelVertex[][] graph;
	/* PixelGraph constructor
	   Given a 2d array of colour values (where element [x][y] is the colour 
	   of the pixel at position (x,y) in the image), initialize the data
	   structure to contain the pixel graph of the image. 
	   Build the Pixel graph within iterating over the 2d-colour array 
	   one time. So the running time of this method is O(n) where n represents
	   the number of vertices.
	*/
    
	public PixelGraph(Color[][] imagePixels){		
		graph = new PixelVertex[imagePixels.length][imagePixels[0].length];
		
  		for (int i = 0; i < imagePixels.length; i++){
			for (int j = 0; j < imagePixels[0].length; j++){
				graph[i][j] = new PixelVertex(i, j);
				
				//Connect the vertices with the same color horizontally.
				if(j > 0){
					if(imagePixels[i][j].equals(imagePixels[i][j - 1])){					
						graph[i][j].addNeighbour(graph[i][j - 1]);
						graph[i][j - 1].addNeighbour(graph[i][j]);
					}
				}
				
				//Connect the vertices with the same color vertically.
				if(i > 0){
					if(imagePixels[i][j].equals(imagePixels[i - 1][j])){
						graph[i][j].addNeighbour(graph[i - 1][j]);
						graph[i - 1][j].addNeighbour(graph[i][j]);
					}
				}					
			}
		}
	}
	
	/* getPixelVertex(x,y)
	   Given an (x,y) coordinate pair, return the PixelVertex object 
	   corresponding to the pixel at the provided coordinates.
	   This method is not required to perform any error checking (and you may
	   assume that the provided (x,y) pair is always a valid point in the 
	   image).
	*/
	public PixelVertex getPixelVertex(int x, int y){
		
		return graph[x][y];
	}
	
	/* getWidth()
	   Return the width of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getWidth(){
		return graph[0].length;
	}
	
	/* getHeight()
	   Return the height of the image corresponding to this PixelGraph 
	   object.
	*/
	public int getHeight(){
		return graph.length;
	}
	
}