/* A3Algorithms.java
   CSC 225 - Summer 2019


   B. Bird - 04/28/2019
   (Add your name/studentID/date here)
*/ 

import java.awt.Color;
import java.util.LinkedList;

public class A3Algorithms{

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
		v.visited = true;
		writer.setPixel(v.x, v.y, fillColour);
		
		for(PixelVertex w : v.neighbor){
			if(!w.visited){
				FloodFillDFS(w, writer, fillColour);
			}
		}		
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){		
 		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();
		q.addLast(v);
		v.visited = true;
		writer.setPixel(v.x, v.y, fillColour);
		PixelVertex current = null;
		while(!q.isEmpty()){
			current = q.removeFirst();
			writer.setPixel(current.x, current.y, fillColour);
			for(PixelVertex w : current.neighbor){
				if(!w.visited){
					w.visited = true;
					q.addLast(w);
				}
			}
		}		
	}
	
	/* OutlineRegionDFS(v, writer, outlineColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void OutlineRegionDFS(PixelVertex v, PixelWriter writer, Color outlineColour){
		v.visited = true;
		if(v.getDegree() < 4)
			writer.setPixel(v.x, v.y, outlineColour);		
		for(PixelVertex w : v.neighbor){
			if(!w.visited){
				OutlineRegionDFS(w, writer, outlineColour);
			}
		}
	}
	
	/* OutlineRegionBFS(v, writer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   
	   To change the colour of a pixel at position (x,y) in the image to a 
	   colour c, use
			writer.setPixel(x,y,c);
	*/
	public static void OutlineRegionBFS(PixelVertex v, PixelWriter writer, Color outlineColour){
 		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();
		q.addLast(v);
		v.visited = true;
		if(v.getDegree() < 4)
			writer.setPixel(v.x, v.y, outlineColour);
		PixelVertex current = null;
		while(!q.isEmpty()){
			current = q.removeFirst();
			if(current.getDegree() < 4)
				writer.setPixel(current.x, current.y, outlineColour);
			for(PixelVertex w : current.neighbor){
				if(!w.visited){
					w.visited = true;
					q.addLast(w);
				}
			}
		}
	}

	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	*/
	public static int CountComponents(PixelGraph G){
		int count = 0;
		for(int i = 0; i < G.graph.length; i++)
			for(int j = 0; j < G.graph[0].length; j++){
				if(!G.graph[i][j].visited){
					CountComponents(G.graph[i][j]);
					count++;
				}
			}	
		return count;
	}
	
	private static void CountComponents(PixelVertex v){		
		v.visited = true;
		for(PixelVertex w : v.neighbor){
			if(!w.visited){
				CountComponents(w);
			}
		}		
	}
}