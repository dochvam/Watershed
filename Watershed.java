import java.util.*;

public class Watershed {

	public static PriorityQueue<Point> queueAllMins(Terrain terr) {

		PriorityQueue<Point> mins = new PriorityQueue<>();

		// int counter = 0;

		for (int i = 0; i < terr.dim; i++) {
			for (int j = 0; j < terr.dim; j++) {
				if (terr.localMins[i][j]) {
					// counter++;
					Point p = new Point(i, j, terr.elevation[i][j]);
					mins.add(p);
					// System.out.println(counter + ": " + i + " " + j);
				}
			}
		}

		return mins;
	}

	public static void main(String[] args) {




		int n = 10;
		if (args.length > 0) n = Integer.parseInt(args[0]);
		Terrain terr = new Terrain(n);

		PriorityQueue<Point> mins = queueAllMins(terr);

		int[][] checks = {{-1,0},{0,1},{1,0},{0,-1}};

		while (!mins.isEmpty()) {

			Point p = mins.poll();

			System.out.println(terr.elevation[p.x][p.y] + "\t" + p.x +", " + p.y);

			for (int a = 0; a<4; a++) {

				int i = checks[a][0];
				int j = checks[a][1];
				
				if (!(p.x+i >= terr.dim || p.x+i < 0 || p.y+j >= terr.dim || p.y+j < 0) 
						&& terr.shedLabel[p.x + i][p.y + j] == null) {
					terr.shedLabel[p.x + i][p.y + j] = terr.shedLabel[p.x][p.y];
					Point temp = new Point(p.x + i, p.y + j, terr.elevation[p.x + i][p.y + j]);
					mins.add(temp);
				}

			}
		}

		// for (int i = 0; i < terr.dim; i++) {
		//	 System.out.println(Arrays.toString(terr.shedLabel[i]));
		// }

		terr.display();

	}
}