#Watershed Algorithm

This algorithm identifies the watersheds of a raster image based on single-value cells. The current setup generates a pseudorandom smoothed terrain map, then finds its watersheds, though given proper setup the same input could be used for any raster.

###How to execute

To run this program, execute Watershed.java. The first command line argument is an optional integer setting the dimensions of the square automatically-generated terrain map.