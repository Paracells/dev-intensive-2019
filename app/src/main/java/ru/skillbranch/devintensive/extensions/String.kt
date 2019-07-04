package ru.skillbranch.devintensive.extensions

fun String.truncate(value: Int = 16): String {
    var a = this.trim()
    if (a.length >= value+1) {
        return "${(this.substring(0, value+1)).trim()}..."
    } else {
        return "${this.trim()}"
    }

}

/*
 if (this.length >=value ){
        "${this.substring(0,value).trimStart().trimEnd()}..."
    } else {
        return "${this}"
    }
 */
fun String.stripHtml(): String {
    var intag = false
    val inp = this.trim().replace("\\s+".toRegex(), " ").replace("\\n".toRegex(), "")
    println(inp)
    var outp = ""

    for (i in 0 until inp.length) {
        if (!intag && inp[i] == '<') {
            intag = true
            continue
        }
        if (intag && inp[i] == '>') {
            intag = false
            continue
        }
        if (!intag) {
            outp += inp[i]
        }
    }
    return outp.trim()

}