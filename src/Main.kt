import java.io.File

fun main() {
    val inputFileName = "map.csv"
    val inputFile = File(inputFileName)
    val mapSize = 60

    // Check if file exists
    if (!inputFile.exists()) {
        println("File not found")
        return
    }

    val outputFileName = "formattedMap.csv"
    val outPutFile = File(outputFileName)

    outPutFile.bufferedWriter().use { out ->
        // Read CSV line by line
        inputFile.bufferedReader().useLines { lines ->
            var rowIdx = mapSize
            lines.forEach { line ->
                val column = line.split(",")  // Assuming comma is the delimiter
                var columnIdx = 1
                val results = mutableListOf<String>()
                column.forEach { block ->
                    val blockItems = block.split(" ")
                    blockItems.forEach { blockItem ->
                        val formattedRow = String.format("%02d", rowIdx)
                        val formattedColumn = String.format("%02d", columnIdx)
                        val result = "$formattedColumn$formattedRow$blockItem"
                        // Check if file format is correct
                        if (result.length != 6) {
                            println("Wrong format in CSV")
                            return
                        }
                        results.add(result)
                    }
                    columnIdx++
                }
                // Join the results with a comma and write to the output file
                out.write(results.joinToString(",") + "\n")
                rowIdx--
            }
        }
    }
}