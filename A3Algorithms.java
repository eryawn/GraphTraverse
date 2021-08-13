/* A3Algorithms.java
   CSC 225 - Summer 2019


   B. Bird - 04/28/2019
   Xuhui Wang/Eric Wang. V00913734. Aug 1, 2019.
*/ 

import java.awt.Color;
import java.util.LinkedList;

public class A3Algorithms{

	/* FloodFillDFS(v, writer, fillColour)
	   Traverse the component the vertex v using DFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   The running time of DFS traverse is O(n + m) where n represents the
	   number of vertices and m represents the number of edges in the 
	   connected component. Because the graph is 
	   located in a grid, vertices can only be connected horizontally and 
	   vertically, so the number of vertices and the number of edges are 
	   almost equal. So, DFS traverse costs O (n + n) = n.
	*/
	public static void FloodFillDFS(PixelVertex v, PixelWriter writer, Color fillColour){
		v.visited = true;
		writer.setPixel(v.x, v.y, fillColour);		
		for (PixelVertex w : v.neighbor){
			if (!w.visited){
				FloodFillDFS(w, writer, fillColour);
			}
		}		
	}
	
	/* FloodFillBFS(v, writer, fillColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices encountered during the 
	   traversal to fillColour.
	   The running time of BFS traverse is O(n + m) where n represents the
	   number of vertices and m represents the number of edges in the 
	   connected component. Because the graph is 
	   located in a grid, vertices can only be connected horizontally and 
	   vertically, so the number of vertices and the number of edges are 
	   almost equal. So, BFS traverse costs O (n + n) = n.
	*/
	public static void FloodFillBFS(PixelVertex v, PixelWriter writer, Color fillColour){		
 		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();
		q.addLast(v);
		v.visited = true;
		PixelVertex current = null;
		
		//Implement the BFS traverse with a queue.
		while (!q.isEmpty()){
			current = q.removeFirst();
			writer.setPixel(current.x, current.y, fillColour);
			for (PixelVertex w : current.neighbor){
				if (!w.visited){
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
	   The running time of DFS traverse is O(n + m) where n represents the
	   number of vertices and m represents the number of edges in the 
	   connected component. Because the graph is 
	   located in a grid, vertices can only be connected horizontally and 
	   vertically, so the number of vertices and the number of edges are 
	   almost equal. So, DFS traverse costs O (n + n) = n.
	*/
	public static void OutlineRegionDFS(PixelVertex v, PixelWriter writer, Color outlineColour){
		v.visited = true;
		if (v.getDegree() < 4)
			writer.setPixel(v.x, v.y, outlineColour);	
		for (PixelVertex w : v.neighbor){
			if (!w.visited){
				OutlineRegionDFS(w, writer, outlineColour);
			}
		}
	}
	
	/* OutlineRegionBFS(v, writer, outlineColour)
	   Traverse the component the vertex v using BFS and set the colour 
	   of the pixels corresponding to all vertices with degree less than 4
	   encountered during the traversal to outlineColour.
	   The running time of BFS traverse is O(n + m) where n represents the
	   number of vertices and m represents the number of edges in the 
	   connected component. Because the graph is 
	   located in a grid, vertices can only be connected horizontally and 
	   vertically, so the number of vertices and the number of edges are 
	   almost equal. So, BFS traverse costs O (n + n) = n.
	*/
	public static void OutlineRegionBFS(PixelVertex v, PixelWriter writer, Color outlineColour){
 		LinkedList<PixelVertex> q = new LinkedList<PixelVertex>();
		q.addLast(v);
		v.visited = true;
		PixelVertex current = null;
		
		//Implement the BFS traverse with a queue.
		while (!q.isEmpty()){
			current = q.removeFirst();
			if (current.getDegree() < 4)
				writer.setPixel(current.x, current.y, outlineColour);
			for (PixelVertex w : current.neighbor){
				if (!w.visited){
					w.visited = true;
					q.addLast(w);
				}
			}
		}
	}

	/* CountComponents(G)
	   Count the number of connected components in the provided PixelGraph 
	   object.
	   Iterating over the graph costs O(n) where n represents the number
	   of the vertices in the graph. The graph consists of many connected
	   components. Traversing each component costs O(s) where s represents the
	   number of vertices in the component. So the total running time of 
	   traversing the whole graph equals to the sum of running time of 
	   traversing each component. That is O(s)+O(s)+O(s)+...... = O(n).
	   So the running time of this method is O(n + n) = n.
	*/
	public static int CountComponents(PixelGraph G){
		int count = 0;
		for(int i = 0; i < G.graph.length; i++)
			for(int j = 0; j < G.graph[0].length; j++){
				if(!G.graph[i][j].visited){
					CountComponents(G.graph[i][j]);
					//Count imcremented 1 after a component DFS is over.
					count++;
				}
			}	
		return count;
	}
	/* Helper method CountComponents(PixelVertex v).
	   The running time of DFS traverse is O(n + m) where n represents the
	   number of vertices and m represents the number of edges in the 
	   connected component. Because the graph is 
	   located in a grid, vertices can only be connected horizontally and 
	   vertically, so the number of vertices and the number of edges are 
	   almost equal. So, DFS traverse costs O (n + n) = n.
	*/
	private static void CountComponents(PixelVertex v){		
		v.visited = true;
		for(PixelVertex w : v.neighbor){
			if(!w.visited){
				CountComponents(w);
			}
		}		
	}
}