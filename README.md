# image-to-tetris-kt

This is a multiplatform library for turning png images into tetrominoes (or other cell figures).
But this repository is responsible for creating tetromino tessellations,

## Algorithm

Main idea is to create a random [tessellation](https://en.wikipedia.org/wiki/Tessellation).
Algorithm should be fit for any cell figures that have cells connected by sides.

## Image

The image part is secondary (and just an excuse for me to work on yet another procedural generator,
but more mathematical and algorithmic this time). Image processing should be done on the site side with JS.