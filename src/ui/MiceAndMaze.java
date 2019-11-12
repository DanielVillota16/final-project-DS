package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import graphs.*;

public class MiceAndMaze {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cases = Integer.parseInt(br.readLine());
		int[] successForCases = new int[cases];
		for (int i = 0; i < cases; i++) {
			br.readLine();
			int success = 0;
			int n = Integer.parseInt(br.readLine()),
				e = Integer.parseInt(br.readLine()),
				t = Integer.parseInt(br.readLine()),
				m = Integer.parseInt(br.readLine());
			AdjListGraph<Integer> maze = new AdjListGraph<Integer>(true, true);
			for (int j = 1; j <= n; j++) {
				maze.addVertex(j);
			}
			for (int j = 0; j < m; j++) {
				String[] mLine = br.readLine().split(" ");
				int a = Integer.parseInt(mLine[0]),
					b = Integer.parseInt(mLine[1]),
					w = Integer.parseInt(mLine[2]);
				maze.addEdge(a, b, w);
			}
			for (int j = 1; j <= n; j++) {
				//if(maze.dijkstraDistances(j, e) <= t) {
				//	success++;
				//}
			}
			successForCases[i] = success;
		}
		for (int i = 0; i < cases; i++) {
			System.out.println(successForCases[i]);
			if(i < cases-1)	System.out.println();
		}
		br.close();
	}
	
}
