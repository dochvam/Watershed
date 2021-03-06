/**
 * Identifies the watersheds of a randomly generated terrain file. Could be applied
 * to any raster object with elevation data (with some tweaking to fix inputs)
 *
 * @author <a href="mailto:bg5087a@american.edu">Ben Goldstein</a>
 * @version 1.1
 */


import java.util.*;

public class Watershed {


     /**
     * Queues up all the local minima in a Terrain object.
     *
     * @param terr is your Terrain object
     * @return a PriorityQueue of mins on the board
     */
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

		//what are the dimensions of your grid?
		int dim = 10;
		if (args.length > 0) dim = Integer.parseInt(args[0]);

		//Randomly generate terrain
		Terrain terr = new Terrain(dim);

		PriorityQueue<Point> mins = queueAllMins(terr);

		//the options for adjacent blocks:
		int[][] checks = {{-1,0},{0,1},{1,0},{0,-1}};


		//the algorithm: poll the lowest point in the priority queue. For each adjacent unlabeled 
		// 	block, label it with this point's label (the local min that "owns" this point). Then add
		// 	each of these newly-labeled points to the queue.
		while (!mins.isEmpty()) {

			Point p = mins.poll();

			// System.out.println(terr.elevation[p.x][p.y] + "\t" + p.x +", " + p.y);

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

		terr.display();

	}
}