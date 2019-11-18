package problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import graphs.AdjListGraph;

public class DarkRoads {

	public static void main(String[] args) throws IOException {
		//darkRoadsAdjList();
		darkRoadsAdjMatrix();
	}
	
	public static void darkRoadsAdjList() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("0 0")) {
			int m = Integer.parseInt(line.split(" ")[0]);
			int n = Integer.parseInt(line.split(" ")[1]);
			int sumRoads = 0;
			AdjListGraph<Integer> graph = new AdjListGraph<>(false, true);
			for (int i = 0; i < m; i++) {
				graph.addVertex(i);
			}
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				int x = Integer.parseInt(line.split(" ")[0]);
				int y = Integer.parseInt(line.split(" ")[1]);
				int z = Integer.parseInt(line.split(" ")[2]);
				sumRoads += z;
				graph.addEdge(x, y, z);
			}
			graph.prim(graph.getVertices().get(0));
			int ans = 0;
			for(int i = 0; i < graph.getVertices().size(); ++i) {
				ans += graph.getVertices().get(i).getD();
			}
			System.out.println(sumRoads - ans);
			line = br.readLine();
		}
		br.close();
	}
	
	public static void darkRoadsAdjMatrix() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		while (!line.equals("0 0")) {
			int m = Integer.parseInt(line.split(" ")[0]);
			int n = Integer.parseInt(line.split(" ")[1]);
			int sumRoads = 0;
			AdjListGraph<Integer> graph = new AdjListGraph<>(false, true);
			for (int i = 0; i < m; i++) {
				graph.addVertex(i);
			}
			for (int i = 0; i < n; i++) {
				line = br.readLine();
				int x = Integer.parseInt(line.split(" ")[0]);
				int y = Integer.parseInt(line.split(" ")[1]);
				int z = Integer.parseInt(line.split(" ")[2]);
				sumRoads += z;
				graph.addEdge(x, y, z);
			}
			graph.prim(graph.getVertices().get(0));
			int ans = 0;
			for(int i = 0; i < graph.getVertices().size(); ++i) {
				ans += graph.getVertices().get(i).getD();
			}
			System.out.println(sumRoads - ans);
			line = br.readLine();
		}
		br.close();
	}

}
