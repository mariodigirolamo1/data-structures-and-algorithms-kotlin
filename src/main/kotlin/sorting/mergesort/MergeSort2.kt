package sorting.mergesort

private val arrayToSort = arrayOf(123,13,4,5,2345,35,235,235,56,346,2,52,567,25,2367,2,4,72,6,75,72,6,347,346,6,7,5,25,63,73,6)
//val arrayToSort = arrayOf(123,13,4,5,2345)

fun main(){
    sort(arrayToSort = arrayToSort, startIndex = 0, endIndex = arrayToSort.size-1)
    print("Sorted Array: ")
    arrayToSort.forEach{ print("$it ") }
}

private fun sort(
    arrayToSort: Array<Int>,
    startIndex: Int,
    endIndex: Int
){
    // If the following condition is false, we arrived at the single-element array, which does not need to be sorted
    if(startIndex < endIndex){
        // Calculate the middle point index of the array to be sorted
        // Creating this array is not essential, but I think is more readable than a something/2f rounded up or down
        val middleIndex = intArrayOf(startIndex,endIndex).average().toInt()
        // Call sort recursively on the two subarrays resulted from the split the in the middle of the given array
        sort(arrayToSort,startIndex,middleIndex)
        sort(arrayToSort,middleIndex+1, endIndex)
        // After the split, we need to merge the subarrays into one which will result in the given arrays' elements to
        // be sorted by the process of merging itself
        merge(arrayToSort,startIndex,middleIndex,endIndex)
    }
}

private fun merge(
    arrayToSort: Array<Int>,
    startIndex: Int,
    middleIndex: Int,
    endIndex: Int
){
    // Calculate the size of the two subarrays
    val leftArraySize = middleIndex - startIndex + 1
    val rightArraySize = endIndex - middleIndex
    // Declare the two arrays
    val leftArray = arrayToSort.copyOfRange(
        fromIndex = startIndex,
        toIndex = startIndex + leftArraySize
    )
    val rightArray = arrayToSort.copyOfRange(
        fromIndex = middleIndex + 1,
        toIndex = middleIndex + 1 + rightArraySize
    )
    // Compare elements of both subarrays and sort the element of starting array by always inserting the smallest
    // one first
    var leftArrayCurrentPosition = 0
    var rightArrayCurrentPosition = 0
    var arrayToSortCurrentPosition = startIndex
    while (leftArrayCurrentPosition < leftArraySize && rightArrayCurrentPosition < rightArraySize){
        if(leftArray[leftArrayCurrentPosition] < rightArray[rightArrayCurrentPosition]){
            arrayToSort[arrayToSortCurrentPosition++] = leftArray[leftArrayCurrentPosition++]
        }else{
            arrayToSort[arrayToSortCurrentPosition++] = rightArray[rightArrayCurrentPosition++]
        }
    }
    // Insert into the start array the remaining values from one of both subarrays
    leftArray.copyInto(
        destination = arrayToSort,
        destinationOffset = arrayToSortCurrentPosition,
        startIndex = leftArrayCurrentPosition,
        endIndex = leftArraySize
    )
    rightArray.copyInto(
        destination = arrayToSort,
        destinationOffset = arrayToSortCurrentPosition,
        startIndex = rightArrayCurrentPosition,
        endIndex = rightArraySize
    )
    // And we're DONE!
}