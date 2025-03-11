/**
 * Kotlin Collections Task 2 - Monkeys in Cages
 *
 *       OOOOO  OOO   OOO
 *          O  O   O O   O
 *         O   O   O O   O
 *       OOOOO  OOO   OOO
 * +-------------+-------------+
 * |    __v__    |             |
 * |   ( o o )   |    __v__    |
 * |    (---)    |   ( o o )   |
 * |    __|__    |    (---)    |
 * |   /|. .|\   |      |      |
 * +-------------+-------------+
 *
 * The monkeys have been rounded up and transported
 * to the Zoo. The zookeepers need some help placing
 * the monkeys into cages. Can you help them?
 */


/**
 * Constant vales used to define some key values
 * which can then be used throughout the code...
 */
const val NUMCAGES = 8      // The total number of cages
const val EMPTY = "---"     // Represents an empty cage


/**
 * Program entry point
 */
fun main() {
    //-------------------------------------------------
    println("Setting up the cages...")

    val cages = setupCages()

    listAllCages(cages)
    println()

    //-------------------------------------------------
    println("Placing monkeys into cages...")

    placeMonkeyInCage(cages, 1, "Kevin")
    placeMonkeyInCage(cages, 8, "Sally")
    placeMonkeyInCage(cages, 4, "Pam")
    placeMonkeyInCage(cages, 3, "Samson")
    placeMonkeyInCage(cages, 5, "Frank")
    placeMonkeyInCage(cages, 6, "Jim")

    println()

    listAllCages(cages)
    println()

    listAllMonkeys(cages)
    println("Monkeys: ${monkeyCount(cages)}")
    println()

    listEmptyCages(cages)
    println("Empty: ${emptyCount(cages)}")
    println()

    listAllMonkeysAndCages(cages)
    println()

    showMonkeyCages(cages)
    println()

    check(monkeyCount(cages) == 6)
    check(emptyCount(cages) == 2)

    //-------------------------------------------------
    println("Clearing out some monkeys...")

    clearCage(cages, 5)
    println()

    showMonkeyCages(cages)
    println()

    check(monkeyCount(cages) == 5)
    check(emptyCount(cages) == 3)

    //-------------------------------------------------
    println("Moving some monkeys around...")

    swapCages(cages, 1, 8)
    println()

    showMonkeyCages(cages)
    println()

    //--------------------

    swapCages(cages, 5, 8)
    println()

    showMonkeyCages(cages)
    println()

    check(monkeyCount(cages) == 5)
    check(emptyCount(cages) == 3)

}

/**
 * Creates and returns a Mutable List, size NUMCAGES,
 * populated with strings representing empty cages
 */
fun setupCages(): MutableList<String> {
    val cageList = mutableListOf<String>()
    for (i in 1..NUMCAGES) cageList.add(EMPTY)
    return cageList
}


/**
 * Put a given monkey into the specified cage number (1...MAX)
 */
fun placeMonkeyInCage(cageList: MutableList<String>, cageNum: Int, name: String) {
    println("+++ Putting $name into cage $cageNum")
    cageList[cageNum -1] = name

}


/**
 * Display a list of all cages in the given list in the format:
 *
 * CAGES
 * - Cage 1: Kevin
 * - Cage 2: ---
 * - Cage 3: Samson
 * - Cage 4: Pam
 * - Etc.
 */
fun listAllCages(cageList: List<String>) {
    println("CAGES")
    for (i in 0 ..< cageList.size) {
        println("- Cage ${i+1}: ${cageList[i]}")
    }

// This does exactly the same thing!
//    for ((i, monkey) in cageList.withIndex()) {
//        println("- Cage ${i+1} $monkey")
//    }
}


/**
 * Display a list of all monkeys found in the given cage list:
 *
 * MONKEYS
 * - Kevin
 * - Samson
 * - Pam
 * - Etc.
 */
fun listAllMonkeys(cageList: List<String>) {
    println("MONKEYS")
        for (i in 0 ..< cageList.size) {
            println("- Monkey ${cageList[i]}")

    }
//    for (i in 0 ..< cageList.size) {
//        println("- Monkey ${cageList[i]}")

    }



/**
 * Display a list of all empty cages in the given cage list:
 *
 * EMPTY CAGES
 * - Cage 2
 * - Cage 7
 * - Etc.
 */
fun listEmptyCages(cageList: List<String>) {
    println("EMPTY CAGES")
    for ((index, i) in cageList.withIndex()) {
        if (i == EMPTY) {
            print("- Cage ${index+1} ")
        }
    }

}


/**
 * Display a full list of all monkeys and the cages they are in.
 * The names and cage numbers should line up in neat columns:
 *
 * MONKEYS & CAGES
 * - Kevin  (Cage 1)
 * - Samson (Cage 3)
 * - Pam    (Cage 4)
 * - Etc.
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun listAllMonkeysAndCages(cageList: List<String>) {
    println("MONKEYS & CAGES")
    for ((index, i) in cageList.withIndex()) {
        if (i != EMPTY) {
            println("- $i (Cage $index) ")
        }
    }
}


/**
 * Returns the number of monkeys found in the given cage list
 */
fun monkeyCount(cageList: List<String>): Int {

    return cageList.count {it != EMPTY}
}


/**
 * Returns the number of cages that are empty in the given cage list
 */
fun emptyCount(cageList: List<String>): Int {

    return cageList.count {it == EMPTY}
}


/**
 * Show all cages from the given list, formatted as a horizontal table:
 *
 * +--------+--------+--------+--------+----
 * | Cage 1 | Cage 2 | Cage 3 | Cage 4 | Etc.
 * +--------+--------+--------+--------+----
 * | Kevin  | ---    | Samson | Pam    | Etc.
 * +--------+--------+--------+--------+----
 *
 * Tip: the String.padEnd(N) function will help you here
 */
fun showMonkeyCages(cageList: List<String>) {
    if (cageList.isEmpty()) return

    val columnWidth = 10  // Define a fixed width for each column
    val separator = "+" + cageList.joinToString("+") { "-".repeat(columnWidth) } + "+"

    // Generate header row (Cage numbers)
    val headers = cageList.indices.joinToString("|") { " Cage ${it + 1} ".padEnd(columnWidth) }

    // Generate values row (Monkey names)
    val values = cageList.joinToString("|") { it.padEnd(columnWidth) }

    // Print formatted table
    println(separator)
    println("|$headers|")
    println(separator)
    println("|$values|")
    println(separator)
}




/**
 * Make a given cage empty (if a monkey was in it, it's gone now!)
 */
fun clearCage(cageList: MutableList<String>, cageNum: Int) {
    println("--- Clearing cage $cageNum")
    cageList.removeAt(cageNum)
}


/**
 * Swap the contents of two given cages.
 *
 * If one was full and the other empty, then the monkey just swaps
 * into the empty cage.
 */
fun swapCages(cageList: MutableList<String>, cageNum1: Int, cageNum2: Int) {
    println("<-> Swapping cages $cageNum1 and $cageNum2")

}








