import java.util.Arrays;

public class Terrain {

	public int[][] elevation;
	public boolean[][] localMins;
	public boolean[][] localMaxs;
	public Point[][] shedLabel;
	public int dim;

	public Terrain(int n) {

		dim = n;

		elevation = new int[dim][dim];

		shedLabel = new Point[dim][dim];

		int influence = (int) dim/3 + 1;

		for (int a = 0; a < dim; a++) {
			int h = (int) (Math.random() * 20 + 40);
			int x = (int) (Math.random() * dim);
			int y = (int) (Math.random() * dim);

			for (int i = -1 * influence + 1; i < influence; i++){
				for (int j = -1 * influence; j < influence; j++){
					try {
						int dist = Math.abs(i) + Math.abs(j);
						elevation[x+i][y+j] += h * (double) (influence-dist + 1) / influence;
					} catch (Exception ignored) {
					}
				}
			}
		}

		getLocalMins();
	}

	public void getLocalMins() {

		localMins = new boolean[dim][dim];
		localMaxs = new boolean[dim][dim];

		for (int x = 0; x < dim; x++) {
			for (int y = 0; y < dim; y++) {

				localMins[x][y] = true;
				localMaxs[x][y] = true;

				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {

						try {
							if (elevation[x][y] > elevation[x+i][y+j]) localMins[x][y] = false;
						} catch (Exception ignored) {
							continue;
						}

						try {
							if (elevation[x][y] < elevation[x+i][y+j]) localMaxs[x][y] = false;
						} catch (Exception ignored) {
							continue;
						}

					}
				}

				if (localMins[x][y] == true) shedLabel[x][y] = new Point(x,y, elevation[x][y]);

			}
		}

	}

	public void display() {

		StdDraw.setCanvasSize(800, 800);
		StdDraw.setScale(0, dim);
   		StdDraw.enableDoubleBuffering();
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				int color = (int) (elevation[i][j] * 0.8);
				if (color > 255) color = 255;
				if (color < 0) color = 0;
				StdDraw.setPenColor(color, color, color);
				StdDraw.filledRectangle(i+0.5, j+0.5, 0.5, 0.5);

				double pr1 = 0.03 * ((double)20 / elevation.length);
				if (localMins[i][j]) {
					StdDraw.setPenColor(StdDraw.RED);
					StdDraw.setPenRadius(pr1);
					StdDraw.point(i+0.5,j+0.5);
				}
				if (localMaxs[i][j]) {
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.setPenRadius(pr1);
					StdDraw.point(i+0.5,j+0.5);
				}

				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.RED);
				if (i < dim-1 && shedLabel[i][j] != shedLabel[i+1][j]) StdDraw.line(i+1, j, i+1, j+1);
				if (j < dim-1 && shedLabel[i][j] != shedLabel[i][j+1]) StdDraw.line(i, j+1, i+1, j+1);

			}
		}

		StdDraw.show();

	}

	public static void main(String[] args) {

		int n = 10;

		if (args.length > 0) n = Integer.parseInt(args[0]);

		Terrain terr = new Terrain(n);

		for (int i = 0; i < n; i++) {
			System.out.println(Arrays.toString(terr.elevation[i]));
		}

		terr.display();

	}


}