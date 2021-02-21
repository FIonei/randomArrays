//На входе n - натуральное число
//надо сгенерировать n массивов
//заполнить их случайными числами
//массивы имеют случайный неповторяющийся размер
//затем отсортировать массивы:
//нечетный порядковый номер - по убыванию
//чётный порядковый номер - по возрастанию
//на выходе - массив с отсортированными массивами

    fun main() {
        val arrays: ArrayList<Array<Int>> = arrayListOf()

        print("enter a positive integer 'n': ")
        val n = readLine().convertToInt()
        var max = 2*n
        val sizes = createUniqRandNums(n, max)

        println("sizes of $n arrays: ")
        for (i in sizes) print("$i ")
        println()
        // min/max for numbers inside arrays
        max = 100
        val min = max - 2*max

        for (i in sizes) arrays += createRandNums(i, min, max)

        println()
        println("initial arrays: ")
        for (i in arrays)
            {for (j in i) print("$j ")
                println()
            }

        for (i in 0..n-1){
            if (i%2 == 0) arrays[i] = arrays[i].sortFromLowToHigh()
            else arrays[i] = arrays[i].sortFromHighToLow()
        }

        println()
        print("sorted arrays: ")
        println()
        for (i in arrays)
        {for (j in i) print("$j ")
            println()
        }
    }

    fun createRandNums(count: Int, min: Int, max: Int): Array<Int>{
        val arr: Array<Int> = Array(count, {0})
        for (i in 0..count-1) arr[i] = (min..max).random()
        return arr
    }

    fun createUniqRandNums(count: Int, max: Int): Array<Int>{
        val arr: Array<Int> = Array(count, {0})
        var num: Int
        for (i in 0..count-1) {
            do num = (1..max).random() while (num in arr)
            arr[i] = num
        }
        return arr
    }

    fun Array<Int>.sortFromLowToHigh(): Array<Int>{
        val arr = this
        for (i in 1..arr.size-1)
            for (j in 1..arr.size-1)
                if (arr[j]>arr[j-1]) {
                    arr[j]+=arr[j-1]
                    arr[j-1] = arr[j]-arr[j-1]
                    arr[j]-=arr[j-1]
                }
        return arr
    }

    fun Array<Int>.sortFromHighToLow(): Array<Int>{
        val arr = this
        for (i in 1..arr.size-1)
            for (j in 1..arr.size-1)
                if (arr[j]<arr[j-1]) {
                    arr[j]+=arr[j-1]
                    arr[j-1] = arr[j]-arr[j-1]
                    arr[j]-=arr[j-1]
                }
        return arr
    }

    fun String?.convertToInt(): Int{
        val str = this
        if ((str == "")||(str == null)) {println("wrong input, n=5"); return 5}
        for (i in str) if (i !in '0'..'9') {println("wrong input, n=5"); return 5}
        if (str.toInt() == 0) {println("wrong input, n=5"); return 5}
        return str.toInt()
    }