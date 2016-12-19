import java.util.Arrays;

public class Terrain {

	public int[][] elevation;
	public int dim;

	public Terrain(int n) {

		dim = n;

		elevation = new int[dim][dim];

		for (int a = 0; a < dim * dim / 2; a++) {
			int h = (int) (Math.random() * 20 + 20);
			int x = (int) (Math.random() * dim);
			int y = (int) (Math.random() * dim);

			for (int i = -2; i < 2; i++){
				for (int j = -2; j < 2; j++){
					try {
						int dist = Math.abs(i) + Math.abs(j);
						elevation[x+i][y+j] += h * (double) (5-dist) / 5;
					} catch (Exception e){
						continue;
					}
				}
			}
		}

	}

	public void display() {

		StdDraw.setCanvasSize(800, 800);
		StdDraw.setScale(0, dim);
   		StdDraw.enableDoubleBuffering();
		
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				int height = elevation[i][j];
				if (height > 255) height = 255;
				StdDraw.setPenColor(height, height, height);
				StdDraw.filledRectangle(i+0.5, j+0.5, 0.5, 0.5);
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