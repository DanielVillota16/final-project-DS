package problems;

public class TestCasesGenerator {
	
	public static String generateMiceAndMazeCase(int c) {
		String input = c+"\n\n";
		int maxWeight = 100;
		int maxVertices = 100;
		int maxEdges = 100;
		for (int i = 0; i < c; i++) {
			int n = (int)(Math.random()*maxVertices+1);
			int e = (int)(Math.random()*n+1);
			int m = (int)(Math.random()*maxEdges+1);
			int[][] data = new int[m][3];
			int totalWeight = 0;
			for (int j = 0; j < m; j++) {
				int s = (int)(Math.random()*n+1);
				int d = (int)(Math.random()*n+1);
				int w = (int)(Math.random()*maxWeight+1);
				while(s == d) {
					s = (int)(Math.random()*n+1);
					d = (int)(Math.random()*n+1);
				}
				data[j][0] = s;
				data[j][1] = d;
				data[j][2] = w;
				totalWeight += w;
			}
			int t = (int)(Math.random()*totalWeight+1);
			input += n + "\n" + e + "\n" + t + "\n" + m +"\n";
			for (int j = 0; j < m; j++) {
				input += data[j][0] +" "+ data[j][1] +" "+ data[j][2]+"\n";
			}
			if(i < c-1) input += "\n";
		}
		return input;
	}
	
	public static String generateDarkRoadsCase(int c) {
		String input = "";
		int max = 200;
		int maxWeight = 1000;
		for (int i = 0; i < c; i++) {
			int m = (int)(Math.random()*max+1);
			int n = (int)(Math.random()*max+(m-1));
			input += m + " " + n + "\n";
			for (int j = 0; j < m-1; j++) {
				int x = j;
				int y = j+1;
				int z = (int)(Math.random()*maxWeight+1);
				input += x +" "+ y +" "+ z + "\n";
			}
			int zz = (int)(Math.random()*maxWeight+1);
			input += m-1 +" "+ 0 +" "+ zz + "\n";
			for (int j = m; j < n; j++) {
				int x = (int)(Math.random()*m);
				int y = (int)(Math.random()*m);
				while (x == y) {
					x = (int)(Math.random()*m);
					y = (int)(Math.random()*m);
				}
				int z = (int)(Math.random()*maxWeight+1);
				input += x +" "+ y +" "+ z + "\n";
			}
		}
		input += "0 0";
		return input;
	}
	
	public static void main(String[] args) {
		//System.out.print(generateMiceAndMazeCase((int)(Math.random()*100 + 1)));
		System.out.println(generateDarkRoadsCase((int)(Math.random()*100 + 1)));
	}
	
}
