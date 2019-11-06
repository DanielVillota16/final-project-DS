package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

public class TripRouting {

	public static String shortestPathWithAdjMatrix(ArrayList<String[]> info, ArrayList<String[]> queries) {
		String registry = "";
		GraphAdjList<String> graph = new GraphAdjList<String>();
		for (int i = 0; i < info.size(); i++) {
			String city1 = info.get(i)[0], city2 = info.get(i)[1];
			if(!graph.getGraph().containsKey(city1)) {
				graph.addVertex(city1);
			}
			if(!graph.getGraph().containsKey(city2)) {
				graph.addVertex(city2);
			}
			int weight = Integer.parseInt(info.get(i)[3]);
			String id = info.get(i)[2];
			graph.addEdge(city1, city2, weight, id, true);
		}
		for (int i = 0; i < queries.size(); i++) {
			String source = queries.get(i)[0], dest = queries.get(i)[1];
			Object[] result = graph.dijkstra(source, dest);
			@SuppressWarnings("unchecked")
			HashMap<String, String> pred = (HashMap<String,String>)result[0];
			@SuppressWarnings("unchecked")
			Hashtable<String, Integer> distances = (Hashtable<String, Integer>)result[1];
			List<String> path = new ArrayList<>();
			String actualPred = dest;
			while(!actualPred.equals(source) && actualPred != null && distances.get(actualPred) < Integer.MAX_VALUE) {
				path.add(actualPred);
				//System.out.println(path);
				actualPred = pred.get(actualPred);
			}
			Collections.reverse(path);
			registry += path.toString()+"\n";
		}
		return registry;
	}

}
