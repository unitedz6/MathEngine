Explanation of method implementation inside each class, getters and setters will be ignored along with overloading methods and tostring

ArrayOperations(Example Array: {1, 2, 4, 8});

- findMin:
  - Start at data[0] which would be 1
  - Iterate through the array comparing our starting position, which is 1, to the next iteration
  - If less than starting, the new starting is whatever is in data[i], returning the minimum
 
- returnDataLength:
  - Returns the length of the array
 
- returnSortedList
  - Iterates through the array, giving our starting comparison to the i-th element
  - Iterates through the array again, giving our second comparison to the i-th + 1 element (also known as "k")
  - Then compares the starting comparison with the second comparison, swapping if startingComparison is greater than secondComparison
  - Continues to iterate through the lowest value is at the beginning
 
- returnMax
  - Starts at the first element of the array
  - Iterates through the array, comparing our starting position to the next iteration
  - If higher than the starting, the new starting is whatever is the highest element in said array

Lighting
- calculateIntensity
- calculateColor

MathematicalOperations
- squareRoot
- absolute
- max
- factorial
- floor
- round
- exponent
- ceil
- sin
- cos
- toRadians
- toDegrees
- add
- subtract
- multiply
- divide

Matrix
- rotationMatrix
- isCorrectDimension
- getMinor
- calculateDeterminant
- matrixMultiplication


Physics:
- deltaTime
- updateVelocity
- updatePosition

PhysicsParticles:

Renderer:
- clearImage
- renderLoop
- generatePixel
- renderLight
- createRope
- createCircle (will be removed)
- fillCircle (will be removed)

Shapes:
- getArea
- getPerimeter
- calculateVertices
- rotateVertices

Statistics:
- returnRange
- returnAverage
- returnStandardDeviation
- zScoreStandardization
- returnMedian

Vectors:
- getVectorLength
- addVectors
- subtractVectors
- scalarMultiply
- transposeVector
- displacement
- velocity
- vectorDistance
  
