package sorting

fun mergeSort(){
    val arrayToSort = arrayOf(123,13,4,5,2345,35,235,235,56,346,2,52,567,25,2367,2,4,72,6,75,72,6,347,346,6,7,5,25,63,73,6)
    sort(arrayToSort = arrayToSort, startIndex = 0, endIndex = arrayToSort.size-1)
}

fun sort(
    arrayToSort: Array<Int>,
    startIndex: Int,
    endIndex: Int
){
    // If the following condition is false, we arrived at the single-element array, which does not need to be sorted
    if(startIndex < endIndex){
        // Calculate the middle point index of the array to be sorted
        val middleIndex = kotlin.math.floor((startIndex + endIndex) / 2f).toInt()
        // Call sort recursively on the two subarrays resulted from the split the in the middle of the given array
        sort(arrayToSort,0,middleIndex)
        sort(arrayToSort,middleIndex+1, arrayToSort.size-1)
        // After the split, we need to merge the subarrays into one which will result in the given arrays' elements to
        // be sorted by the process of merging itself
        merge(arrayToSort,startIndex,middleIndex,endIndex)
    }
}

@OptIn(ExperimentalStdlibApi::class)
fun merge(
    arrayToSort: Array<Int>,
    startIndex: Int,
    middleIndex: Int,
    endIndex: Int
){
    // Calculate the size of the two subarrays
    val leftArraySize = middleIndex - startIndex + 1
    val rightArraySize = endIndex - middleIndex
    // Declare the two arrays
    val leftArray = Array(leftArraySize){0}
    val rightArray = Array(rightArraySize){0}
    // Fill'em with values from the left and right of the middle point in the array to sort, respectively
    for(i in 0 ..< leftArraySize){
        leftArray[i] = arrayToSort[startIndex + i]
    }
    for(j in 0 ..< rightArraySize){
        rightArray[j] = arrayToSort[middleIndex + 1 + j]
    }
    // Compare elements of both subarrays and sort the element of starting array by always inserting the smallest
    // one first
    var i = 0
    var j = 0
    var k = startIndex
    while (i < leftArraySize && j < rightArraySize){
        if(leftArray[i] < rightArray[j]){
            arrayToSort[k] = leftArray[i]
            i++
            k++
        }else{
            arrayToSort[k] = rightArray[j]
            j++
            k++
        }
    }
    // Insert into the start array the remaining values from one of both subarrays
    while (i < leftArraySize){
        arrayToSort[k] = leftArray[i]
        i++
        k++
    }
    while (j < rightArraySize){
        arrayToSort[k] = leftArray[j]
        j++
        k++
    }
    // And we're DONE!
}