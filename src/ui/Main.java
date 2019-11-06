package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import model.TripRouting;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String[]> info = new ArrayList<>(0);
		ArrayList<String[]> queries = new ArrayList<>(0);
		/*String line = br.readLine();
		while(line != null) {
			info.add(line.split(","));
			line = br.readLine();
		}
		line = br.readLine();
		while(line != null) {
			queries.add(line.split(","));
			line = br.readLine();
		}*/
		String a= "San Luis Obispo,Bakersfield,CA-58,117",
				b="Bakersfield,Mojave,CA-58,65",
				c="Mojave,Barstow,CA-58,70",
				d="Barstow,Baker,I-15,62",
				e="Baker,Las Vegas,I-15,92",
				f="San Luis Obispo,Santa Barbara,US-101,106",
				g="San Luis Obispo,Santa Barbara,CA-1,113",
				h="Santa Barbara,Los Angeles,US-101,95",
				i="Bakersfield,Wheeler Ridge,CA-99,24",
				j="Wheeler Ridge,Los Angeles,I-5,88",
				k="Mojave,Los Angeles,CA-14,94",
				l="Los Angeles,San Bernardino,I-10,65",
				m="San Bernardino,Barstow,I-15,73",
				n="Los Angeles,San Diego,I-5,121",
				o="San Bernardino,San Diego,I-15,103",

				p="Santa Barbara,Las Vegas",
				q="San Diego,Los Angeles",
				r="San Luis Obispo,Los Angeles";
		info.add(a.split(","));
		info.add(b.split(","));
		info.add(c.split(","));
		info.add(d.split(","));
		info.add(e.split(","));
		info.add(f.split(","));
		info.add(g.split(","));
		info.add(h.split(","));
		info.add(i.split(","));
		info.add(j.split(","));
		info.add(k.split(","));
		info.add(l.split(","));
		info.add(m.split(","));
		info.add(n.split(","));
		info.add(o.split(","));
		
		queries.add(p.split(","));
		queries.add(q.split(","));
		queries.add(r.split(","));
		
		String registry = TripRouting.shortestPathWithAdjMatrix(info, queries);
		System.out.println(registry);
		br.close();
	}
	
}
